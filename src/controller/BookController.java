package controller;

import model.attributes.Author;
import model.attributes.Genre;
import model.items.Book;
import repository.impl.SimulatedAuthorRepository;
import repository.impl.SimulatedBookRepository;
import repository.impl.SimulatedGenreRepository;
import service.BookService;
import util.DatabaseSimulator;

import java.util.List;
import java.util.Scanner;

public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public List<Book> listAll() {
        return bookService.listAll();
    }

    public void findById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert the ID you are searching for:");
        Integer idInput = scanner.nextInt();
        bookService.findById(idInput);
    }

    public void findByTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert the title of the book you are searching for:");
        String titleInput = scanner.nextLine();
        bookService.findByTitle(titleInput);
    }

    public void addBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("First, insert the title of the book you want to add to the collection: ");
        String titleInput = scanner.nextLine();

        System.out.println("Now insert the name of the author: ");
        String authorNameInput = scanner.nextLine();
        Author authorInput = new Author(authorNameInput);

        System.out.println("Insert the name of the genre to finish: ");
        String genreNameInput = scanner.nextLine();
        Genre genreInput = new Genre(genreNameInput);

        Book newBookEntry = new Book(titleInput, authorInput, genreInput);
        bookService.addBook(newBookEntry);
    }

    public void removeById() {

    }

    public void removeByTitle() {

    }
}
