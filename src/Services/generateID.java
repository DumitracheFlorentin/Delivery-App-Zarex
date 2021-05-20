package Services;
import java.util.UUID;

public interface generateID {
    static String genID ()
    {
        return UUID.randomUUID().toString();
    }
}
