package ml;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vkurilo on 7/27/17.
 */
public class Training {
    int epoch =62000;
    double learning_rate = 0.501;
    Network network = null;
    Training(Network network){
        this.network = network;
    }
    Map<String, ArrayList<Double>> testData = new HashMap<String, ArrayList<Double>>();
    ArrayList<ArrayList<Double>> testData1 = new ArrayList<ArrayList<Double>>();
    TrainData trainData = new TrainData();
    Training(){
                        //vodka,rain,friend,zont
        trainData.addValue(0,0,0, 0,0,0);
        trainData.addValue(0,0,1, 0,0,1);
        trainData.addValue(0,1,0, 0,0,0);
        trainData.addValue(0,1,0, 1,0,0);
        trainData.addValue(1,0,0, 1,0,1);
        trainData.addValue(1,0,1, 1,1,1);
        trainData.addValue(1,1,0, 0,1,0);
        trainData.addValue(1,1,0, 1,0,1);
        trainData.addValue(1,1,1, 0,1,0);
        trainData.addValue(1,1,1, 1,0,1);
        //                      1   1       0      1   1
        //trainData.addValue(1,1,1,1,0,1);
        //trainData.addValue(1,1,1,0,0,0);
    }
    public void setNetwork(Network network){
        this.network = network;
    }

    public void test(){
        //int len = testData1.get(0).size();
        NeironNetworkValue inputValue = null;
        double errorV = 0;
        int c =0;
        for (int j = 0; j < epoch; j++) {
            //System.out.println(trainData.getValuesCoount());
            for (int i = 0; i < trainData.getValuesCoount(); i++) {
                inputValue = new NeironNetworkValue();
                ArrayList<Double> doubles = trainData.getByIndex(i);
                inputValue.setValues("Vodka", doubles.get(1));
                inputValue.setValues("Rain", doubles.get(2));
                inputValue.setValues("Friend", doubles.get(3));
                inputValue.setValues("Zont", doubles.get(4));
                inputValue.setValues("Probka", doubles.get(5));
                inputValue.setResultValue(doubles.get(0));
                this.network.setInputNeironsValue(inputValue);
                this.network.calculate();
                double exitV = this.network.getExitValue();
                errorV = exitV - inputValue.getResultValue();
                double weight_delta = errorV * exitV;
                //System.out.println(inputValue + " ==> " + exitV + " error = " + errorV);
                //System.out.println("weight_delta ='" + weight_delta + "'" + "sig/exitv ='" + exitV + "'");
                network.setErrorValue(errorV, weight_delta, learning_rate);
            }
            c++;
           if(c==100) {
               System.out.printf("\r " + j + " --> " + String.format("%.06f",errorV));
               c=0;
           }
        }
        System.out.println("\n" );
        for (int i = 0; i < 8; i++) {
            inputValue = new NeironNetworkValue();
            ArrayList<Double> doubles = trainData.getByIndex(i);
            inputValue.setValues("Vodka", doubles.get(1));
            inputValue.setValues("Rain", doubles.get(2));
            inputValue.setValues("Friend", doubles.get(3));
            inputValue.setValues("Zont", doubles.get(4));
            inputValue.setValues("Probka", doubles.get(5));

            inputValue.setResultValue(doubles.get(0));
            this.network.setInputNeironsValue(inputValue);
            this.network.calculate();
            double exitV = this.network.getExitValue();
            errorV = exitV - inputValue.getResultValue();
            double weight_delta = errorV * exitV;
            System.out.println( inputValue + " ==> " + String.format("%.04f",exitV)  + " error = " +  String.format("%.04f",errorV) );
            //System.out.println("weight_delta ='" + weight_delta + "'" + "sig/exitv ='" + exitV + "'");
            //network.setErrorValue(errorV, weight_delta, learning_rate);
        }
        this.network.showWeight();
        testInput();
    }
    public void testInput(){
        NeironNetworkValue neironNetworkValue = new NeironNetworkValue();

        try{
            BufferedReader bufferRead = null;
            String inputString = null;
            do {
                System.out.println("Vodka: ");
                bufferRead = new BufferedReader(new InputStreamReader(System.in));
                inputString = bufferRead.readLine();

                System.out.println("Vodka entered : " + inputString);
                neironNetworkValue.setValues("Vodka",Double.valueOf(inputString));
                System.out.println("Rain: ");
                bufferRead = new BufferedReader(new InputStreamReader(System.in));
                inputString = bufferRead.readLine();

                System.out.println("Rain entered : " + inputString);
                neironNetworkValue.setValues("Rain",Double.valueOf(inputString));

                System.out.println("Friend: ");
                bufferRead = new BufferedReader(new InputStreamReader(System.in));
                inputString = bufferRead.readLine();

                System.out.println("Friend entered : " + inputString);
                neironNetworkValue.setValues("Friend",Double.valueOf(inputString));
                System.out.println("Zont: ");
                bufferRead = new BufferedReader(new InputStreamReader(System.in));
                inputString = bufferRead.readLine();

                System.out.println("Zont entered : " + inputString);
                neironNetworkValue.setValues("Zont",Double.valueOf(inputString));


                System.out.println("Probka: ");
                bufferRead = new BufferedReader(new InputStreamReader(System.in));
                inputString = bufferRead.readLine();

                System.out.println("Probka entered : " + inputString);
                neironNetworkValue.setValues("Probka",Double.valueOf(inputString));

                this.network.setInputNeironsValue(neironNetworkValue);
                this.network.calculate();
                System.out.println(String.format("%.04f",this.network.getExitValue()));

                System.out.println("One more?: Y/n");
                bufferRead = new BufferedReader(new InputStreamReader(System.in));
                inputString = bufferRead.readLine();
            }while (inputString.equals("Y"));


        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }


    public void train(){
        double network_result = 0;
        for (int i = 0; i < epoch; i++) {

          //  network_result = this.network.getValue();
        }
    }

    private void networkSetIbputValues(){

    }
}
