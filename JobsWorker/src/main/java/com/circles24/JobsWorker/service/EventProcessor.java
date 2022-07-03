package com.circles24.JobsWorker.service;

import com.circles24.JobsWorker.configuration.MatcherConfiguration;
import com.circles24.JobsWorker.domain.*;
import com.circles24.JobsWorker.exception.Error;
import com.circles24.JobsWorker.matcher.Matcher;
import com.circles24.JobsWorker.matcher.MatcherFactory;
import com.circles24.JobsWorker.parser.FileParserFactory;
import com.circles24.JobsWorker.parser.FileParser;
import com.circles24.JobsWorker.reader.FileReader;
import com.circles24.JobsWorker.reader.FileReaderFactory;
import com.circles24.JobsWorker.repository.JobRepository;
import com.circles24.JobsWorker.repository.JobResultRepository;
import com.circles24.JobsWorker.writer.ResultWriter;
import com.circles24.JobsWorker.writer.ResultWriterFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EventProcessor {
    @Autowired
    private final JobRepository jobRepository;

    @Autowired
    private final JobResultRepository jobResultRepository;

    @Autowired
    private final FileReaderFactory fileReaderFactory;

    @Autowired
    private final FileParserFactory fileParserFactory;

    @Autowired
    private final MatcherFactory matcherFactory;

    @Autowired
    private final MatcherConfiguration matcherConfiguration;

    @Autowired
    private final ResultWriterFactory resultWriterFactory;

    public void process(Event event) {
        String jobId = (String) event.getData().get("id");
        Job job = jobRepository.findById(jobId).orElseThrow(Error.JOB_NOT_FOUND::build);

        if (!JobState.PROCESSING_REQUESTED.equals(job.getState())) {
            throw Error.JOB_NOT_IN_PROCESSING_REQUESTED_STATE.build();
        }

        List<Map<String, String>> buyersList = readAndParseFiles(job.getBuyersFileSource(), job.getBuyersFileFormat(), job.getBuyersFilePath());
        List<Map<String, String>> suppliersList = readAndParseFiles(job.getSuppliersFileSource(), job.getSuppliersFileFormat(), job.getSuppliersFilePath());

        Matcher matcher = matcherFactory.get(matcherConfiguration.getMatcherType());
        List<ResultRecord> matches = matcher.match(buyersList, suppliersList);

        ResultWriter resultWriter = resultWriterFactory.get(job.getResultFormat());
        String resultFilePath = resultWriter.writeResult(matches);

        JobResult jobResult = JobResult.builder().jobId(job.getId()).resultFilePath(resultFilePath)
                .resultFileFormat(job.getResultFormat()).build();
        jobResultRepository.save(jobResult);

        job.setState(JobState.PROCESSED);
        jobRepository.save(job);
    }

    private List<Map<String, String>> readAndParseFiles(FileSource fileSource, FileFormat fileFormat, String filePath) {
        FileReader fileReader = fileReaderFactory.get(fileSource);
        InputStream inputStream = fileReader.readFile(filePath);
        FileParser fileParser = fileParserFactory.get(fileFormat);
        return fileParser.parseFile(inputStream);
    }
}
