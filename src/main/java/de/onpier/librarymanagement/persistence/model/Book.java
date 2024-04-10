package de.onpier.librarymanagement.persistence.model;

import java.util.Objects;

public class Book {

    private String title;
    private String author;
    private String genre;
    private String publisher;

    public Book() {
    }

    public Book(String author, String genre, String publisher, String title) {
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(getTitle(), book.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getTitle());
    }
}
