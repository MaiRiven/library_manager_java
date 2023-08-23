
//import Scanner
import java.util.Scanner;

//declare class, same name as file name
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner to read user input

        System.out.println("Welcome to your ~*LIBRARY*~");

        // initialise components
        Prompt prompt = new Prompt(scanner);
        DataManager dataManager = new DataManager();
        LibraryManager libraryManager = new LibraryManager();
        Art art = new Art();

        // app logic
        boolean keepRunning = true;
        while (keepRunning) {
            prompt.displayMainMenu(); // display the main menu using the prompt instance
            int choice = prompt.getIntInput(); // get the user's input choice with getIntInput from prompts
            scanner.nextLine();

            // switch for choice from input
            switch (choice) {
                case 1:
                    libraryManager.displayLibrary();
                    break;
                case 2:
                    libraryManager.searchBooksByAuthorOrTitle();
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
                    keepRunning = false; // Exit the loop and end the program
                    break;
                default:
                    System.out.println("That wasn't an option.");
            }
        }
        System.out.println("Goodbye...");
        art.closedBookArt();
    }
}