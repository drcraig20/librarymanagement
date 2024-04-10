package de.onpier.librarymanagement.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BookDTO(String title, String author, String genre, String publisher) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDTO bookDTO)) return false;
        return Objects.equals(title(), bookDTO.title());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title());
    }
}
