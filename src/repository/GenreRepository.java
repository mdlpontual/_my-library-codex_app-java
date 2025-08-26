package repository;

import model.attributes.Author;
import model.attributes.Genre;

import java.util.List;

public interface GenreRepository {
    List<Genre> listAll();

    Genre findById(Integer id);
    Genre findByTitle(String title);
}
