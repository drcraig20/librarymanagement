package de.onpier.librarymanagement.persistence.reader;

import de.onpier.librarymanagement.persistence.model.User;
import de.onpier.librarymanagement.service.DateParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserReaderTest {

    @InjectMocks
    private UserReader userReader;

    @Mock
    private DateParser dateParser;

    private final String validUserFullname = "Jumummaaq,James";

    @Test
    @DisplayName("Validate UserReader can pass CSV to map")
    void testReadingUsersData_thenValidateResponse() throws IOException, URISyntaxException {
        Map<String, User> stringUserMap = userReader.readCSVFileToObject("data/user.csv");
        assertFalse(stringUserMap.isEmpty());
    }

    @Test
    @DisplayName("Validate User List contains valid User")
    void testReadingValidUser_thenValidateResponse() throws IOException, URISyntaxException {
        Map<String, User> stringUserMap = userReader.readCSVFileToObject("data/user.csv");
        assertTrue(stringUserMap.containsKey(validUserFullname));
    }

    @Test
    @DisplayName("Validate User List does not contains invalid User")
    void testReadingInValidUser_thenValidateResponse() throws IOException, URISyntaxException {
        String invalidUserFullname = "invalid name";
        Map<String, User> stringUserMap = userReader.readCSVFileToObject("data/user.csv");
        assertFalse(stringUserMap.containsKey(invalidUserFullname));
    }

    @Test
    @DisplayName("Validate User Details Matches")
    void testReadingValidUserDetails_thenValidateResponse() throws IOException, URISyntaxException {
        Map<String, User> stringUserMap = userReader.readCSVFileToObject("data/user.csv");
        User user = stringUserMap.get(validUserFullname);
        assertEquals(validUserFullname, user.getFullName());
        assertEquals("m", user.getGender());
    }

    @Test
    @DisplayName("Validate User resource not valid")
    void testReadingInvalidResource_thenExpectError() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            userReader.readCSVFileToObject("invalidPath/user.csv");
        });

        String expectedMessage = "Cannot invoke \"java.net.URL.toURI()\" because \"resource\" is null";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }
}
