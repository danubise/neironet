package ml;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vkurilo on 7/26/17.
 */
public class Neiron {
    public NeironType getNeironType() {
        return neironType;
    }

    NeironType neironType = NeironType.HIDDEN;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name = null;
    private double value = 0.0;
    private double sigmoidValue = 0.0;
    private Map<Neiron, Double> lowLevelNeironsList = new HashMap<Neiron, Double>();
    public Map<Neiron, Double> getNeirons(){
        return lowLevelNeironsList;
    }

    public Neiron addNeirons(Neiron neiron, double weight){
        System.out.println("Add weigth " + weight);
        System.out.println(this.name + " Added - " + neiron + " with weight = '" + weight + "'");
        lowLevelNeironsList.put(neiron,weight);
        return this;
    }

    public void calculateWeights(){
        this.value = 0.0;
        for (Map.Entry<Neiron, Double> entry : this.lowLevelNeironsList.entrySet() ) {
            if(entry.getKey().getNeironType() == NeironType.INPUT) {
                //System.out.println("INPUT V='" + entry.getKey().getValue() + "' W='" + entry.getValue() + "' R='" +entry.getKey().getValue() * entry.getValue() + "'");
                this.value += entry.getKey().getValue() * entry.getValue();
            }else{
                //System.out.println("HIDDEN V='" + entry.getKey().getSigmoidValue() + "' W='" + entry.getValue() + "' R='" +entry.getKey().getValue() * entry.getValue() + "'" );
                this.value += entry.getKey().getSigmoidValue() * entry.getValue();
            }
            //System.out.println(this.name + " V='" + this.value + "'");
            //System.out.printf(".");
        }
        this.sigmoidValue =1 / (1 + Math.exp(- this.value));
    }


    Neiron(String name , double value){
        this.name = name;
        this.value = value;
    }

    Neiron(){
        name="Hidden neiron";
    }

    Neiron(String name ,NeironType neironType, Neiron inputNeiron, double weight){
        this.name = name;
        addNeirons(inputNeiron, weight);
    }
    Neiron(String name){
        this.name = name;
    }
    Neiron(String name, NeironType neironType){
        this.neironType = neironType;
        this.name = name;
    }

    public Neiron setValue(double value){
        this.value = value;
        return this;
    }

    public double getValue(){
        return value;
    }

    public double getSigmoidValue(){
        return this.sigmoidValue ;
    }
    public void showNeironTree(){
        System.out.println(this.toString());
        this.showTree(this, 0);
    }
    private  void showTree(Neiron neiron, int spaceLeft){
        spaceLeft ++;

        String space = "";
        for (int i = 0; i < spaceLeft -1 ; i++) {
            space = space.concat("-");
        }
        space = space.concat("|");
        for (Map.Entry<Neiron,Double> map: neiron.getNeirons().entrySet()) {
            System.out.println(space +"> " + map.getKey());
            showTree(map.getKey(), spaceLeft);
        }
    }
    public void reCalculateWeight(){
        //System.out.println("Recalculate in progress.");
        recall(this, 0);
        //System.out.println("Recalculate is done.");
        this.calculateWeights();
    }

    private void recall(Neiron neiron , int spaceLeft){
        spaceLeft ++;

        String space = "";
        for (int i = 0; i < spaceLeft -1 ; i++) {
            space = space.concat("-");
        }
        space = space.concat("|");
        for (Map.Entry<Neiron,Double> map: neiron.getNeirons().entrySet()) {
            if(map.getKey().getNeironType() == NeironType.HIDDEN) {
                //System.out.println(space + "> " + map.getKey());
                recall(map.getKey(), spaceLeft);
                map.getKey().calculateWeights();
            }
        }
    }
    public void showWeight(){
        for (Map.Entry<Neiron, Double> neironWithWeight:this.lowLevelNeironsList.entrySet() ){
            Double weigth = neironWithWeight.getValue();
            Neiron lowNeiron = neironWithWeight.getKey();

            if(lowNeiron.getNeironType() == NeironType.INPUT){
               System.out.println(this.getName() + "->" + lowNeiron.getName() + "=" + weigth);
            }else {
                System.out.println(this.getName() + "->" + lowNeiron.getName() + "=" + weigth);
                lowNeiron.showWeight();
            }
        }
    }
    public String toString() {
        if(lowLevelNeironsList.size() > 0){
            this.calculateWeights();
            return "Neiron name = '"+ this.name +
                    "' value = '" + this.value + "'" +
                    " sigmoidValue = '"+ this.sigmoidValue + "'" +
                    " CountLowLevelNeirons = '"+lowLevelNeironsList.size()+"'";

        }
        return "Neiron name = '"+ this.name + "' value = '" + this.value + "'";
    }

    public void setErrorValue(double errorValue, double weight_delta, double learningRate){
        for (Map.Entry<Neiron, Double> neironWithWeight:this.lowLevelNeironsList.entrySet() ){
            Double weigth = neironWithWeight.getValue();
            Neiron lowNeiron = neironWithWeight.getKey();
            //System.out.println(lowNeiron.getName() + " weigth ='" + weigth + "'");
            Double weigth_new;
            if(lowNeiron.getNeironType() == NeironType.INPUT){
                weigth_new = weigth - lowNeiron.getValue() * errorValue * learningRate;
               // System.out.println(weigth+"  - " + lowNeiron.getValue() +" * "+ errorValue+" * "+ learningRate + "= " +weigth_new);
            }else {
                weigth_new =weigth - lowNeiron.getSigmoidValue() * errorValue * learningRate;
               // System.out.println(weigth+"  - " + lowNeiron.getSigmoidValue() +" * "+ errorValue+" * "+ learningRate + "= " +weigth_new);
            }

            if(lowNeiron.getNeironType() != NeironType.INPUT)
                lowNeiron.setErrorValue(weigth_new *weight_delta, weight_delta, learningRate );
           // System.out.println(lowNeiron.getName() + " weigth ='" + weigth_new + "'");
            lowLevelNeironsList.put(lowNeiron, weigth_new);
        }
        //System.out.println(lowLevelNeironsList);
    }
}
