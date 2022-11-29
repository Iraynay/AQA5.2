import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.experimental.UtilityClass;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.Locale;


public class DataGenerator {

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private static final Faker faker = new Faker(new Locale("en"));

    private DataGenerator() {
    }

    private static void sendRequest(UserData user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static String getRandomLogin() {

        String login = faker.name().username();
        return login;
    }

    public static String getRandomPassword() {

        String password = faker.internet().password();
        return password;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserData getUser(String status) {
            UserData user = new UserData(getRandomLogin(), getRandomPassword(), status);
            return user;
        }

        public static UserData getRegisteredUser(String status) {
            UserData registeredUser = getUser(status);
            DataGenerator.sendRequest(registeredUser);

            return registeredUser;

        }
    }

}


