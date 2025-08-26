package repository;

import model.items.Book;

import java.util.List;

public interface BookRepository {
    List<Book> listAll();

    Book findById(Integer id);
    Book findByTitle(String title);

    void addBook(Book newBookEntry);

    void removeById(Integer id);
    void removeByTitle(String title);
}
