public class Book {

    // attributes of a Book
    private String title;
    private String author;
    private String genre;
    private String status;
    private String starRating;
    private String readingProgress;
    private int pageCount;

    // constructor to initialise the Book object
    public Book(String title, String author, String genre, String status, String starRating, String readingProgress,
            int pageCount) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
        this.starRating = starRating;
        this.readingProgress = readingProgress;
        this.pageCount = pageCount;
    }

    // Getters and setters for each attribute
    // allow access and modify the Book object

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getStatus() {
        return status;
    }

    public String getStarRating() {
        return starRating;
    }

    public String getReadingProgress() {
        return readingProgress;
    }

    public int getPageCount() {
        return pageCount;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }

    public void setReadingProgress(String readingProgress) {
        this.readingProgress = readingProgress;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    // formatted string representation of a Book
    @Override
    public String toString() {
        return String.format("%-30s | %-25s | %-10s | %-6s | %-6s | %-10s",
                title, author, genre, status, starRating, readingProgress);
    }
}
