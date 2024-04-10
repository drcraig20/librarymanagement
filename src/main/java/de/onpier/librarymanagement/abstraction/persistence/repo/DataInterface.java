package de.onpier.librarymanagement.abstraction.persistence.repo;

import java.util.Collection;
import java.util.Optional;

public interface DataInterface<A> {

    Optional<A> findById(String id);

    Collection<A> getAll();
}
