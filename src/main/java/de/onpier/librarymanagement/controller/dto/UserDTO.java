package de.onpier.librarymanagement.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDTO(String name, String firstName, String gender, LocalDate memberSince, LocalDate memberTill) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO userDTO)) return false;
        return Objects.equals(name(), userDTO.name()) && Objects.equals(firstName(), userDTO.firstName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name(), firstName());
    }
}
