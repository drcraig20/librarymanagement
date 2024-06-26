package de.onpier.librarymanagement.persistence.reader;


import de.onpier.librarymanagement.abstraction.persistence.reader.AbstractReader;
import de.onpier.librarymanagement.persistence.model.Book;
import de.onpier.librarymanagement.persistence.model.Borrowed;
import de.onpier.librarymanagement.persistence.model.User;
import de.onpier.librarymanagement.persistence.repo.BookRepository;
import de.onpier.librarymanagement.persistence.repo.UserRepository;
import de.onpier.librarymanagement.service.DateParser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BorrowedReader extends AbstractReader<Borrowed> {

    private final DateParser dateParser;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public BorrowedReader(DateParser dateParser, UserRepository userRepository, BookRepository bookRepository) {
        this.dateParser = dateParser;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Function<List<Map<String, String>>, Map<String, Borrowed>> postProcessor() {
        return csvBorrowed -> csvBorrowed.stream()
                .filter(this::notEmpty)
                .map(map -> {
                    Book book = bookRepository.findById(map.get("Book")).orElse(null);
                    User borrower = userRepository.findById(map.get("Borrower")).orElse(null);
                    LocalDate borrowedFrom = dateParser.parseDate(map.get("borrowed from"));
                    LocalDate borrowedTo = dateParser.parseDate(map.get("borrowed to"));
                    return new Borrowed(borrower, borrowedTo, borrowedFrom, book);

                })
                .collect(Collectors.toMap(
                        borrowed -> String.join(",", borrowed.getBorrower().getFullName(), borrowed.getBook().getTitle(), borrowed.getBorrowedFrom().toString()),
                        borrowed -> borrowed)
                );
    }

    private boolean notEmpty(Map<String, String> borrowed) {
        return StringUtils.hasText(borrowed.get("Book")) &&
                StringUtils.hasText(borrowed.get("Borrower")) &&
                StringUtils.hasText(borrowed.get("borrowed from"));
    }
}
