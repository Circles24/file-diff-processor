package com.circles24.JobsWorker.parser;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface FileParser {
    List<Map<String, String>> parseFile(InputStream inputStream);
}
