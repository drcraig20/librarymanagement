package de.onpier.librarymanagement.abstraction.persistence.repo;

import de.onpier.librarymanagement.abstraction.persistence.reader.AbstractReader;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractDataInterface<A> implements DataInterface<A> {

    private final AbstractReader<A> reader;
    private Map<String, A> dataCache;

    protected AbstractDataInterface(AbstractReader<A> reader) {
        this.reader = reader;
    }

    @PostConstruct
    protected void postInit() throws IOException, URISyntaxException {
        dataCache = reader.readCSVFileToObject(dataUrl());
    }


    @Override
    public Optional<A> findById(String id) {
        A datum = dataCache.get(id);
        return Optional.ofNullable(datum);
    }

    @Override
    public Collection<A> getAll() {
        return dataCache.values();
    }

    protected abstract String dataUrl();
}
