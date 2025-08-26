package service;

import model.items.Book;
import repository.impl.SimulatedAuthorRepository;
import repository.impl.SimulatedBookRepository;
import repository.impl.SimulatedGenreRepository;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private final SimulatedBookRepository bookRepository;
    private final SimulatedAuthorRepository authorRepository;
    private final SimulatedGenreRepository genreRepository;

    public BookService(SimulatedBookRepository bookRepository,
                       SimulatedAuthorRepository authorRepository,
                       SimulatedGenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
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
        if (newBookEntry.getTitle() == null || newBookEntry.getTitle().length() < 2) {
            throw new IllegalArgumentException("Invalid title input.");
        } else if (bookRepository.listAll()
                    .stream()
                    .anyMatch(n -> n.getTitle().trim()
                    .equalsIgnoreCase(newBookEntry.getTitle().toLowerCase().trim()))) {
            throw new IllegalArgumentException("This title already have an entry.");
        }

        if (newBookEntry.getAuthor().getName() == null || newBookEntry.getAuthor().getName().length() < 2) {
            throw new IllegalArgumentException("Invalid author name.");
        } else if (authorRepository.listAll()
                .stream()
                .anyMatch(n -> n.getName().trim()
                        .equalsIgnoreCase(newBookEntry.getAuthor().getName().toLowerCase().trim()))) {
            // if any match I will need to swap the input one for the one in the db
            // else i add to the database
        }
    }
}
