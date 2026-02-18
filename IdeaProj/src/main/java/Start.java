import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import static java.lang.IO.*;
public class Start {
    static void main(String[] args) throws FileNotFoundException {
        println("Утилита для сортировки входных данных по их типу.");
        List<String> inputfiles = new ArrayList<>();
        String prefix ="", savepath = "";
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
        IStatistic stat = new Statistic(sorter);
        if(shortstat) stat.setStatistic("-s");
        if(longstat) stat.setStatistic("-f");
        stat.getStatistic();
        Saver.SaveToFile(sorter.getString(),prefix, savepath, append);
        Saver.SaveToFile(sorter.getInt(),prefix, savepath, append);
        Saver.SaveToFile(sorter.getDouble(),prefix, savepath, append);
    }
}
// java -jar uXl.jar -s(-f) -a -o /path -p sample- in1.txt in2.txt