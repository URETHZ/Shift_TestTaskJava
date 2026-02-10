import java.lang.reflect.Type;
import java.util.List;

public interface ISaver {
    static void SaveToFile(List<String> data, String prefix, String path, boolean append) {}
}
