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
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                //  .body(new UserData (user.getLogin(), user.getPassword(), "active")) // передаём в теле объект, который будет преобразован в JSON
                .body(new UserData(user.getLogin(), user.getPassword(), user.getStatus()))
                .when() // "когда"
                .post("/api/system/users") // на какой путь относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    public static String getRandomLogin() {
        // TODO: добавить логику для объявления переменной login и задания её значения, для генерации
        //  случайного логина используйте faker
        String login = faker.name().username();
        return login;
    }

    public static String getRandomPassword() {
        // TODO: добавить логику для объявления переменной password и задания её значения, для генерации
        //  случайного пароля используйте faker
        String password = faker.internet().password();
        return password;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserData getUser(String status) {
            // TODO: создать пользователя user используя методы getRandomLogin(), getRandomPassword() и параметр status
            UserData user = new UserData(getRandomLogin(), getRandomPassword(), status);
            return user;
        }

        public static UserData getRegisteredUser(String status) {
            // TODO: объявить переменную registeredUser и присвоить ей значение возвращённое getUser(status).
            // Послать запрос на регистрацию пользователя с помощью вызова sendRequest(registeredUser)
            //  DataGenerator.Registration.getUser(status);
            UserData registeredUser = new UserData(getUser(status).getLogin(), getUser(status).getPassword(), getUser(status).getStatus());
            DataGenerator.sendRequest(registeredUser);

            return registeredUser;

        }
    }

}

