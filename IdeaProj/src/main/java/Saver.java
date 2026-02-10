import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Saver implements ISaver
{
    public static void SaveToFile(List<String> data, String prefix, String path, boolean append) throws FileNotFoundException {
        if(data.isEmpty())return;
        String typeof="UnknownType";
        try {
            Integer.parseInt(data.getFirst().trim());
            typeof="Integer";
        } catch (NumberFormatException ex1) {
            try {
                Double.parseDouble(data.getFirst().trim());
                typeof="Double";
            } catch (NumberFormatException ex2) {
                typeof= "String";
            }
        }
        if(path.lastIndexOf('/') != path.length() - 1) path+='/';
        try(FileOutputStream out = new FileOutputStream(path+prefix+typeof+".txt", append)){
            for(String a: data){
                byte[] bytes = a.getBytes();
                out.write(bytes);
            }
        }
        catch (IOException ex){
            throw new RuntimeException(ex + " - Ошибка записи");
        }
    }
}
