import java.util.*;

public class Statistic implements IStatistic{
    private final ISorter sorter;
    private List<Integer> sStat;
    private List<Double> fStat;
    public void setStatistic(String option){
        try {
            switch (option) {
                case ("-s"): {
                    sStat.add(sorter.getInt().size());
                    sStat.add(sorter.getDouble().size());
                    sStat.add(sorter.getString().size());
                    return;
                }
                case ("-f"): {
                    setStatistic("-s");
                    //преобразуем String - коллекции
                    List<Integer> newIntList = new ArrayList<>();
                    List<Double> newDoubleList = new ArrayList<>();
                    for (String a : sorter.getInt()) {
                        //гарантируем что преобразование пройдет.
                        newIntList.add(Integer.parseInt(a.trim()));
                    }
                    for (String a : sorter.getDouble()) {
                        //гарантируем что преобразование пройдет.
                        newDoubleList.add(Double.parseDouble(a.trim()));
                    }
                    //min max
                    fStat.add(Collections.min(newIntList).doubleValue());
                    fStat.add(Collections.max(newIntList).doubleValue());
                    double sum = 0;
                    for (int num : newIntList) {
                        sum += num;
                    }
                    //сумма int
                    fStat.add(sum);
                    //среднее int
                    fStat.add(sum / newIntList.size());
                    //Double
                    fStat.add(Collections.min(newDoubleList));
                    fStat.add(Collections.max(newDoubleList));
                    sum = 0;
                    for (double num : newDoubleList) {
                        sum += num;
                    }
                    fStat.add(sum);
                    fStat.add(sum / newDoubleList.size());
                    //String
                    // Строка с максимальной длиной
                    double maxStr = Collections.max(sorter.getString(),
                            Comparator.comparingInt(String::length)).length();
                    // Минимальная длина
                    double minStr = Collections.min(sorter.getString(),
                            Comparator.comparingInt(String::length)).length();
                    fStat.add(minStr);
                    fStat.add(maxStr);
                    return;
                }
                default: {
                }
            }
        }
        catch(Exception ex){
            throw new RuntimeException("Ошибка на этапе сбора статистики. Перезапустите утилиту.");
        }
    }
    public void getStatistic(){
        System.out.println(this.toString());
    }

    public Statistic(ISorter sorter){
        this.sorter=sorter;
        fStat=new ArrayList<>();
        sStat=new ArrayList<>();
    }
    @Override
    public String toString() {
        String str="";
        if(!sStat.isEmpty()){
            str += "Добавлено Integers: " + sStat.get(0) +
                    ", Double: " + sStat.get(1) +
                    ", String: " + sStat.get(2) + "\n";
        }
        if(!fStat.isEmpty()) {
            str += "Integer   Мин. значение: " + fStat.getFirst().intValue()
            + ", Макс. знач: " + fStat.get(1).intValue() + ", сумма: " + fStat.get(2).intValue()
            + ", среднее: " + fStat.get(3) + "\nDouble    Мин. значение: " + fStat.get(4)
                    + ", Макс. знач: " + fStat.get(5) + ", сумма: " + fStat.get(6)
                    + ", среднее: " + fStat.get(7) + "\nString    Мин. длин. строки: "
            + fStat.get(8) + ", Макс.: " + fStat.get(9);
        }
        return str;
    }

}
