package model;

import model.attributes.Author;
import model.attributes.Genre;

public abstract class CatalogItem {
    protected Integer id;
    protected String title;
    protected Author author;
    protected Genre genre;

    // --- Future attributes:
    // protected String media;
    // protected String language;
    // protected String publisher;
    // protected Integer originalYear;
    // protected Integer editionYear;
    // protected Boolean isDigital;
    // protected String platform;
    // protected Integer unitsCollected;

    public CatalogItem(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
