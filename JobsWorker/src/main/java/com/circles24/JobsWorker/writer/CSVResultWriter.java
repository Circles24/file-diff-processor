package com.circles24.JobsWorker.writer;

import com.circles24.JobsWorker.domain.ResultRecord;

import java.util.List;

public class CSVResultWriter implements ResultWriter {
    @Override
    public String writeResult(List<ResultRecord> result) {
        return "sample-csv-result-path";
    }
}
