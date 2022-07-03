package com.circles24.JobsWorker.reader;

import com.circles24.JobsWorker.exception.Error;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.InputStream;

@Slf4j
public class LocalSystemFileReader implements FileReader {
    @Override
    public InputStream readFile(String filePath) {
        try {
            return new FileInputStream(filePath);
        } catch (Exception ex) {
            log.error("error while reading file : {}", filePath, ex);
            throw Error.INVALID_FILE_PATH.build();
        }
    }
}
