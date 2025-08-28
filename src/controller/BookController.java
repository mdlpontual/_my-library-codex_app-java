package controller;

import model.attributes.Author;
import model.attributes.Genre;
import model.items.Book;
import service.BookService;

import java.util.List;
import java.util.NoSuchElementException;
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
        int idInput;
        try {
            idInput = scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); // clear bad input
            System.out.println("Invalid ID.");
            return null;
        }
        scanner.nextLine();

        try {
            // we need to handle a null return here because we can get a null value returned from the database
            // if we look all the way back on the repository method, we can have a null value returned
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
            // we need to handle a null return here because we can get a null value returned from the database
            // if we look all the way back on the repository method, we can have a null value returned
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

        // the try block here don't need to take care of null because it is adding,
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
        // try/catch avoids crash in case the input here is non-numeric
        int idInput;
        try {
            idInput = scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            return "Invalid ID. Operation canceled.";
        }
        scanner.nextLine();

        // second confirmation before deleting entry - safety net
        System.out.println("Are you sure you want to delete this item?");
        System.out.println("Type Y to CONFIRM, or type N to CANCEL the operation:");
        String confirm = scanner.nextLine().toUpperCase().trim();

        // if the user types "Y" the code goes to the try/catch to process the operation - if not:
        if ("N".equals(confirm)) {
            return "Operation canceled.";
        } else if (!"Y".equals(confirm)) {
            return "Invalid value. Operation canceled.";
        }

        // the try block allows the trowing of the exception (in the service method) in case it fails
        // then this exception from the service is catch here, and handled accordingly
        // the "e.getMessage()" picks the message I customized in the throw from the service method
        try {
            return bookService.removeById(idInput);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String removeByTitle() {
        System.out.println("Insert the title of the book you want to remove from the collection:");
        // not need try/catch because strings normally don't fail for bad input - it accepts ints as strings after all
        String titleInput = scanner.nextLine();

        // second confirmation before deleting entry - safety net
        System.out.println("Are you sure you want to delete this item?");
        System.out.println("Type Y to CONFIRM, or type N to CANCEL the operation:");
        String confirm = scanner.nextLine().toUpperCase().trim();

        // if the user types "Y" the code goes to the try/catch to process the operation - if not:
        if ("N".equals(confirm)) {
            return "Operation canceled.";
        } else if (!"Y".equals(confirm)) {
            return "Invalid value. Operation canceled.";
        }

        // the try block allows the trowing of the exception (in the service method) in case it fails
        // then this exception from the service is catch here, and handled accordingly
        // the "e.getMessage()" picks the message I customized in the throw from the service method
        try {
            return bookService.removeByTitle(titleInput);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return "Error: " + e.getMessage();
        }
    }
}
