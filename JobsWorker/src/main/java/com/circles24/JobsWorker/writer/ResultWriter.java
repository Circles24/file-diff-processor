package com.circles24.JobsWorker.writer;

import com.circles24.JobsWorker.domain.ResultRecord;

import java.util.List;

public interface ResultWriter {
    String writeResult(List<ResultRecord> result);
}
