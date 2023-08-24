import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // initialise components
        Prompt prompt = new Prompt(scanner);
        DataManager dataManager = new DataManager();
        LibraryManager libraryManager = new LibraryManager(dataManager, prompt);
        Art art = new Art();

        System.out.println("\nWelcome to your ~*LIBRARY*~");
        art.bookshelfArt();

        boolean keepRunning = true;
        while (keepRunning) {
            prompt.displayMainMenu();
            int choice = prompt.getIntInput();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    libraryManager.displayLibrary();
                    break;
                case 2:
                    System.out.println("What are you looking for?");
                    String searchString = prompt.getStringInput();
                    libraryManager.searchBooks(searchString);
                    break;
                case 3:
                    libraryManager.updateBookStatus();
                    break;
                case 4:
                    libraryManager.updateReadingProgress();
                    break;
                case 5:
                    libraryManager.leaveStarRating();
                    break;
                case 6:
                    keepRunning = false;
                    break;
                default:
                    System.out.println("That wasn't an option silly.");
            }
        }
        System.out.println("Goodbye...\n");
        art.closedBookArt();
    }
}