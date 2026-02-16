import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Sorter implements ISorter
{
    private List<Integer> IntList = null;
    private List<Float> FloatList = null;
    private List<String> StringList = null;
    public void Sort(String filepath){
        try(FileInputStream file = new FileInputStream(filepath)){
            byte[] data = new byte[256];
            StringBuilder str = new StringBuilder();
            List<String> input = new ArrayList<>();
            int count=0;
            while((count = file.read(data))!=-1)
            {
                for(int i=0; i<count;i++)
                {
                    if((char)data[i]=='\n') {
                        input.add(str.toString());
                        str = new StringBuilder();
                    }
                    str.append((char) data[i]);
                    System.out.println((char)data[i] + " - [" + data[i] + "] ");
                }
            }
            for(String a:input){
                Integer A = Integer.getInteger(a);
                if (A!=null) {IntList.add(A); continue;}


            }
        }
        catch(IOException ex)
        {
            throw new RuntimeException(ex.getMessage() + " - ошибка в работе с файлом чтения");
        }
    }
    public List<Integer> getInt(){
        return IntList;
    }
    public List<Float> getFloat(){
        return FloatList;
    }
    public List<String> getString(){
        return StringList;
    }
    public Sorter(){
        this.IntList = new ArrayList<>();
        this.FloatList = new ArrayList<>();
        this.StringList = new ArrayList<>();
    }
}
