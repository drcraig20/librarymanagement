package de.onpier.librarymanagement.persistence.reader;



import de.onpier.librarymanagement.abstraction.persistence.reader.AbstractReader;
import de.onpier.librarymanagement.persistence.model.Book;
import de.onpier.librarymanagement.persistence.model.Borrowed;
import de.onpier.librarymanagement.persistence.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BorrowedReader extends AbstractReader<Borrowed> {

    @Override
    public Function<List<Map<String, String>>, Map<String, Borrowed>> postProcessor() {
        return csvBorrowed -> csvBorrowed.stream()
                .filter(this::notEmpty)
                .map(map -> {
                    Book book = new Book();
                    User borrower = new User();
                    LocalDate borrowedFrom = LocalDate.now();
                    LocalDate borrowedTo = LocalDate.now();
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
