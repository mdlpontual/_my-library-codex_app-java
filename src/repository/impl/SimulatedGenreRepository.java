package repository.impl;

import model.attributes.Genre;
import repository.GenreRepository;
import util.DatabaseSimulator;

import java.util.ArrayList;
import java.util.List;

public class SimulatedGenreRepository implements GenreRepository {

    private final DatabaseSimulator genreDatabase;

    public SimulatedGenreRepository(DatabaseSimulator genreDatabase) {
        this.genreDatabase = genreDatabase;
    }

    @Override
    public List<Genre> listAll() {
        return new ArrayList<>(genreDatabase.getGenreList());
    }

    @Override
    public Genre findById(Integer id) {
        return genreDatabase.getGenreList()
                .stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Genre findByTitle(String title) {
        return genreDatabase.getGenreList()
                .stream()
                .filter(book -> book.getName().equals(title))
                .findFirst()
                .orElse(null);
    }
}
