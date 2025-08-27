package controller;

import model.items.Book;
import repository.impl.SimulatedAuthorRepository;
import repository.impl.SimulatedBookRepository;
import repository.impl.SimulatedGenreRepository;
import service.BookService;
import util.DatabaseSimulator;

import java.util.List;

public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public List<Book> listAll() {
        return bookService.listAll();
    }

    public void findById() {

    }

    public void findByTitle() {

    }

    public void addBook() {

    }

    public void removeById() {

    }

    public void removeByTitle() {

    }
}
