package Models;

import java.util.ArrayList;

/**
 * Created by izaac on 1/06/2017.
 */
public class PrizeMoney
{
    QuestionNumber number = new QuestionNumber();
    private static ArrayList<Integer> values;
    private int prizeMoney;

    public void setPrizeMoney() {
       /*This method stores the prize money arraylist, so i can print the amount each question is worth*/

        values = new ArrayList<>();
        values.add(500);
        values.add(1000);
        values.add(2000);
        values.add(3000);
        values.add(5000);
        values.add(7500);
        values.add(10000);
        values.add(12500);
        values.add(15000);
        values.add(25000);
        values.add(50000);
        values.add(100000);
        values.add(250000);
        values.add(500000);
        values.add(1000000);

    }


    public Integer getScore(int index)
    {
        if (index >= 0 && index < values.size()) {
            return values.get(index);
        }
        return null;
    }

}