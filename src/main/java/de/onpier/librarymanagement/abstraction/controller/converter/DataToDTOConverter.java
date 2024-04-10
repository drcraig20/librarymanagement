package de.onpier.librarymanagement.abstraction.controller.converter;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class DataToDTOConverter<A,B> {
    public abstract B fromDataToDTO(A a);

    public Set<B> fromDataToDTOList(Collection<A> data){
        return data.stream().map(this::fromDataToDTO).collect(Collectors.toSet());
    }
}
