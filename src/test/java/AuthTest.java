import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


public class AuthTest {

    static String name1 = DataGeneration.Auth.generateInfo("gb").getName();
    static String password1 = DataGeneration.Auth.generateInfo("ru").getPassword();
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    @Test

    void setUpAll() {
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new UserData (name1, password1, "active")) // передаём в теле объект, который будет преобразован в JSON

                .when() // "когда"
                .post("/api/system/users") // на какой путь относительно BaseUri отправляем запрос

                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }


    }

