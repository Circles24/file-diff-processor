package com.circles24.JobsWorker.writer;

import com.circles24.JobsWorker.domain.FileFormat;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResultWriterFactory {
    private final Map<FileFormat, ResultWriter> registry = new HashMap<>();

    public void add(FileFormat fileFormat, ResultWriter resultWriter) {
        registry.put(fileFormat, resultWriter);
    }

    public ResultWriter get(FileFormat fileFormat) {
        return registry.get(fileFormat);
    }

    public static ResultWriterFactory getResultWriterFactory() {
        ResultWriterFactory factory = new ResultWriterFactory();
        factory.add(FileFormat.CSV, new CSVResultWriter());
        factory.add(FileFormat.JSON, new JSONResultWriter());
        return factory;
    }
}
