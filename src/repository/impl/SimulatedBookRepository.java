package repository.impl;

import model.items.Book;
import repository.BookRepository;
import util.DatabaseSimulator;

import java.util.ArrayList;
import java.util.List;

public class SimulatedBookRepository implements BookRepository {

    private final DatabaseSimulator bookDatabase;

    public SimulatedBookRepository(DatabaseSimulator bookDatabase) {
        this.bookDatabase = bookDatabase;
    }

    @Override
    public List<Book> listAll() {
        return new ArrayList<>(bookDatabase.getBookCollection());
    }

    @Override
    public Book findById(Integer id) {
        return bookDatabase.getBookCollection()
                .stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Book findByTitle(String title) {
        String trimmedTitle = title.trim();
        return bookDatabase.getBookCollection()
                .stream()
                .filter(book -> book.getTitle().trim().equalsIgnoreCase(trimmedTitle))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addBook(Book newBookEntry) {
        bookDatabase.getBookCollection().add(newBookEntry);
    }

    @Override
    public void removeById(Integer id) {
        bookDatabase.getBookCollection().remove(this.findById(id));
    }

    @Override
    public void removeByTitle(String title) {
        String trimmedTitle = title.trim();
        bookDatabase.getBookCollection().remove(this.findByTitle(trimmedTitle));
    }
}
