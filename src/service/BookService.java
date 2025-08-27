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
        // checks if inputs are null
        if (newBookEntry == null || newBookEntry.getTitle() == null) {
            throw new IllegalArgumentException("book/title is required");
        } else if (newBookEntry.getAuthor() == null || newBookEntry.getAuthor().getName() == null) {
            throw new IllegalArgumentException("author/name is required");
        } else if (newBookEntry.getGenre() == null || newBookEntry.getGenre().getName() == null) {
            throw new IllegalArgumentException("genre/name is required");
        }

        // trims and updates inputs
        String title = newBookEntry.getTitle().trim();
        String authorName = newBookEntry.getAuthor().getName().trim();
        String genreName  = newBookEntry.getGenre().getName().trim();
        newBookEntry.setTitle(title);
        newBookEntry.getAuthor().setName(authorName);
        newBookEntry.getGenre().setName(genreName);

        // checks minimum input length - with trimmed values
        if (title.length() < 2) {
            throw new IllegalArgumentException("Invalid title input. Title must have at least 2 characters.");
        } else if (authorName.length() < 2) {
            throw new IllegalArgumentException("Invalid name. Author's name must have at least 2 characters");
        } else if (genreName.length() < 2) {
            throw new IllegalArgumentException("Invalid name. Genre's name must have at least 2 characters");
        }

        // checks book database:
        // if there is a match, throws error to prevent duplicates
        // if there is no match, returns new entry to proceed to repository - at the end
        if (bookRepository.findByTitle(title) != null) {
            throw new IllegalArgumentException("book with title '" + title + "' already has an entry.");
        }

        // vars prevent multiple independent method calls
        // after Book obj is validated to not run unnecessarily in case is null
        var existingAuthor = authorRepository.findByName(authorName);
        var existingGenre = genreRepository.findByName(genreName);

        // checks author database:
        // if there is a match, reset new entry's author to the existing one on db
        // if there is no match, add a new entry to author db
        if (existingAuthor != null) {
            newBookEntry.setAuthor(existingAuthor);
        } else {
            // calls repository version of addAuthor with verified input data
            authorRepository.addAuthor(newBookEntry.getAuthor());
        }

        // checks genre database:
        // if there is a match, reset new entry's genre to the existing one on db
        // if there is no match, adds as a new entry to genre db
         if (existingGenre != null) {
            newBookEntry.setGenre(existingGenre);
        } else {
             // calls repository version of addGenre with verified input data
            genreRepository.addGenre(newBookEntry.getGenre());
        }

        // calls repository version of addBook with verified input data
        bookRepository.addBook(newBookEntry);
    }
}
