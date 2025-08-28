package view;

import controller.BookController;
import model.items.Book;
import repository.BookRepository;
import repository.impl.SimulatedAuthorRepository;
import repository.impl.SimulatedBookRepository;
import repository.impl.SimulatedGenreRepository;
import service.BookService;
import util.DatabaseSimulator;

import java.util.List;
import java.util.Scanner;

public class NavigationMenu {

    private final DatabaseSimulator database = new DatabaseSimulator();
    private final SimulatedBookRepository bookRepository = new SimulatedBookRepository(database);
    private final SimulatedAuthorRepository authorRepository = new SimulatedAuthorRepository(database);
    private final SimulatedGenreRepository genreRepository = new SimulatedGenreRepository(database);
    private final BookService service = new BookService(bookRepository, authorRepository, genreRepository);
    private final BookController controller = new BookController(service);

    Scanner in = new Scanner(System.in);

    /** Call this to start the loop */
    public void start() {
        while (true) {
            printMenu();
            String choice = in.nextLine().trim();

            switch (choice) {
                case "1": // list all
                    List<Book> books = controller.listAll();
                    if (books.isEmpty()) {
                        System.out.println("No books in the collection.");
                    } else {
                        System.out.println("— All Books —");
                        for (Book b : books) {
                            printBook(b);
                        }
                    }
                    pause();
                    break;

                case "2": // find by id
                    Book byId = controller.findById(); // controller handles input + messages
                    if (byId != null) {
                        System.out.println("Result:");
                        printBook(byId);
                    }
                    pause();
                    break;

                case "3": // find by title
                    Book byTitle = controller.findByTitle(); // controller handles input + messages
                    if (byTitle != null) {
                        System.out.println("Result:");
                        printBook(byTitle);
                    }
                    pause();
                    break;

                case "4": // add
                    String addMsg = controller.addBook(); // controller handles input + validation
                    System.out.println(addMsg);
                    pause();
                    break;

                case "5": // remove by id
                    String delIdMsg = controller.removeById(); // controller handles input + confirm
                    System.out.println(delIdMsg);
                    pause();
                    break;

                case "6": // remove by title
                    String delTitleMsg = controller.removeByTitle(); // controller handles input + confirm
                    System.out.println(delTitleMsg);
                    pause();
                    break;

                case "0": // exit
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
                    pause();
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("===== Library Menu =====");
        System.out.println("1) List all books");
        System.out.println("2) Find book by ID");
        System.out.println("3) Find book by title");
        System.out.println("4) Add a new book");
        System.out.println("5) Remove a book by ID");
        System.out.println("6) Remove a book by title");
        System.out.println("0) Exit");
        System.out.print("Choose an option: ");
    }

    private void printBook(Book b) {
        System.out.println("------------------------");
        System.out.println("ID:     " + b.getId());
        System.out.println("Title:  " + b.getTitle());
        System.out.println("Author: " + (b.getAuthor() != null ? b.getAuthor().getName() : "(none)"));
        System.out.println("Genre:  " + (b.getGenre()  != null ? b.getGenre().getName()  : "(none)"));
    }

    private void pause() {
        System.out.println();
        System.out.print("Press Enter to continue...");
        in.nextLine();
    }
}
