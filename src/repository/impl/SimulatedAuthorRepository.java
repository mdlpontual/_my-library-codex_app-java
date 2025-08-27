package repository.impl;

import model.attributes.Author;
import repository.AuthorRepository;
import util.DatabaseSimulator;

import java.util.ArrayList;
import java.util.List;

public class SimulatedAuthorRepository implements AuthorRepository {

    private final DatabaseSimulator authorDatabase;

    public SimulatedAuthorRepository(DatabaseSimulator authorDatabase) {
        this.authorDatabase = authorDatabase;
    }

    @Override
    public List<Author> listAll() {
        return new ArrayList<>(authorDatabase.getAuthorList());
    }

    @Override
    public Author findById(Integer id) {
        return authorDatabase.getAuthorList()
                .stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Author findByName(String name) {
        String trimmedName = name.trim();
        return authorDatabase.getAuthorList()
                .stream()
                .filter(book -> book.getName().trim().equalsIgnoreCase(trimmedName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addAuthor(Author newAuthorEntry) {
        authorDatabase.getAuthorList().add(newAuthorEntry);
    }
}
