package com.circles24.JobsWorker.matcher;

import com.circles24.JobsWorker.configuration.MatcherConfiguration;
import com.circles24.JobsWorker.domain.DataType;
import com.circles24.JobsWorker.domain.MatchType;
import com.circles24.JobsWorker.domain.ResultRecord;
import com.circles24.JobsWorker.dto.PartialMatchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DefaultMatcher implements Matcher {
    @Autowired
    private final MatcherConfiguration configuration;

    @Override
    public List<ResultRecord> match(List<Map<String, String>> buyersSheet,
                                      List<Map<String, String>> suppliersSheet) {
        List<ResultRecord> response = new ArrayList<>();
        int n = buyersSheet.size(), m = suppliersSheet.size();
        for (int i=0;i<n;i++) {
            Map<String, String> buyersRecord = buyersSheet.get(i);
            List<ResultRecord> localMatches = new ArrayList<>();
            for (int j=0;j<m;j++) {
                Map<String, String> suppliersRecord = suppliersSheet.get(i);
                if (completeMatch(buyersRecord, suppliersRecord)) {
                    localMatches.add(ResultRecord.builder().buyersRecord(buyersRecord).suppliersRecord(suppliersRecord)
                            .matchType(MatchType.COMPLETE).build());
                } else {
                    PartialMatchResponse partialMatchResponse = partialMatch(buyersRecord, suppliersRecord);
                    if (partialMatchResponse.isPartialMatch()) {
                        response.add(ResultRecord.builder().buyersRecord(buyersRecord).suppliersRecord(suppliersRecord)
                                .matchType(MatchType.PARTIAL).partialMatchDifference(partialMatchResponse.getDifference())
                                .build());
                    }
                }
            }

            if (localMatches.isEmpty()) {
                response.add(ResultRecord.builder().buyersRecord(buyersRecord)
                        .matchType(MatchType.ONLY_IN_SUPPLIER).build());
            } else {
                Optional<ResultRecord> completeMatch = localMatches.stream().filter(ResultRecord::isCompleteMatch).findFirst();
                if (completeMatch.isPresent()) {
                    response.add(completeMatch.get());
                } else {
                    List<ResultRecord> partialMatches = response.stream()
                            .filter(ResultRecord::isPartialMatch)
                            .sorted(Comparator.comparingDouble(ResultRecord::getPartialMatchDifference))
                            .collect(Collectors.toList());

                    if (!partialMatches.isEmpty()) {
                        response.add(partialMatches.get(0));
                    } else {
                        response.add(ResultRecord.builder().buyersRecord(buyersRecord).matchType(MatchType.ONLY_IN_BUYER).build());
                    }
                }
            }
        }

        suppliersSheet.forEach(suppliersRecord -> {
            AtomicBoolean hasMatch = new AtomicBoolean(false);
            buyersSheet.forEach(buyersRecord -> {
                if (completeMatch(suppliersRecord, buyersRecord) || partialMatch(suppliersRecord, buyersRecord).isPartialMatch()) {
                    hasMatch.set(true);
                }
            });
            if (!hasMatch.get()) {
                response.add(ResultRecord.builder().suppliersRecord(suppliersRecord).matchType(MatchType.ONLY_IN_SUPPLIER).build());
            }
        });

        return response;
    }

    private boolean completeMatch(Map<String, String> record1, Map<String, String> record2) {
        List<String> keys = configuration.getMatchingKeys();
        boolean isMatch = true;

        for (String key : keys) {
            DataType type = configuration.getDataTypeMap().get(key);
            if (!type.completeMatch(record1.get(key), record2.get(key))) {
                isMatch = false;
            }
        }

        return isMatch;
    }

    private PartialMatchResponse partialMatch(Map<String, String> record1, Map<String, String> record2) {
        List<String> keys = configuration.getMatchingKeys();
        boolean isMatch = true;
        double difference = 0;

        for (String key : keys) {
            DataType type = configuration.getDataTypeMap().get(key);
            PartialMatchResponse partialMatchResponse = type.partialMatch(record1.get(key), record2.get(key), configuration);
            if (!partialMatchResponse.isPartialMatch()) {
                isMatch = false;
            } else {
                isMatch &= partialMatchResponse.isPartialMatch();
                difference += partialMatchResponse.getDifference();
            }
        }

        return PartialMatchResponse.builder().partialMatch(isMatch).difference(difference).build();
    }
}
