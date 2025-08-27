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

    public Book addBook(Book newBookEntry) {
        if (newBookEntry ==  null) {
            throw new IllegalArgumentException("Invalid book.");
        };

        // basic input check - checks book database:
        // if there is a match, throws error to prevent duplicates
        // if there is no match, returns new entry to proceed to repository - return at the end
        if (newBookEntry.getTitle() == null || newBookEntry.getTitle().length() < 2) {
            throw new IllegalArgumentException("Invalid title input.");
        } else if (bookRepository.listAll()
                .stream()
                .anyMatch(n -> n.getTitle().trim()
                        .equalsIgnoreCase(newBookEntry.getTitle().toLowerCase().trim()))) {
            throw new IllegalArgumentException("This title already has an entry.");
        }

        // basic input check - checks author database:
        // if there is a match, reset new entry's author to the existing one on db
        // if there is no match, adds as a new entry to author db
        if (newBookEntry.getAuthor().getName() == null || newBookEntry.getAuthor().getName().length() < 2) {
            throw new IllegalArgumentException("Invalid author name.");
        } else if (authorRepository.listAll()
                .stream()
                .anyMatch(n -> n.getName().trim().equalsIgnoreCase(
                        newBookEntry.getAuthor().getName().toLowerCase().trim()))) {
            newBookEntry.setAuthor(authorRepository.findByName(newBookEntry.getAuthor().getName()));
        } else {
            authorRepository.addAuthor(newBookEntry.getAuthor());
        }

        // basic input check - checks genre database:
        // if there is a match, reset new entry's genre to the existing one on db
        // if there is no match, adds as a new entry to genre db
        if (newBookEntry.getGenre().getName() == null || newBookEntry.getGenre().getName().length() < 2) {
            throw new IllegalArgumentException("Invalid genre name.");
        } else if (genreRepository.listAll()
                .stream()
                .anyMatch(n -> n.getName().trim().equalsIgnoreCase(
                        newBookEntry.getGenre().getName().toLowerCase().trim()))) {
            newBookEntry.setGenre(genreRepository.findByName(newBookEntry.getGenre().getName()));
        } else {
            genreRepository.addGenre(newBookEntry.getGenre());
        }

        return newBookEntry;
    }
}
