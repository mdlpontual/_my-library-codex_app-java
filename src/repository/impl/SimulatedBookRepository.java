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
        return bookDatabase.getBookCollection()
                .stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addNewBook() {

    }

    @Override
    public void removeById() {

    }

    @Override
    public void removeByTitle() {

    }

    @Override
    public void editTitle() {

    }

    @Override
    public void editAuthor() {

    }

    @Override
    public void editGenre() {

    }
}
