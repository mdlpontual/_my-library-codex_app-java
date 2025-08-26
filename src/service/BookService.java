package service;

import model.items.Book;
import repository.impl.SimulatedBookRepository;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private final SimulatedBookRepository bookRepository;

    public BookService(SimulatedBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> listAll() {
        return bookRepository.listAll();
    }

    public Book findById(Integer id) {
        if(id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID input.");
        }
        return bookRepository.findById(id);
    }

    public Book findByTitle(String title) {
        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("Invalid title input.");
        }
        return bookRepository.findByTitle(title);
    }

    public void addBook(Book newBookEntry) {

    }
}
