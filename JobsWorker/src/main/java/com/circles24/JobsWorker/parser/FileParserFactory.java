package com.circles24.JobsWorker.parser;

import com.circles24.JobsWorker.domain.FileFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class FileParserFactory {
    private final HashMap<FileFormat, FileParser> registry = new HashMap<>();

    public void add(FileFormat fileFormat, FileParser fileParser) {
        registry.put(fileFormat, fileParser);
    }

    public FileParser get(FileFormat fileFormat) {
        return registry.get(fileFormat);
    }

    @Bean
    public static FileParserFactory getFileParserFactory(ObjectMapper objectMapper) {
        FileParserFactory factory = new FileParserFactory();
        factory.add(FileFormat.CSV, new CSVFileParser());
        factory.add(FileFormat.JSON, new JSONFileParser(objectMapper));
        return factory;
    }
}
