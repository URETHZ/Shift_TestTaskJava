import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Saver implements ISaver
{
    public static void SaveToFile(List<String> data, String prefix, String path, boolean append) {
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
        //создаем несуществующие папки в пути, даже если их несколько
        File testPath = new File(path);
        if(!testPath.exists()){
            System.out.println("Создание нового каталога "+ testPath.getPath());
            if(!testPath.mkdirs()) throw new RuntimeException("Ошибка при создании директории результатов");
        }
        try(FileOutputStream out = new FileOutputStream(testPath.getPath()+"/"+prefix+typeof+".txt", append)){
            for(String a: data){
                byte[] bytes = a.getBytes();
                out.write(bytes);
            }
        }
        catch (IOException ex){
            throw new RuntimeException("Ошибка записи.");
        }

    }
}
