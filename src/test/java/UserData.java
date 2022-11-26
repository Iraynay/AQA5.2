import lombok.Data;
import lombok.experimental.UtilityClass;

import java.util.Locale;

@Data

public class UserData {
    private final String login;
    private final String password;
    private final String status;


}
