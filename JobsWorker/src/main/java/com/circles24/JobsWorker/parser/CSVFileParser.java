package com.circles24.JobsWorker.parser;

import com.circles24.JobsWorker.exception.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

@Slf4j
@Service
public class CSVFileParser implements FileParser {
    private static final String SEPARATOR = ",";

    @Override
    public List<Map<String, String>> parseFile(InputStream inputStream) {
        List<Map<String, String>> response = new ArrayList<>();
        try (Scanner scanner = new Scanner(inputStream)) {
            if (!scanner.hasNextLine()) {
                throw Error.INVALID_FILE.build();
            }

            List<String> keys = parseKeys(scanner.nextLine());

            while (scanner.hasNextLine()) {
                response.add(parseRecord(scanner.nextLine(), keys));
            }
        } catch (Exception ex) {
            log.error("error while reading file", ex);
            throw Error.INVALID_FILE.build();
        }

        return response;
    }

    private List<String> parseKeys(String header) {
        return Arrays.asList(header.split(SEPARATOR));
    }

    private Map<String, String> parseRecord(String record, List<String> keys) {
        List<String> fields = Arrays.asList(record.split(SEPARATOR));
        if (fields.size() != keys.size()) {
            throw Error.INVALID_FILE.build();
        }

        Map<String, String> response = new HashMap<>();

        for(int i=0;i < fields.size();i++) {
            response.put(keys.get(i), fields.get(i));
        }

        return response;
    }
}
