package Services;
import java.util.UUID;

public interface GenerateID {
    static String genID ()
    {
        return UUID.randomUUID().toString();
    }
}
