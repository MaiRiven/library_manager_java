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

        System.out.printf("| %-40s | %-25s | %-12s | %-10s | %-4s |\n",
                "Title", "Author", "Rating", "Status", "Progress");

        for (Book book : library) {
            System.out.printf("| %-40s | %-25s | %-12s | %-10s | %-4s |\n",
                    book.getTitle(), book.getAuthor(),
                    book.getStarRating(), book.getStatus(), book.getReadingProgress());
        }
    }

    // add logic for multiple matches
    public void searchBooks(String searchString) {

        List<Book> results = dataManager.searchBooks(searchString);

        if (results.isEmpty()) {
            System.out.println("No matches found. Go buy more books.");
        } else {
            System.out.printf("| %-40s | %-25s | %-12s | %-10s | %-4s| \n",
                    "Title", "Author", "Rating", "Status", "Progress");
            for (Book book : results) {
                System.out.printf("| %-40s | %-25s | %-12s | %-10s | %-4s |\n",
                        book.getTitle(), book.getAuthor(),
                        book.getStarRating(), book.getStatus(), book.getStatus());
            }
        }
    }

    public void updateBookStatus() {
        System.out.println("Enter the title of the book you want to update: ");
        String titleInput = prompt.getStringInput();

        List<Book> matchingBooks = dataManager.searchBooksByTitle(titleInput);

        if (matchingBooks.isEmpty()) {
            System.out.println("No matching books found.");
            return;
        }
        Book selectedBook = matchingBooks.get(0);

        System.out.println("Matching book:");
        displayBookDetails(selectedBook);

        System.out.print("Is this the book you want to update?(yes / no)");
        String confirmation = prompt.getStringInput();

        if (confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Select the new reading status:");
            System.out.println("1. Unread");
            System.out.println("2. Reading");
            System.out.println("3. Finished");
            int statusChoice = prompt.getIntInput();

            switch (statusChoice) {
                case 1:
                    selectedBook.setStatus("unread");
                    break;
                case 2:
                    selectedBook.setStatus("reading");
                    break;
                case 3:
                    selectedBook.setStatus("finished");
                    selectedBook.setReadingProgress("100%");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }
            dataManager.saveLibraryData();
            System.out.println("Reading status updated!");
            displayBookDetails(selectedBook);
        } else {
            System.out.println("Update cancelled.");
        }
    }

    public void updateReadingProgress() {
        System.out.println("Enter the title of the book you want to update: ");
        String title = prompt.getStringInput();

        List<Book> matchingBooks = dataManager.searchBooksByTitle(title);

        if (matchingBooks.isEmpty()) {
            System.out.println("No matches found.");
            return;
        }

        Book bookToUpdate = matchingBooks.get(0);

        displayBookDetails(bookToUpdate);

        System.out.println("Is this the book you want to update? (yes/no)");
        String confirmation = prompt.getStringInput();

        if (confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Enter the number of pages you have read: ");
            int pagesRead = prompt.getIntInput();
            int totalPages = bookToUpdate.getPageCount();
            System.out.println(totalPages);
            double progressPercentage = ((double) pagesRead / totalPages) * 100;
            String newProgress = String.format("%.2f%%", progressPercentage);
            System.out.println(newProgress);
            bookToUpdate.setReadingProgress(newProgress);
            dataManager.updateReadingProgress(bookToUpdate, newProgress);
            System.out.println("Reading progress updated.");
            displayBookDetails(bookToUpdate);
        } else {
            System.out.println("Reading progress update canceled.");
        }
    }

    public void leaveStarRating() {
        System.out.println("Enter the title of the book you want to rate: ");
        String title = prompt.getStringInput();

        List<Book> matchingBooks = dataManager.searchBooksByTitle(title);

        if (matchingBooks.isEmpty()) {
            System.out.println("No matches found.");
            return;
        }

        Book bookToUpdate = matchingBooks.get(0);

        displayBookDetails(bookToUpdate);

        System.out.println("Is this the book you want to update? (yes/no)");
        String confirmation = prompt.getStringInput();

        if (confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Select the star rating (1-5): ");
            int rating = prompt.getIntInput();

            if (rating >= 1 && rating <= 5) {
                String starRating = getStarRatingString(rating); // Implement this method

                dataManager.leaveStarRating(bookToUpdate, starRating);
                System.out.println("Star rating updated!");
                displayBookDetails(bookToUpdate);
            } else {
                System.out.println("Invalid rating choice.");
            }
        } else {
            System.out.println("Rating update cancelled.");
        }
    }

    private void displayBookDetails(Book book) {
        System.out.println("Book Details:");
        System.out.printf("| %-40s | %-25s | %-12s | %-10s | %-4s |\n",
                "Title", "Author", "Rating", "Status", "Progress");
        System.out.printf("| %-40s | %-25s | %-12s | %-10s | %-4s |\n",
                book.getTitle(), book.getAuthor(),
                book.getStarRating(), book.getStatus(), book.getReadingProgress());

    }

    private String getStarRatingString(int rating) {
        StringBuilder starRatingBuilder = new StringBuilder();
        for (int i = 0; i < rating; i++) {
            starRatingBuilder.append("★");
        }
        for (int i = rating; i < 5; i++) {
            starRatingBuilder.append("☆");
        }
        return starRatingBuilder.toString();
    }

}
