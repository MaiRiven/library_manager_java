import java.util.List;
import java.util.Scanner;

public class LibraryManager {

    private DataManager dataManager;
    private Prompt prompt;

    public LibraryManager(DataManager dataManager, Prompt prompt) {
        this.dataManager = dataManager;
        this.prompt = prompt;
    }

    public void updateBookStatus() {
        prompt.displayUpdateBookStatusPrompt();
        String title = prompt.getStringInput("Enter the title of the book: ");
        String newStatus = prompt.getStringInput("Enter the new status (unread/reading/read): ");

        Book bookToUpdate = findBookByTitle(title);
        if (bookToUpdate == null) {
            System.out.println("Book not found.");
            return;
        }

        dataManager.updateBookStatus(bookToUpdate, newStatus);
        System.out.println("Book status updated.");
    }

    // Other methods for other functionalities

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
