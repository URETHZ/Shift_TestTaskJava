package org.build;
import java.util.ArrayList;
import java.util.List;

import static java.lang.IO.println;
public class Start {
    static void main(String[] args) {
        try {
            println("Утилита для сортировки входных данных по их типу. -help для доп. информации");
            List<String> inputfiles = new ArrayList<>();
            String prefix = "", savepath = "./";
            boolean append = false, shortstat = false, longstat = false;
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-p": {
                        if (i + 1 < args.length) prefix = args[i + 1];
                        i++;
                        break;
                    }
                    case "-o": {
                        if (i + 1 < args.length) savepath = args[i + 1];
                        i++;
                        break;
                    }
                    case "-a": {
                        append = true;
                        break;
                    }
                    case "-s": {
                        shortstat = true;
                        break;
                    }
                    case "-f": {
                        longstat = true;
                        break;
                    }
                    case "-help": {
                        Help();
                        break;
                    }
                    default: {
                        if (args[i].endsWith(".txt")) inputfiles.add(args[i]);
                        else { println("Неизвестный аргумент: " + args[i]);}
                        break;
                    }
                }
            }
            ISorter sorter = new Sorter();
            for (String filepath : inputfiles) {
                sorter.Sort(filepath);
            }
            if (shortstat || longstat) {
                IStatistic stat = new Statistic(sorter);
                if (shortstat) stat.setStatistic("-s");
                if (longstat) stat.setStatistic("-f");
                println(stat.getStatistic());
            }
            Saver.SaveToFile(sorter.getString(), prefix, savepath, append);
            Saver.SaveToFile(sorter.getInt(), prefix, savepath, append);
            Saver.SaveToFile(sorter.getDouble(), prefix, savepath, append);
            println("Успех.");
        } catch (Exception ex) {
            println(ex.getMessage());
        }
    }

    static void Help() {
        println("----------Manual----------\n"+
                "[-p <option>] - Задание префикса имен выходных файлов. Пример: java -jar start.jar -p simple_ test.txt => " +
                "simple_Integers.txt simple_Double.txt simple_String.txt\n" +
                "[-o <option>] - Задание пути сохранения результатов\n" +
                "[-a] - Включить режим дозаписи существующих файлов\n" +
                "[-s] - Короткую статистика: количество записаных элементов\n" +
                "[-f] - Полная статитстика: короткая + минимальные/максимальные числа, среднее, сумма; Для строк макс./мин. Длина строки");
    }
}