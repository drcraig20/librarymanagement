package de.onpier.librarymanagement.service;



import de.onpier.librarymanagement.abstraction.service.AbstractService;
import de.onpier.librarymanagement.persistence.model.Book;
import de.onpier.librarymanagement.persistence.repo.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService extends AbstractService<Book> {

    public BookService(BookRepository bookRepository) {
        super(bookRepository);
    }

}
