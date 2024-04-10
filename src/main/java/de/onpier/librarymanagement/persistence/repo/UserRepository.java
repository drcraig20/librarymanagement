package de.onpier.librarymanagement.persistence.repo;

import de.onpier.librarymanagement.abstraction.persistence.reader.AbstractReader;
import de.onpier.librarymanagement.abstraction.persistence.repo.AbstractDataInterface;
import de.onpier.librarymanagement.persistence.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractDataInterface<User> {

    protected UserRepository(AbstractReader<User> reader) {
        super(reader);
    }

    @Override
    protected String dataUrl() {
        return "data/user.csv";
    }
}
