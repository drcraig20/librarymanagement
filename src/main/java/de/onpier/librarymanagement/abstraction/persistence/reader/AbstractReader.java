package de.onpier.librarymanagement.abstraction.persistence.reader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractReader<A> implements ReaderInterface<A, Map<String, A>>{

    @Override
    public Map<String, A> readCSVFileToObject(String csvFilePath) throws IOException, URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(csvFilePath);

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper.schema().withHeader().withColumnSeparator(',').withQuoteChar('"');
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(resource.toURI())), StandardCharsets.ISO_8859_1));
        MappingIterator<Map<String, String>> csvIterator =  csvMapper.readerWithTypedSchemaFor(Map.class).with(csvSchema).readValues(bufferedReader);

        List<Map<String, String>> resultList = new ArrayList<>();
        while (csvIterator.hasNext()) {
            Map<String, String> rowAsMap = csvIterator.next();
            resultList.add(rowAsMap);
        }
        return postProcessor().apply(resultList);
    }
}
