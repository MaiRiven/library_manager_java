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
        System.out.println("HEERRRREEE !!!!!!!!!!!!!!!!!!!!!!!******************");
        System.out.printf("| %-40s | %-25s | %-10s | %-4s |\n",
                "Title", "Author", "Rating", "Status", "Progress");

        for (Book book : library) {
            System.out.printf("| %-40s | %-25s | %-10s | %-8s | %-4s |\n",
                    book.getTitle(), book.getAuthor(),
                    book.getRating(), book.getStatus(), book.getReadingProgress());
        }
    }

    public void searchBooks(String searchString) {

        List<Book> results = new ArrayList<>();

        List<Book> titleResults = dataManager.searchBooksByTitle(searchString);
        results.addAll(titleResults);

        List<Book> authorResults = dataManager.searchBooksByAuthor(searchString);
        results.addAll(authorResults);

        if (results.isEmpty()) {
            System.out.println("No matches found. Go buy more books.");
        } else {
            art.bookLineArt();
            System.out.printf("| %-40s | %-25s | %-10s | %-10s |\n",
                    "Title", "Author", "Rating", "Status");
            for (Book book : results) {
                System.out.printf("| %-40s | %-25s | %-10s | %-10s |\n",
                        book.getTitle(), book.getAuthor(),
                        book.getRating(), book.getStatus());
            }
        }
        art.openBookArt();
    }

    public void updateBookStatus() {
        System.out.println("update book status func placeholder");
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
