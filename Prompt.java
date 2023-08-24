import java.util.Scanner;

public class Prompt {

    private Scanner scanner; // declare a scanner field. a variable that belongs to a class

    public Prompt(Scanner scanner) {
        this.scanner = scanner;
    }

    // display main menu options
    public void displayMainMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1. View Library");
        System.out.println("2. Search Books");
        System.out.println("3. Update Book Status");
        System.out.println("4. Update Reading Progress");
        System.out.println("5. Leave a Star Rating");
        System.out.println("6. Leave the Library");
    }

    public int getIntInput() {
        System.out.print(": "); // prompt for input
        return scanner.nextInt(); // read and return integer input
    }

    public void printPrompts() {
        System.out.println("Prompts class is working!");
    }

    public String getStringInput() {
        // System.out.println("Enter: ");
        return scanner.nextLine();
    }

}