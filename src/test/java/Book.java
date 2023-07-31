import com.google.gson.annotations.SerializedName;

public class Book {
    private String author;

    @SerializedName("book_attrs")
    private BookAttrs bookAttrs;

    @SerializedName("book_status")
    private int bookStatus;

    @SerializedName("created_at")
    private String createdAt;

    private String id;
    private String title;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("user_id")
    private String userId;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookAttrs getBookAttrs() {
        return bookAttrs;
    }

    public void setBookAttrs(BookAttrs bookAttrs) {
        this.bookAttrs = bookAttrs;
    }

    public int getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(int bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
