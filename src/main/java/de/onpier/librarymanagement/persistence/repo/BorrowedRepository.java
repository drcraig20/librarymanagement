package de.onpier.librarymanagement.persistence.repo;


import de.onpier.librarymanagement.abstraction.persistence.reader.AbstractReader;
import de.onpier.librarymanagement.abstraction.persistence.repo.AbstractDataInterface;
import de.onpier.librarymanagement.persistence.model.Borrowed;
import de.onpier.librarymanagement.persistence.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public class BorrowedRepository extends AbstractDataInterface<Borrowed> {

    protected BorrowedRepository(AbstractReader<Borrowed> reader) {
        super(reader);
    }

    public Collection<Borrowed> getAllByUser(User user) {
        return super.getAll().stream()
                .filter(borrowed -> borrowed.getBorrower().equals(user))
                .collect(Collectors.toSet());
    }

    @Override
    protected String dataUrl() {
        return "data/borrowed.csv";
    }

}
