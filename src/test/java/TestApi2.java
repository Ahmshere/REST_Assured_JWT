import com.google.gson.JsonObject;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestApi2 {
    public TestApi2() {
        // Пустой конструктор
    }
    // https://jsonplaceholder.typicode.com/

    @Test
    public void jsonPlaceHolder(){
        // Задайте id элемента, который хотите получить
        int targetId = 1;
        Response res = given().get("https://jsonplaceholder.typicode.com/posts")
                .then().log().all().extract().response();
        System.out.println("STATUS_CODE = "+"\u001B[32m"+res.getStatusCode());
        // Получаем список всех элементов (постов) из JSON-ответа
        List<Map<String, ?>> posts = res.jsonPath().getList("");



        // Ищем элемент по id в списке постов
        Map<String, ?> targetPost = posts.stream()
                .filter(post -> post.get("id").equals(targetId))
                .findFirst()
                .orElse(null);

        if (targetPost != null) {
            // Если элемент с заданным id найден, выводим его title
            String title = targetPost.get("title").toString();
            System.out.println("Заголовок поста с id " + targetId + ": " + title);
        } else {
            System.out.println("Пост с id " + targetId + " не найден.");
        }

    }
@Test
@Description("Пример теста с REST Assured и Allure")
@Feature("Моя функциональность")
@Story("Сценарий проверки REST API")
public void chooseStatus(){
    RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    Allure.step("Выполнение GET-запроса к /todos", () -> {
        Response rStatus = given().param("completed", "true").param("id", "7")
                .get("/todos")
                .then().log().all().extract().response();
        //Assertions.assertEquals(200, rStatus.getStatusCode());
        Allure.addAttachment("Ответ от сервера", rStatus.getBody().asString());
        Assertions.assertEquals(rStatus.getHeader("X-Powered-By"), "Express");


    });

}

@Test
public void createPost(){
    RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    // JSON-тело запроса
    String requestBody = "{ \"title\": \"myTestParametr\", \"body\": \"bar\", \"userId\": 1 }";

    // Отправьте POST-запрос и получите ответ
    Response response = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(requestBody).log().all()
            .post("/posts");

    // Получите статус-код ответа
    int statusCode = response.getStatusCode();

    // Выведите тело ответа на консоль
    String responseBody = response.getBody().asString();
    System.out.println("Status Code: " +"\u001B[32m"+ statusCode);
    System.out.println("Response Body: " + responseBody);
}
    @Test
    public void getOneOfAll(){
        Response resp = given().param("id", 1)
                .get("https://jsonplaceholder.typicode.com/posts/1/comments")
                .then().log().all().extract().response();

        timeAndDate(resp);
        System.out.println("STATUS_CODE = "+"\u001B[32m"+resp.getStatusCode());
        String param_01 = resp.jsonPath().getString("email");
        System.out.println("EMAIL : " + param_01);
    }



/* https://reqres.in/ */
    @Test
    public void reqresTest_1(){
        RestAssured.baseURI = "https://reqres.in/";

        Response res = given().body("{ \"name\": \"Mike\", \"job\": \"DevOps\"}")
                .post("api/users")
                .then().log().all().extract().response();
        System.out.println("RESULT : " + res.getStatusCode());
    }
    @Test
    public void getMailById(){
        RestAssured.baseURI = "https://reqres.in/";
        Response r = given().get("api/users/1").then().log().all().extract().response();
        System.out.println("ENTITY : "+r.jsonPath().getString("data.email"));
    }
    @Test
    public void getAllMails(){
        RestAssured.baseURI = "https://reqres.in/";
        Response r = given().get("api/users").then().log().all().extract().response();
        System.out.println("===== MAIL ===== "+r.jsonPath().getString("data.email[1]"));
    }
    @Test
    public void createNewPetstorePet(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2/";
        Response response = given().contentType(ContentType.JSON).body("{\n" +
                "  \"id\": 100,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1010,\n" +
                "    \"name\": \"MegaPet\"\n" +
                "  },\n" +
                "  \"name\": \"Brantozavr\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 33333,\n" +
                "      \"name\": \"Hbz\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}").post("pet").then().log().all().extract().response();
        System.out.println("===== MEGA ID :"+response.jsonPath().getString("id"));
        Assertions.assertEquals(100, Integer.parseInt(response.jsonPath().getString("id")));
    }
    @Test
    public void createOrderToPetStore(){
        String petIdNumber = "5";
        String orderNumber = "577";
        RestAssured.baseURI = "https://petstore.swagger.io/v2/";
        Response r = given().contentType(ContentType.JSON).body("{\n" +
                "  \"id\": "+orderNumber+",\n" +
                "  \"petId\": "+petIdNumber+",\n" +
                "  \"quantity\": 1,\n" +
                "  \"shipDate\": \"2023-07-31T13:57:54.245Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true\n" +
                "}").post("store/order").then().log().all().extract().response();
        System.out.println(r.jsonPath().getString("petId"));
        Assertions.assertEquals(petIdNumber, r.jsonPath().getString("petId"));
        Response order = given().contentType(ContentType.JSON).get("store/order/"+orderNumber)
                .then().log().all().extract().response();
        System.out.println("The order number : " + order.jsonPath().getString("id"));
    }

    @Test
    public void getOrderById(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2/";
        Response r = given().get("https://petstore.swagger.io/v2/store/inventory").then().log().all().extract().response();
        System.out.println("========= "+ r);
    }
    /**
     *
     * @apiNote The helper methods
     */
    public void timeAndDate(Response r){
        long executionTimeInMillis = r.time();

        // Преобразуем время в объект Duration
        Duration duration = Duration.ofMillis(executionTimeInMillis);

        // Получаем часы, минуты, секунды
        long hours = duration.toHours();
        long minutes = duration.toMinutes();
        long seconds = duration.getSeconds();
        long millis = duration.toMillis();

        // Получаем дату и время завершения запроса
        Instant requestEndTime = Instant.now();
        Instant requestStartTime = requestEndTime.minusMillis(executionTimeInMillis);

        // Выводим результат
        System.out.println("Время выполнения запроса: " + hours + " ч " + minutes + " мин " + seconds + " с " + millis + " мс");
        System.out.println("Время начала запроса: " + requestStartTime);
        System.out.println("Время завершения запроса: " + requestEndTime);

    }


    public void testColorANSI(){
        System.out.println("Черный: \u001B[30m" + "Этот текст черным цветом" + "\u001B[0m");
        System.out.println("Красный: \u001B[31m" + "Этот текст красным цветом" + "\u001B[0m");
        System.out.println("Зеленый: \u001B[32m" + "Этот текст зеленым цветом" + "\u001B[0m");
        System.out.println("Желтый: \u001B[33m" + "Этот текст желтым цветом" + "\u001B[0m");
        System.out.println("Синий: \u001B[34m" + "Этот текст синим цветом" + "\u001B[0m");
        System.out.println("Пурпурный: \u001B[35m" + "Этот текст пурпурным цветом" + "\u001B[0m");
        System.out.println("Голубой: \u001B[36m" + "Этот текст голубым цветом" + "\u001B[0m");
        System.out.println("Белый: \u001B[37m" + "Этот текст белым цветом" + "\u001B[0m");

    }

    /**
     * @implNote resultsFolderPath - Путь к папке с результатами тестов (JSON-файлами)
     * reportFolderPath - Путь к папке, где будет создан отчет в формате HTML
     * The method for allure report generation.
     */
    public static void generateAllureReport() throws IOException, InterruptedException {
        String resultsFolderPath = "allure-results"; // Путь к папке с результатами тестов (JSON-файлами)
        String reportFolderPath = "allure-report"; // Путь к папке, где будет создан отчет в формате HTML

        String command = String.format("allure generate %s --clean -o %s", resultsFolderPath, reportFolderPath);
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();

        System.out.println("Allure report generated successfully.");
    }
}
