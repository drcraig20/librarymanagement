package de.onpier.librarymanagement.persistence.reader;

import de.onpier.librarymanagement.persistence.model.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BookReaderTest {

    @InjectMocks
    private BookReader bookReader;

    private final String validBookTitle = "Journal of a Novel";

    @Test
    @DisplayName("Validate BookReader can pass CSV to map")
    void testReadingBooksData_thenValidateResponse() throws IOException, URISyntaxException {
        Map<String, Book> stringBookMap = bookReader.readCSVFileToObject("data/books.csv");
        assertFalse(stringBookMap.isEmpty());
    }

    @Test
    @DisplayName("Validate Book List contains valid Book")
    void testReadingValidBook_thenValidateResponse() throws IOException, URISyntaxException {
        Map<String, Book> stringBookMap = bookReader.readCSVFileToObject("data/books.csv");
        assertTrue(stringBookMap.containsKey(validBookTitle));
    }

    @Test
    @DisplayName("Validate Book List does not contains invalid Book")
    void testReadingInValidBook_thenValidateResponse() throws IOException, URISyntaxException {
        String invalidBookTitle = "invalid title";
        Map<String, Book> stringBookMap = bookReader.readCSVFileToObject("data/books.csv");
        assertFalse(stringBookMap.containsKey(invalidBookTitle));
    }

    @Test
    @DisplayName("Validate Book Details Matches")
    void testReadingValidBookDetails_thenValidateResponse() throws IOException, URISyntaxException {
        Map<String, Book> stringBookMap = bookReader.readCSVFileToObject("data/books.csv");
        Book book = stringBookMap.get(validBookTitle);
        assertEquals("Steinbeck, John", book.getAuthor());
        assertEquals(validBookTitle, book.getTitle());
    }

    @Test
    @DisplayName("Validate Book resource not valid")
    void testReadingInvalidResource_thenExpectError() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            bookReader.readCSVFileToObject("invalidPath/books.csv");
        });

        String expectedMessage = "Cannot invoke \"java.net.URL.toURI()\" because \"resource\" is null";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }
}
