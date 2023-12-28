package petstore3;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class PetStore3Tests {

    String URL_JSON_PLACEHOLDER = "https://jsonplaceholder.typicode.com/";
/*    @Test
    public void newPost(){
        RestAssured.baseURI=URL_JSON_PLACEHOLDER;
        int userID = 313, id= 51;
        String title="medatitle", body="eto tipa bodyyyyy";
        String jsonBody = String.format("{\n" +
                "    \"userId\": %d,\n" +
                "    \"id\": %d,\n" +
                "    \"title\": \"%s\",\n" + // Заменяем строку на значение переменной title
                "    \"body\": \"%s\"\n" +
                "  }", userID, id, title, body);

      *//*  Response rPost = given().contentType(ContentType.JSON)
                .body(jsonBody).post("posts")
                .then().log().all().extract().response();*//*
JsonPlacePosts posts = given().contentType(ContentType.JSON)
        .body(jsonBody).post("posts")
        .then().log().all().extract().response().as(JsonPlacePosts.class);



        //System.out.println("+++++ RESPONSE : "+rPost.jsonPath().getString("title"));
        System.out.println("RESULT : " +posts.getBody());
    }*/
    @Test
            public void testPlaceHolder(){
        RestAssured.baseURI = URL_JSON_PLACEHOLDER;
        Response resp = given().contentType(ContentType.JSON).param("id",5)
                .get("users")
                .then().log().all().extract().response();
        System.out.println("RESPONSE : " +resp.jsonPath().getString("email"));
    }

    String URL_PETSTORE3 = "https://petstore3.swagger.io/api/v3/";
        @Test
        public void loginUser(){
            RestAssured.baseURI = URL_PETSTORE3;
            Response r = given().contentType(ContentType.JSON)
                    .param("username","mike")
                    .param("password","ajynhf6565").get("user/login")
                    .then().log().all().extract().response();
            System.out.println("----- Parametr : calls per hour allowed by the user : " + r.header("x-rate-limit"));
            System.out.println("----- Parametr : date in UTC when token expires : " + r.header("x-expires-after"));

        }


    @JsonIgnoreProperties(ignoreUnknown = true)
        @Test
        public void createUser(){
            // Accessable just for subscribed users.
            RestAssured.baseURI = URL_PETSTORE3;
            String id = "107", userName = "Burbulyator", firstName = "John", lastName = "Conorovich", email = "jconorterminatorovich@blabla.bla";
            Response res = given().contentType(ContentType.JSON).body("{\n" +
                            "  \"id\": "+id+",\n" +
                            "  \"username\": \""+userName+"\",\n" +
                            "  \"firstName\": \""+firstName+"\",\n" +
                            "  \"lastName\": \""+lastName+"\",\n" +
                            "  \"email\": \""+email+"\",\n" +
                            "  \"password\": \"12345\",\n" +
                            "  \"phone\": \"12345\",\n" +
                            "  \"userStatus\": 1\n" +
                            "}")
                    .post("user").then().log().all().extract().response();
            System.out.println("*** USER_NAME : "+res.jsonPath().getString("username"));
            Assertions.assertEquals(200,res.getStatusCode());
        }
/*        @Test
        public void checkUserById(){
            RestAssured.baseURI = URL_PETSTORE3;

            Response r = given().get("user/Burbulyator").then().log().all().extract().response();
            if (r != null) {
                if (r.getBody() != null) {

                    String responseBody = r.getBody().asString();
                    if (responseBody != null && !responseBody.isEmpty()) {
                        System.out.println("ENTITY : "+r.jsonPath().getString("id"));
                    } else {
                        System.out.println("Ответ пустой.");
                    }
                } else {
                    System.out.println("Тело ответа пустое.");
                }
            } else {
                System.out.println("Ответ вернулся как null.");
            }

        }*/
        @Test
        public void createPet() {
            RestAssured.baseURI = URL_PETSTORE3;
            String petId = "701", name = "Chuvachello";
            Response res = given().contentType(ContentType.JSON).body("{\n" +
                    "  \"id\": " + petId + ",\n" +
                    "  \"name\": \"" + name + "\",\n" +
                    "  \"category\": {\n" +
                    "    \"id\": 1,\n" +
                    "    \"name\": \"Dogs\"\n" +
                    "  },\n" +
                    "  \"photoUrls\": [\n" +
                    "    \"string\"\n" +
                    "  ],\n" +
                    "  \"tags\": [\n" +
                    "    {\n" +
                    "      \"id\": 0,\n" +
                    "      \"name\": \"string\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"status\": \"available\"\n" +
                    "}").post("pet").then().log().all().extract().response();
            // Создаю пользователя и проверяю что он существует по ИМЕНИ
            System.out.println("===== NAME ===== " + res.jsonPath().getString("name"));
            Assertions.assertEquals(name, res.jsonPath().getString("name"));
        }
// Генерация рандомного мейла
    public static String generateEmail(int xLength, int yLength, int zLength) {
        if (xLength <= 0 || yLength <= 0 || zLength <= 0) {
            throw new IllegalArgumentException("Длины x, y и z должны быть больше нуля.");
        }

        String xPart = generateRandomString(xLength);
        String yPart = generateRandomString(yLength);
        String zPart = generateRandomString(zLength);

        return xPart + "@" + yPart + "." + zPart;
    }

    /**
     * The method generates a string of a certain length according to the "length" parameter.
     * @param length - must be > 0
     * @return Generated string
     */
    private static String generateRandomString(int length) {
        String allowedCharacters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            char randomChar = allowedCharacters.charAt(random.nextInt(allowedCharacters.length()));
            randomString.append(randomChar);
        }

        return randomString.toString();
    }

}
