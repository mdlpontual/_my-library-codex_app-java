package controller;

import model.attributes.Author;
import model.attributes.Genre;
import model.items.Book;
import service.BookService;

import java.util.List;
import java.util.Scanner;

public class BookController {
    private final BookService bookService;
    Scanner scanner = new Scanner(System.in);

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public List<Book> listAll() {
        return bookService.listAll();
    }

    public Book findById() {
        System.out.println("Insert the ID you are searching for:");
        Integer idInput = scanner.nextInt();
        scanner.nextLine();
        try {
            Book b = bookService.findById(idInput);
            if (b == null) {
                System.out.println("No book found with ID " + idInput);
            }
            return b;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public Book findByTitle() {
        System.out.println("Insert the title of the book you are searching for:");
        String titleInput = scanner.nextLine();
        try {
            Book b = bookService.findByTitle(titleInput);
            if (b == null) {
                System.out.println("No book found with title " + titleInput);
            }
            return b;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public String addBook() {
        System.out.println("First, insert the title of the book you want to add to the collection: ");
        String titleInput = scanner.nextLine();

        System.out.println("Now insert the name of the author: ");
        String authorNameInput = scanner.nextLine();
        Author authorInput = new Author(authorNameInput);

        System.out.println("Insert the name of the genre to finish: ");
        String genreNameInput = scanner.nextLine();
        Genre genreInput = new Genre(genreNameInput);

        Book newBookEntry = new Book(titleInput, authorInput, genreInput);

        // the try block here don't need to take care of null because is adding,
        // therefore it doesn't have the risk of returning null from the database
        // what in the find methods is the case, here is not
        try {
            return bookService.addBook(newBookEntry);
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String removeById() {
        System.out.println("Insert the ID of the book you want to remove from the collection:");
        Integer idInput = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Are you sure you want to delete this item?");
        System.out.println("Type 0 to CONFIRM, or type 1 to CANCEL the operation:");
        int confirmInt = scanner.nextInt();
        scanner.nextLine();
        if (confirmInt == 1) {
            return "Operation canceled.";
        }
        return bookService.removeById(idInput);
    }

    public String removeByTitle() {
        System.out.println("Insert the title of the book you want to remove from the collection:");
        String titleInput = scanner.nextLine();
        System.out.println("Are you sure you want to delete this item?");
        System.out.println("Type 0 to CONFIRM, or type 1 to CANCEL the operation:");
        int confirmInt = scanner.nextInt();
        scanner.nextLine();

        if (confirmInt == 1) {
            return "Operation canceled.";
        } else if (confirmInt > 1) {
            return "Invalid value. Operation canceled.";
        }
        return bookService.removeByTitle(titleInput);
    }
}
