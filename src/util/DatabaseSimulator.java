package util;

import model.attributes.Author;
import model.attributes.Genre;
import model.items.Book;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSimulator {
    // Items collections simulated databases:
    private List<Book> bookCollection = new ArrayList<>();

    // Items attributes simulated databases:
    private List<Author> authorList = new ArrayList<>();
    private List<Genre> genreList = new ArrayList<>();

    public List<Book> getBookCollection() {
        return bookCollection;
    }

    public void setBookCollection(List<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }
}
