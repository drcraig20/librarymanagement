package de.onpier.librarymanagement.controller;

import de.onpier.librarymanagement.abstraction.controller.AbstractController;
import de.onpier.librarymanagement.controller.converter.BookToDTOConverter;
import de.onpier.librarymanagement.controller.dto.BookDTO;
import de.onpier.librarymanagement.controller.dto.BookRequest;
import de.onpier.librarymanagement.persistence.model.Book;
import de.onpier.librarymanagement.persistence.model.Borrowed;
import de.onpier.librarymanagement.persistence.model.User;
import de.onpier.librarymanagement.service.BookService;
import de.onpier.librarymanagement.service.BorrowedService;
import de.onpier.librarymanagement.service.DateParser;
import de.onpier.librarymanagement.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class BooksController extends AbstractController<Book, BookDTO> {
    private final UserService userService;
    private final DateParser dateParser;
    private final BorrowedService borrowedService;

    public BooksController(UserService userService,
                           DateParser dateParser,
                           BorrowedService borrowedService,
                           BookToDTOConverter bookToDTOConverter,
                           BookService bookService) {
        super(bookToDTOConverter, bookService);
        this.userService = userService;
        this.dateParser = dateParser;
        this.borrowedService = borrowedService;
    }

    @GetMapping("/by-user")
    public Set<BookDTO> findAllBorrowedBooksByRange(@ModelAttribute BookRequest bookRequest) {
        String fullname = String.join(",", bookRequest.getName(), bookRequest.getFirstName());
        User user = userService.getById(fullname);
        LocalDate startDate = dateParser.parseDate(bookRequest.getStartDate());
        LocalDate endDate = dateParser.parseDate(bookRequest.getEndDate());
        Set<Book> books = borrowedService.getAllByUser(user).stream()
                .filter(borrowed -> !borrowed.getBorrowedFrom().isBefore(startDate) && !borrowed.getBorrowedFrom().isAfter(endDate))
                .map(Borrowed::getBook)
                .collect(Collectors.toSet());
        return dataToDTOConverter.fromDataToDTOList(books);
    }

    @GetMapping("/available")
    public Set<BookDTO> findAllAvailableBooks() {
        Set<Book> borrowedBooks = borrowedService.getAllStillBorrowed()
                .stream()
                .map(Borrowed::getBook)
                .collect(Collectors.toSet());
        Collection<Book> allBooks = dataService.getAll();
        allBooks.removeAll(borrowedBooks);
        return dataToDTOConverter.fromDataToDTOList(allBooks);
    }

}
