import com.github.javafaker.Faker;
import com.github.javafaker.Faker.*;
import lombok.experimental.UtilityClass;

import java.util.Locale;

@UtilityClass
public class DataGeneration {
    @UtilityClass

    public static class Auth {
        public static UserData generateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new UserData(faker.name().username(),
                    faker.internet().password(),
                    faker.internet().password())
        ;}
    }

}
