package org.build;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class Saver implements ISaver
{
    public static void SaveToFile(List<String> data, String prefix, String path, boolean append) {
        if(data.isEmpty())return;
        String typeof="String";
        try {
            Integer.parseInt(data.getFirst().trim());
            typeof="Integer";
        } catch (NumberFormatException ex1) {
            try {
                Double.parseDouble(data.getFirst().trim());
                typeof="Double";
            } catch (NumberFormatException ex2) {}
        }
        //создаем несуществующие папки в пути, даже если их несколько
        if(!path.isEmpty()) {
            File testPath = new File(path);
            if (!testPath.exists()) {
                System.out.println("Создание нового каталога " + testPath.getPath());
                if (!testPath.mkdirs()) throw new RuntimeException("Ошибка при создании директории результатов");
            }
            path = testPath.getPath()+"/";
        }
        try(FileOutputStream out = new FileOutputStream(path+prefix+typeof+".txt", append)){
            for(String a: data)
            {
                byte[] bytes = a.getBytes();
                out.write(bytes);
            }
        }
        catch (Exception ex){
            throw new RuntimeException("Ошибка записи.");
        }

    }
}
