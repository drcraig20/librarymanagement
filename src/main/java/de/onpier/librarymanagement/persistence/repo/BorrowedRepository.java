package de.onpier.librarymanagement.persistence.repo;


import de.onpier.librarymanagement.abstraction.persistence.reader.AbstractReader;
import de.onpier.librarymanagement.abstraction.persistence.repo.AbstractDataInterface;
import de.onpier.librarymanagement.persistence.model.Borrowed;
import org.springframework.stereotype.Repository;

@Repository
public class BorrowedRepository extends AbstractDataInterface<Borrowed> {

    protected BorrowedRepository(AbstractReader<Borrowed> reader) {
        super(reader);
    }


    @Override
    protected String dataUrl() {
        return "data/borrowed.csv";
    }

}
