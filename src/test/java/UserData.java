import lombok.Data;
import lombok.experimental.UtilityClass;

import java.util.Locale;
@Data

public class UserData {
    private final String name;
    private final String password;
    private final String status;
}
