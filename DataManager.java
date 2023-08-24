import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataManager {

    private List<Book> library; // private declaration only accessed within DataManager. sets library as only
                                // capable of being a list of Book objects

    public DataManager() {
        library = new ArrayList<>(); // makes an array called library
        loadLibraryData(); // calls method to load data from json file
    }

    private void loadLibraryData() {
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("library-db.json"));
            // loop through array and create a Book object for each entry
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                // extract data from JSON object beep bepp boop boop
                String title = (String) jsonObject.get("title");
                String author = (String) jsonObject.get("author");
                String genre = (String) jsonObject.get("genre");
                String starRating = (String) jsonObject.get("starRating");
                String readingProgress = (String) jsonObject.get("readingProgress");
                String status = (String) jsonObject.get("status");
                // add each new Book object filled with extracted data into the library list
                Book book = new Book(title, author, genre, status, starRating, readingProgress);
                library.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // a public method to get the library to control access to library.
    // encapsulation innit. maintains data integrity innit.
    public List<Book> getLibrary() {
        return library;
    }

    // method for saving the current state of the library data (the list of Book
    // objects) back to library-db.json. is called in other functions that ask for
    // user input
    public void saveLibraryData() {
        try {
            JSONArray jsonArray = new JSONArray();
            for (Book book : library) {
                // create JSON object for each Book in library array, populates it with
                // attributes and adds it to the JSONarray
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("title", book.getTitle());
                jsonObject.put("author", book.getAuthor());
                jsonObject.put("genre", book.getGenre());
                jsonObject.put("status", book.getStatus());
                jsonObject.put("starRating", book.getRating());
                jsonObject.put("readingProgress", book.getReadingProgress());
                jsonArray.add(jsonObject);
            }
            // write the json array into the file
            FileWriter fileWriter = new FileWriter("library-db.json");
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // search for a book by title
    public List<Book> searchBooksByTitle(String title) {
        List<Book> results = new ArrayList<>();
        for (Book book : library) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                results.add(book);
            }
        }
        return results;
    }

    // search for a book by author
    public List<Book> searchBooksByAuthor(String author) {
        List<Book> results = new ArrayList<>();
        for (Book book : library) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                results.add(book);
            }
        }
        return results;
    }

    // update the status of a book
    public void updateBookStatus(Book book, String newStatus) {
        book.setStatus(newStatus);
        saveLibraryData();
    }

    // update the reading progress of a book
    public void updateReadingProgress(Book book, String newProgress) {
        book.setReadingProgress(newProgress);
        saveLibraryData();
    }

    // leave a star rating for a book
    public void leaveStarRating(Book book, String rating) {
        book.setRating(rating);
        saveLibraryData();
    }
}
