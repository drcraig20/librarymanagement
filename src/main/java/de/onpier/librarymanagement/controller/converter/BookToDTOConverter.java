package de.onpier.librarymanagement.controller.converter;


import de.onpier.librarymanagement.abstraction.controller.converter.DataToDTOConverter;
import de.onpier.librarymanagement.controller.dto.BookDTO;
import de.onpier.librarymanagement.persistence.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookToDTOConverter extends DataToDTOConverter<Book, BookDTO> {

    @Override
    public BookDTO fromDataToDTO(Book book) {
        return new BookDTO(book.getTitle(), book.getAuthor(), book.getGenre(), book.getPublisher());
    }
}
