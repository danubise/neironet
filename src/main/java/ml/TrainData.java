package ml;

import java.util.ArrayList;

/**
 * Created by vkurilo on 7/27/17.
 */
public class TrainData {
    ArrayList<Double> vodka = new ArrayList<Double>();
    ArrayList<Double> rain = new ArrayList<Double>();
    ArrayList<Double> friend = new ArrayList<Double>();
    ArrayList<Double> zont = new ArrayList<Double>();
    ArrayList<Double> result = new ArrayList<Double>();
    ArrayList<Double> probka = new ArrayList<Double>();

    public int getValuesCoount() {
        return valuesCoount;
    }

    private int valuesCoount =0;
    public void addValue(double v1, double v2 , double v3,double v4,double v5, double r1){
        vodka.add(v1);
        rain.add(v2);
        friend.add(v3);
        zont.add(v4);
        probka.add(v5);
        result.add(r1);
        this.valuesCoount ++;
    }
    public ArrayList<Double> getByIndex(int index){
        ArrayList<Double> doubles = new ArrayList<Double>();
        doubles.add(0, result.get(index));
        doubles.add(1, vodka.get(index));
        doubles.add(2, rain.get(index));
        doubles.add(3, friend.get(index));
        doubles.add(4, zont.get(index));
        doubles.add(5, probka.get(index));



        return doubles;
    }
}
