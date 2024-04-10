package de.onpier.librarymanagement.controller.converter;


import de.onpier.librarymanagement.abstraction.controller.converter.DataToDTOConverter;
import de.onpier.librarymanagement.controller.dto.BorrowedDTO;
import de.onpier.librarymanagement.persistence.model.Borrowed;
import org.springframework.stereotype.Component;

@Component
public class BorrowedToDTOConverter extends DataToDTOConverter<Borrowed, BorrowedDTO> {

    @Override
    public BorrowedDTO fromDataToDTO(Borrowed borrowed) {
        return new BorrowedDTO(borrowed.getBorrower(), borrowed.getBook(), borrowed.getBorrowedFrom(), borrowed.getBorrowedTo());
    }
}
