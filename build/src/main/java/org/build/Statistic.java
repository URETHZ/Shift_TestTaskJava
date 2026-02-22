package org.build;

import java.util.*;

public class Statistic implements IStatistic{
    private final ISorter sorter;
    private Integer[] sStat;
    private Integer[] IntStat;
    private Double[] DoubleStat;
    private Integer[] StringStat;
    /**
     * @brief  для сбора статистики, на вход передается вариант сортировки (-f или -s)
     * Если фалг неизвестен, создает исключение Runtime.
     */
    public void setStatistic(String option){
        if(sorter==null) return;
        try {
            switch (option)
            {
                case ("-s"): {
                    sStat=new Integer[3];
                    sStat[0]=sorter.getInt().size();
                    sStat[1]=sorter.getDouble().size();
                    sStat[2]=sorter.getString().size();
                    return;
                }
                case ("-f"): {

                    if(sStat==null){ setStatistic("-s"); }
                    //преобразуем String - коллекции для поиска min max average sum
                    List<Integer> newIntList = new ArrayList<>();
                    List<Double> newDoubleList = new ArrayList<>();
                    for (String a : sorter.getInt()) {
                        newIntList.add(Integer.parseInt(a.trim()));
                    }
                    for (String a : sorter.getDouble()) {
                        newDoubleList.add(Double.parseDouble(a.trim()));
                    }
                    //Integer : min max sum average
                    if(!newIntList.isEmpty()){
                        IntStat=new Integer[4];
                        IntStat[0] = Collections.min(newIntList);
                        IntStat[1] = Collections.max(newIntList);
                        int sum1 = 0;
                        for (int num : newIntList) {
                            sum1 += num;
                        }
                        //сумма int
                        IntStat[2] = sum1;
                        //среднее int
                        IntStat[3] = sum1 / newIntList.size();
                    }
                    if(!newDoubleList.isEmpty()){
                        //Double
                        DoubleStat=new Double[4];
                        DoubleStat[0] = Collections.min(newDoubleList);
                        DoubleStat[1] = Collections.max(newDoubleList);
                        double sum2 = 0;
                        for (double num : newDoubleList) {
                            sum2 += num;
                        }
                        DoubleStat[2] = sum2;
                        DoubleStat[3] = sum2 / newDoubleList.size();
                    }
                    if(!newDoubleList.isEmpty()){
                        //String
                        StringStat=new Integer[2];
                        // Строка с максимальной длиной
                        int maxStr = Collections.max(sorter.getString(),
                                Comparator.comparingInt(String::length)).length();
                        // Минимальная длина
                        int minStr = Collections.min(sorter.getString(),
                                Comparator.comparingInt(String::length)).length();
                        StringStat[0] = minStr;
                        StringStat[1] = maxStr;
                    }
                    return;
                }
                default: {
                    throw new RuntimeException("Неизвестный флаг статистики.");
                }
            }
        }
        catch(Exception ex){
            throw new RuntimeException(ex.getMessage()+"Ошибка на этапе сбора статистики. Перезапустите утилиту.");
        }
    }
    public Statistic(ISorter sorter){
        this.sorter=sorter;
    }
    /**
     * @brief Метод для получения результата сбора статистики в виде строки.
     * Сначала необходимо собрать статистику методом setStatistic().
     * @return Строка-результат сбора статистики. Содержит уже готовую к выводу информацию
     */
    public String getStatistic() {
        String str="";
        if(sStat!=null){
            str += "Добавлено Integers: " + sStat[0] +
                    ", Double: " + sStat[1] +
                    ", String: " + sStat[2] + "\n";
        }
        if(IntStat!=null) {
            str+="Integer   Мин. значение: " + IntStat[0]
                + ", Макс. знач: " + IntStat[1] + ", сумма: " + IntStat[2]
                + ", среднее: " + IntStat[3]+"\n";
        }
        if(DoubleStat!=null){
            str+="Double    Мин. значение: " + DoubleStat[0]
                    + ", Макс. знач: " + DoubleStat[1] + ", сумма: " + DoubleStat[2]
                    + ", среднее: " + DoubleStat[3]+"\n";
        }
        if(StringStat!=null){
            str+="String    Мин. длина строки: " + StringStat[0]
                    + ", Макс. длина: " + StringStat[1]+"\n";
        }
        return str;
    }
}
