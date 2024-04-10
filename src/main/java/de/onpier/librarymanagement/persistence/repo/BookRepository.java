package de.onpier.librarymanagement.persistence.repo;


import de.onpier.librarymanagement.abstraction.persistence.reader.AbstractReader;
import de.onpier.librarymanagement.abstraction.persistence.repo.AbstractDataInterface;
import de.onpier.librarymanagement.persistence.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository extends AbstractDataInterface<Book> {

    protected BookRepository(AbstractReader<Book> reader) {
        super(reader);
    }

    @Override
    protected String dataUrl() {
        return "data/books.csv";
    }
}
