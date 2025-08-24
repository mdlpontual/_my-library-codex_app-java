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
    public List<Book> findById() {
        return List.of();
    }

    @Override
    public List<Book> findByTitle() {
        return List.of();
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
