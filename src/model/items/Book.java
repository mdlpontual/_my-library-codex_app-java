package model.items;

import model.CatalogItem;
import model.attributes.Author;
import model.attributes.Genre;

public class Book extends CatalogItem {
    public Book(String title, Author author, Genre genre) {
        super(title, author, genre);
    }
}
