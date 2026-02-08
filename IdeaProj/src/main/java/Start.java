import java.util.ArrayList;
import java.util.List;
import static java.lang.String.*;
import static java.lang.IO.*;
public class Start {
    public static void main(String[] args){
        println("Утилита для сортировки входных данных по их типу.");
        List<String> Files = new ArrayList<>();
        for(String arg : args){
            if (!arg.contains(".txt")) continue;
            Files.add(arg);
        }
    }
    private static void StartSort(String path){

    }
    private static void GetStatistic(Boolean s_option, Boolean f_option){

    }
    private static void StartSave(String o_option, String p_option, Boolean a_option){

    }
}
// java -jar uXl.jar -s(-f) -a -o /path -p sample- in1.txt in2.txt