package de.onpier.librarymanagement.abstraction.service;


import de.onpier.librarymanagement.abstraction.persistence.repo.AbstractDataInterface;

import java.util.Collection;
import java.util.Optional;

public abstract class AbstractService<A> implements ServiceInterface<A> {
    public final AbstractDataInterface<A> dataRepository;

    protected AbstractService(AbstractDataInterface<A> dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public A getById(String id) {
        Optional<A> oneOptionalItem = dataRepository.findById(id);
        return oneOptionalItem
                .orElseThrow(() -> new RuntimeException("Data with id: " + id + " could not be found"));
    }

    @Override
    public Collection<A> getAll() {
        return dataRepository.getAll();
    }
}
