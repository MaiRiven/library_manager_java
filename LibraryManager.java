import java.util.ArrayList;
import java.util.List;

public class LibraryManager {

    Art art = new Art();

    private DataManager dataManager;
    private Prompt prompt;

    public LibraryManager(DataManager dataManager, Prompt prompt) {
        this.dataManager = dataManager;
        this.prompt = prompt;
    }

    public void displayLibrary() {
        List<Book> library = dataManager.getLibrary(); // Get the library data from DataManager
        art.bookLineArtTwo();
        System.out.printf("| %-40s | %-25s | %-10s | %-4s |\n",
                "Title", "Author", "Rating", "Status", "Progress");

        for (Book book : library) {
            System.out.printf("| %-40s | %-25s | %-10s | %-8s | %-4s |\n",
                    book.getTitle(), book.getAuthor(),
                    book.getRating(), book.getStatus(), book.getReadingProgress());
        }
    }

    public void searchBooks(String searchString) {

        List<Book> results = dataManager.searchBooks(searchString);

        if (results.isEmpty()) {
            System.out.println("No matches found. Go buy more books.");
        } else {
            art.bookLineArt();
            System.out.printf("| %-40s | %-25s | %-10s | %-10s |\n",
                    "Title", "Author", "Rating", "Status", "Progress");
            for (Book book : results) {
                System.out.printf("| %-40s | %-25s | %-10s | %-10s | %-10s\n",
                        book.getTitle(), book.getAuthor(),
                        book.getRating(), book.getStatus());
            }
        }
    }

    public void updateBookStatus() {
        displayLibrary();
        System.out.println("Enter the title of the book you want to update: ");
        String title = prompt.getStringInput();

        Book bookToUpdate = findBookByTitle(title);
        if (bookToUpdate != null) {
            System.out.println("Is this the book you want to update?");
            System.out.println("Title: " + bookToUpdate.getTitle());
            System.out.println("Author: " + bookToUpdate.getAuthor());
            System.out.print("Type 'yes' to update, 'no' to cancel: ");
            String confirmation = prompt.getStringInput();

            if (confirmation.equalsIgnoreCase("yes")) {
                System.out.println("Select the new reading status:");
                System.out.println("1. Unread");
                System.out.println("2. Reading");
                System.out.println("3. Finished");
                int statusChoice = prompt.getIntInput();

                String newStatus = "";
                switch (statusChoice) {
                    case 1:
                        newStatus = "unread";
                        break;
                    case 2:
                        newStatus = "reading";
                        break;
                    case 3:
                        newStatus = "finished";
                        bookToUpdate.setReadingProgress("100%");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        return;
                }
                dataManager.updateBookStatus(bookToUpdate, newStatus);
                System.out.println("Reading status updated!");
                System.out.printf("| %-40s | %-25s | %-10s | %-8s | %-4s |\n",
                        "Title", "Author", "Rating", "Status", "Progress");
                System.out.printf("| %-40s | %-25s | %-10s | %-8s | %-4s |\n",
                        bookToUpdate.getTitle(), bookToUpdate.getAuthor(),
                        bookToUpdate.getRating(), bookToUpdate.getStatus(), bookToUpdate.getReadingProgress());

            } else {
                System.out.println("Update cancelled");
            }
        } else {
            System.out.println("Book not found/");
        }
    }

    public void updateReadingProgress() {
        System.out.println("update reading progress func placeholder");
    }

    public void leaveStarRating() {
        System.out.println("leave star rating func placeholder");
    }

    private Book findBookByTitle(String title) {
        List<Book> library = dataManager.getLibrary();
        for (Book book : library) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}
