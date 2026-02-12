import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Sorter implements ISorter
{
    private List<String> IntList = null;
    private List<String> DoubleList = null;
    private List<String> StringList = null;
    public void Sort(String filepath){
        try {
            List<String> input = getStrings(filepath);
            for (String a : input) {
                try {
                    Integer.parseInt(a.trim());
                    IntList.add(a);
                } catch (NumberFormatException ex1) {
                    try {
                        Float.parseFloat(a.trim());
                        DoubleList.add(a);
                    } catch (NumberFormatException ex2) {
                        StringList.add(a);
                    }
                }
            }
        }
        catch(Exception ex){

            if(ex.getMessage()==null) throw new RuntimeException("Ошибка на этапе сортировки. Перезапустите утилиту.");
            throw new RuntimeException(ex.getMessage()+" Проверьте дирректории с входными файлами.");
        }
    }

    private static List<String> getStrings(String filepath) {
        List<String> input = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(filepath)) {
            byte[] data = new byte[256];
            StringBuilder str = new StringBuilder();
            int count = 0;
            while ((count = file.read(data)) != -1) {
                for (int i = 0; i < count; i++) {
                    if ((char) data[i] == '\n') {
                        input.add(str.toString());
                        str = new StringBuilder();
                        continue;
                    }
                    str.append((char) data[i]);
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("Ошибка в работе с файлом чтения.");
        }
        return input;
    }

    public List<String> getInt(){
        return IntList;
    }
    public List<String> getDouble(){
        return DoubleList;
    }
    public List<String> getString(){
        return StringList;
    }
    public Sorter(){
        this.IntList = new ArrayList<>();
        this.DoubleList = new ArrayList<>();
        this.StringList = new ArrayList<>();
    }
}
