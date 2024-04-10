package de.onpier.librarymanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DateParser {

    Logger logger = Logger.getLogger(getClass().getName());


    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public LocalDate parseDate(String dateStr) {
        if (!StringUtils.hasText(dateStr)) {
            return null;
        }
        try {
            return LocalDate.parse(dateStr, formatter);
        } catch (Exception e) {
            logger.log(Level.WARNING,"Failed to parse date: " + e.getMessage());
        }
        return null;
    }
}
