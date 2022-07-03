package com.circles24.JobsWorker.writer;

import com.circles24.JobsWorker.domain.ResultRecord;

import java.util.List;

public class JSONResultWriter implements ResultWriter {
    @Override
    public String writeResult(List<ResultRecord> result) {
        return "sample-json-result-file-path";
    }
}
