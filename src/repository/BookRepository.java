package repository;

import model.items.Book;

import java.util.List;

public interface BookRepository {
    List<Book> listAll();
    List<Book> findById();
    List<Book> findByTitle();

    void addNewBook();

    void removeById();
    void removeByTitle();

    void editTitle();
    void editAuthor();
    void editGenre();
}
