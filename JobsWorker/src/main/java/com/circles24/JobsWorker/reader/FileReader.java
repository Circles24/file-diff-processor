package com.circles24.JobsWorker.reader;

import java.io.InputStream;

public interface FileReader {
    InputStream readFile(String filePath);
}
