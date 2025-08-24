package repository;

import model.items.Book;

import java.util.List;

public interface BookRepository {
    List<Book> listAll();

    Book findById(Integer id);
    Book findByTitle(String title);

    void addNewBook();

    void removeById();
    void removeByTitle();

    void editTitle();
    void editAuthor();
    void editGenre();
}
