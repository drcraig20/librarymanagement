package de.onpier.librarymanagement.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.onpier.librarymanagement.persistence.model.Book;
import de.onpier.librarymanagement.persistence.model.User;

import java.time.LocalDate;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BorrowedDTO(User borrower, Book book, LocalDate borrowedFrom, LocalDate borrowedTo) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BorrowedDTO that)) return false;
        return Objects.equals(borrower(), that.borrower()) && Objects.equals(book(), that.book()) && Objects.equals(borrowedFrom(), that.borrowedFrom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrower(), book(), borrowedFrom());
    }
}
