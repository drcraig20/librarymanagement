package de.onpier.librarymanagement.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.Objects;

public class User {

    private String name;
    private String firstName;
    private LocalDate memberSince;
    private LocalDate memberTill;
    private String gender;

    public User() {
    }

    public User(String name, String firstName, String gender, LocalDate memberSince, LocalDate memberTill) {
        this.firstName = firstName;
        this.gender = gender;
        this.memberSince = memberSince;
        this.memberTill = memberTill;
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(LocalDate memberSince) {
        this.memberSince = memberSince;
    }

    public LocalDate getMemberTill() {
        return memberTill;
    }

    public void setMemberTill(LocalDate memberTill) {
        this.memberTill = memberTill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getFullName() {
        return name + ","+ firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getName(), user.getName()) && Objects.equals(getFirstName(), user.getFirstName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getFirstName());
    }
}
