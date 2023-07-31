import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BookApi {

    private final String baseURL = "http://34.0.64.53/api/v1/book";
    private final String token; // Здесь сохраните ваш полученный токен

    public BookApi(String token) {
        this.token = token;
    }

    public Response createNewBook(
            String author, String description, String picture, int rating, String title,
            int bookStatus, String createdAt, String id, String updatedAt, String userId) {

        String body = "{\n" +
                "  \"author\": \"" + author + "\",\n" +
                "  \"book_attrs\": {\n" +
                "    \"description\": \"" + description + "\",\n" +
                "    \"picture\": \"" + picture + "\",\n" +
                "    \"rating\": " + rating + "\n" +
                "  },\n" +
                "  \"book_status\": " + bookStatus + ",\n" +
                "  \"created_at\": \"" + createdAt + "\",\n" +
                "  \"id\": \"" + id + "\",\n" +
                "  \"title\": \"" + title + "\",\n" +
                "  \"updated_at\": \"" + updatedAt + "\",\n" +
                "  \"user_id\": \"" + userId + "\"\n" +
                "}";

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(body)
                .post(baseURL);
    }

    // Другие методы для работы с API книг могут быть добавлены здесь
}