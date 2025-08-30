# My Library Codex (CLI)

**Terminal-first library manager.** A compact Java CLI to add, find, list, and remove books with a simple in-memory store and clear, guided prompts.


> **Note:** All actions involving **IDs** are **inactive for now** (temporarily disabled). Use title-based actions to test.

**Version:** `v0.1.0`

## Features

* 📚 Add books (Title, Author, Genre)
* 🔎 Find by **Title**
* 🗂️ List all
* ❌ Remove by **Title** (with confirmation)
* 🧪 In-memory storage via `DatabaseSimulator`
* ⚠️ **ID-based actions (find/remove by ID) are currently inactive**

## Tech Stack

* Java 17+ (no external deps)
* Plain OOP layers: **model → repository → service → controller → view**

## Project Structure

```
src/
 ├─ controller/
 │   └─ BookController.java
 ├─ model/
 │   ├─ CatalogItem.java
 │   ├─ CatalogAttribute.java
 │   ├─ attributes/
 │   │   ├─ Author.java
 │   │   └─ Genre.java
 │   └─ items/
 │       └─ Book.java
 ├─ repository/
 │   ├─ BookRepository.java
 │   ├─ AuthorRepository.java
 │   ├─ GenreRepository.java
 │   └─ impl/
 │       ├─ SimulatedBookRepository.java
 │       ├─ SimulatedAuthorRepository.java
 │       └─ SimulatedGenreRepository.java
 ├─ service/
 │   └─ BookService.java
 ├─ util/
 │   └─ DatabaseSimulator.java
 └─ view/
     └─ NavigationMenu.java
```

## Getting Started

### Prerequisites

* Install **JDK 17+**
* Recommended: IntelliJ IDEA (Community) or VS Code + Java Extension Pack

### Run (IDE – easiest)

1. Create a small `Main` that wires:

    * `DatabaseSimulator` → repositories → `BookService` → `BookController` → `NavigationMenu`
2. Call `new NavigationMenu(controller).start();`
3. Run `Main` from your IDE.

## Usage

You’ll see a simple menu like:

```
===== Library Menu =====
1) List all books
2) Find book by ID        [INACTIVE]
3) Find book by title
4) Add a new book
5) Remove a book by ID    [INACTIVE]
6) Remove a book by title
0) Exit
Choose an option:
```

## Notes

* **IDs** are assigned in repositories when adding new entities (backed by `DatabaseSimulator`’s in-memory lists), but **ID-based operations are temporarily disabled** in the current UI flow.
* The **service** layer validates inputs and throws clear messages; the controller catches them and prints friendly text.
* The CLI uses a single `Scanner` and confirmation prompts (Y/N or 0/1) to avoid accidental deletes.

Here’s the end-of-README section, keeping your structure and separating technical items:

## Next Steps - Release Plan:

### Next Patch

* **v0.1.1 — Integrate ID-based actions** (enable find/remove by ID in the menu).

### Future Implementations

* **Other media types:** magazines, CDs, vinyls, DVDs, Blu-ray, and videogames.
* **More attributes & characteristics:** media type, language, year of release, edition, publishers, digital vs. physical, and media platform.
* **More actions:** edit/update values, improved search tools, etc.

### Technical Roadmap (Stack Upgrades)

* **Persistence:** PostgreSQL with JDBC.
* **ORM & JPA:** Hibernate/JPA entities and relationships; Spring Data JPA repositories.
* **Spring Boot API:** REST controllers, global exception handling, environment profiles; API docs with OpenAPI/Swagger.
...and more!

