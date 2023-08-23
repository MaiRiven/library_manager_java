import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataManager {

    private List<Book> library;

    public DataManager() {
        library = new ArrayList<>();
        loadLibraryData();
    }

    private void loadLibraryData() {
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("library.json"));

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                String title = (String) jsonObject.get("title");
                String author = (String) jsonObject.get("author");
                String genre = (String) jsonObject.get("genre");
                String status = (String) jsonObject.get("status");
                String rating = (String) jsonObject.get("starRating");
                String readingProgress = (String) jsonObject.get("readingProgress");

                Book book = new Book(title, author, genre, status, rating, readingProgress);
                library.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Book> getLibrary() {
        return library;
    }

    public void saveLibraryData() {
        try {
            JSONArray jsonArray = new JSONArray();
            for (Book book : library) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("title", book.getTitle());
                jsonObject.put("author", book.getAuthor());
                jsonObject.put("genre", book.getGenre());
                jsonObject.put("status", book.getStatus());
                jsonObject.put("starRating", book.getRating());
                jsonObject.put("readingProgress", book.getReadingProgress());
                jsonArray.add(jsonObject);
            }
            FileWriter fileWriter = new FileWriter("library.json");
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
