package com.circles24.JobsWorker.matcher;

import com.circles24.JobsWorker.configuration.MatcherConfiguration;
import com.circles24.JobsWorker.domain.MatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MatcherFactory {
    private final Map<MatcherType, Matcher> matcherRegistry = new HashMap<>();

    public void add(MatcherType matcherType, Matcher matcher) {
        matcherRegistry.put(matcherType, matcher);
    }

    public Matcher get(MatcherType matcherType) {
        return matcherRegistry.get(matcherType);
    }

    @Bean
    public static MatcherFactory getMatcherFactory(MatcherConfiguration matcherConfiguration) {
        MatcherFactory matcherFactory = new MatcherFactory();
        matcherFactory.add(MatcherType.DEFAULT_MATCHER, new DefaultMatcher(matcherConfiguration));
        return matcherFactory;
    }
}
