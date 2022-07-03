package com.circles24.JobsWorker.matcher;

import com.circles24.JobsWorker.domain.ResultRecord;

import java.util.List;
import java.util.Map;

public interface Matcher {
    List<ResultRecord> match(List<Map<String, String>> buyersSheet, List<Map<String, String>> suppliersSheet);
}
