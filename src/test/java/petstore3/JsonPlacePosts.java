package petstore3;

public class JsonPlacePosts {
    private Integer userID;
    private Integer id;
    private String title;
    private String body;

    public int getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = Integer.valueOf(userID);
    }

    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.valueOf(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
