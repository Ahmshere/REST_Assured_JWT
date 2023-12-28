import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class jwtTesting {

/*    @Test
    public void testJWT_All(){
        // Выполняем запрос и получаем ответ в виде Response
        Response response = given()
                .get("http://34.0.64.53/api/v1/token/new")
                .then()
                .log().all()
                .extract().response();
        // Извлекаем значение токена из JSON-ответа и сохраняем в переменную token
        String token = response.jsonPath().getString("access_token");
        // Дальше вы можете использовать переменную token по своему усмотрению
        System.out.println("Токен: " + token);

        BookApi bookApi = new BookApi(token);

        String author = "I kto eto takoi!";
        String description = "Test book create";
        String picture = "https://covers.openlibrary.org/b/id/13145492-M.jpg";
        int rating = 10;
        String title = "HahahaBook";
        int bookStatus = 1;
                String createdAt = "2023-07-27T10:46:32.427429Z";
        String id = "a97d3fdf-731c-4d71-8199-04abec304855";
        String updatedAt = "today";
        String userId = "57777777";

        Response resCreate = bookApi.createNewBook(author, description, picture, rating, title, bookStatus, createdAt, id, updatedAt, userId);
        System.out.println("Статус код: " + resCreate.getStatusCode());
        System.out.println("Ответ: " + resCreate.getBody().asString());

    }*/
/*    @Test
    public void getAllBooks() {
        Response res = given().get("http://34.0.64.53/api/v1/books").then().log().all().extract().response();
        String json = res.asString();

        // Создаем экземпляр Gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Преобразуем JSON-ответ в объект BookResponse, которые содержит список книг в свойстве "books"
        BookResponse bookResponse = gson.fromJson(json, BookResponse.class);

        // Получаем список книг из BookResponse
        List<Book> books = bookResponse.getBooks();

        // Выводим список книг в читаемом виде
        for (Book book : books) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Description: " + book.getBookAttrs().getDescription());
            System.out.println("Rating: " + book.getBookAttrs().getRating());
            System.out.println("Picture: " + book.getBookAttrs().getPicture());
            System.out.println("Book Status: " + book.getBookStatus());
            System.out.println("Created At: " + book.getCreatedAt());
            System.out.println("ID: " + book.getId());
            System.out.println("User ID: " + book.getUserId());
            System.out.println("Updated At: " + book.getUpdatedAt());
            System.out.println("-------------------------");
        }
    }*/

    // Класс для представления JSON-ответа
    private static class BookResponse {
        private List<Book> books;
        // Добавьте геттеры и сеттеры для books, если необходимо
        public List<Book> getBooks() {
            return books;
        }

        public void setBooks(List<Book> books) {
            this.books = books;
        }
    }

}