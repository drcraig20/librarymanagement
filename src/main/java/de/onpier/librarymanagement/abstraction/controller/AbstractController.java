package de.onpier.librarymanagement.abstraction.controller;


import de.onpier.librarymanagement.abstraction.controller.converter.DataToDTOConverter;
import de.onpier.librarymanagement.abstraction.service.AbstractService;

public abstract class AbstractController<A,B> {
    public final DataToDTOConverter<A,B> dataToDTOConverter;
    public final AbstractService<A> dataService;

    public AbstractController(DataToDTOConverter<A, B> dataToDTOConverter, AbstractService<A> dataService) {
        this.dataToDTOConverter = dataToDTOConverter;
        this.dataService = dataService;
    }
}
