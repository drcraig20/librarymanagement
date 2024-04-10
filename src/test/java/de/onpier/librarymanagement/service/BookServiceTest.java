package de.onpier.librarymanagement.service;


import de.onpier.librarymanagement.persistence.model.Book;
import de.onpier.librarymanagement.persistence.repo.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    Book book1 = new Book("author", "genre", "publisher","title");
    Book book2 = new Book("author1", "genre1", "publisher1","title1");


    @BeforeEach
    void setUp() {
        lenient().when(bookRepository.findById(anyString())).thenReturn(Optional.of(book1));
        lenient().when(bookRepository.getAll()).thenReturn(Set.of(book1, book2));
    }

    @Test
    @DisplayName("Validate Book exists by Title")
    void testGetByValidISBN_thenValidateResponse(){
        String validBookTitle = "title";
        Book actualValidBook = bookService.getById(validBookTitle);

        assertEquals(book1.getTitle(), actualValidBook.getTitle());
    }

    @Test
    @DisplayName("Validate Book does not exists by invalid Title")
    void testFindByInvalidISBN_thenExpectError(){
        String invalidBookTitle = "invalid";
        lenient().when(bookRepository.findById(invalidBookTitle)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookService.getById(invalidBookTitle);
        });

        String expectedMessage = "Data with id: "+ invalidBookTitle+" could not be found";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    @DisplayName("Validate All books exists")
    void testGetAll_thenValidateResponse(){
        Collection<Book> allBooks = bookService.getAll();
        assertEquals(2, allBooks.size());
    }
}
