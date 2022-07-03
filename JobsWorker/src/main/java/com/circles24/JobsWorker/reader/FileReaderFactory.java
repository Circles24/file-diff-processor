package com.circles24.JobsWorker.reader;

import com.circles24.JobsWorker.domain.FileSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FileReaderFactory {
    private final Map<FileSource, FileReader> fileReaderRegistry = new HashMap<>();

    public void add(FileSource fileSource, FileReader fileReader) {
        fileReaderRegistry.put(fileSource, fileReader);
    }

    public FileReader get(FileSource fileSource) {
        return fileReaderRegistry.get(fileSource);
    }

    @Bean
    public static FileReaderFactory getFileReaderFactory() {
        FileReaderFactory factory = new FileReaderFactory();
        factory.add(FileSource.S3, new S3FileReader());
        factory.add(FileSource.LOCAL_SYSTEM, new S3FileReader());
        return factory;
    }
}
