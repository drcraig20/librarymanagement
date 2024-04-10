package de.onpier.librarymanagement.persistence.reader;




import de.onpier.librarymanagement.abstraction.persistence.reader.AbstractReader;
import de.onpier.librarymanagement.persistence.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookReader extends AbstractReader<Book> {

    @Override
    public Function<List<Map<String, String>>, Map<String, Book>> postProcessor() {
        return csvBooks -> csvBooks.stream()
                .filter(this::notEmpty)
                .map(map -> {
                    String title = map.get("Title");
                    String author = map.get("Author");
                    String genre = map.get("Genre");
                    String publisher = map.get("Publisher");
                    return new Book(author,genre,publisher,title);
                })
                .collect(Collectors.toMap(Book::getTitle, book -> book));
    }

    private boolean notEmpty(Map<String, String> book) {
        return StringUtils.hasText(book.get("Title")) &&
        StringUtils.hasText(book.get("Author")) &&
        StringUtils.hasText(book.get("Genre")) &&
        StringUtils.hasText(book.get("Publisher"));
    }
}
