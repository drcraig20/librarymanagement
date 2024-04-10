package de.onpier.librarymanagement.abstraction.service;


import java.util.Collection;

public interface ServiceInterface<A> {
    A getById(String id);

    Collection<A> getAll();
}
