package de.onpier.librarymanagement.persistence.model;

import java.time.LocalDate;
import java.util.Objects;

public class Borrowed {

    private User borrower;
    private Book book;
    private LocalDate borrowedFrom;
    private LocalDate borrowedTo;

    public Borrowed(User borrower, LocalDate borrowedTo, LocalDate borrowedFrom, Book book) {
        this.borrower = borrower;
        this.borrowedTo = borrowedTo;
        this.borrowedFrom = borrowedFrom;
        this.book = book;
    }

    public LocalDate getBorrowedFrom() {
        return borrowedFrom;
    }

    public void setBorrowedFrom(LocalDate borrowedFrom) {
        this.borrowedFrom = borrowedFrom;
    }

    public LocalDate getBorrowedTo() {
        return borrowedTo;
    }

    public void setBorrowedTo(LocalDate borrowedTo) {
        this.borrowedTo = borrowedTo;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Borrowed borrowed)) return false;
        return Objects.equals(getBorrower(), borrowed.getBorrower()) && Objects.equals(getBook(), borrowed.getBook()) && Objects.equals(getBorrowedFrom(), borrowed.getBorrowedFrom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBorrower(), getBook(), getBorrowedFrom());
    }
}
