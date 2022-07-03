package com.circles24.JobsWorker.parser;

import com.circles24.JobsWorker.exception.Error;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class JSONFileParser implements FileParser {
    private final ObjectMapper objectMapper;

    @Override
    public List<Map<String, String>> parseFile(InputStream inputStream) {
        try {
            String filePayload = String.valueOf(inputStream);
            return objectMapper.convertValue(filePayload, new TypeReference<List<Map<String, String>>>() {});
        } catch (Exception ex) {
            log.error("error while parsing json file", ex);
            throw Error.INVALID_FILE.build();
        }
    }
}
