import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import static java.lang.IO.*;
public class Start {
    public static void main(String[] args) throws FileNotFoundException {
        println("Утилита для сортировки входных данных по их типу.");
        List<String> inputfiles = new ArrayList<>();
        String prefix ="", savepath =new String();
        boolean append = false, shortstat=false,longstat=false;
        for(int i=0; i<args.length;i++){
            switch (args[i]){
                case "-p":
                {
                    if(i+1< args.length) prefix=args[i+1];i++;
                    break;
                }
                case "-o":
                {
                    if(i+1< args.length) savepath=args[i+1];i++;
                    break;
                }
                case "-a":
                {
                    append=true;
                    break;
                }
                case "-s":
                {
                    shortstat = true;
                    break;
                }
                case "-f":
                {
                    longstat = true;
                    break;
                }
                default:{
                    if (args[i].contains(".txt")) inputfiles.add(args[i]);
                    break;
                }
            }
        }
        ISorter sorter = new Sorter();
        for(String filepath:inputfiles){
            sorter.Sort(filepath);
        }
        Saver.SaveToFile(sorter.getString(),prefix, savepath, append);
        Saver.SaveToFile(sorter.getInt(),prefix, savepath, append);
        Saver.SaveToFile(sorter.getDouble(),prefix, savepath, append);
    }
    private static void StartSort(String path){

    }
    private static void GetStatistic(Boolean s_option, Boolean f_option){

    }
    private static void StartSave(String o_option, String p_option, Boolean a_option){

    }
}
// java -jar uXl.jar -s(-f) -a -o /path -p sample- in1.txt in2.txt