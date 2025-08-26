package repository;

import model.attributes.Author;

import java.util.List;

public interface AuthorRepository {
    List<Author> listAll();

    Author findById(Integer id);
    Author findByName(String title);
}
