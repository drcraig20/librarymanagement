package de.onpier.librarymanagement.abstraction.persistence.reader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

interface ReaderInterface<A, B> {

    B readCSVFileToObject(String csvFilePath) throws IOException, URISyntaxException;

    Function<List<Map<String, String>>, B> postProcessor();
}
