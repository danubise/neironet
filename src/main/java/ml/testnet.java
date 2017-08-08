package ml;

/**
 * Created by vkurilo on 7/25/17.
 */
public class testnet {
    public static void main(String[] args) {
        Neiron neiron = new Neiron("Vodka" , 1.0);
        Neiron neiron1 = new Neiron("Rain" , 1.0);
        Neiron neiron2 = new Neiron("Friend", 0.0);
       // System.out.println(neiron.toString());
        Neiron hidden1 = new Neiron("Bad decision", NeironType.HIDDEN);
        hidden1.addNeirons(neiron, 0.5);
        hidden1.addNeirons(neiron1,0.5);
        hidden1.addNeirons(neiron2, 0.0);
        Neiron hidden2 = new Neiron("Ok decision", NeironType.HIDDEN);
        hidden2.addNeirons(neiron, 0.01);
        hidden2.addNeirons(neiron1,0.01);
        hidden2.addNeirons(neiron2, 0.01);
        System.out.println(hidden1);
        System.out.println(hidden2);
        System.out.println("============ Result ================");
        Neiron exitNeiron = new Neiron("Exit");
        exitNeiron.addNeirons(hidden1,0.2);
        exitNeiron.addNeirons(hidden2, 0.52);
        System.out.println(exitNeiron);




        System.out.println("===========================");
        exitNeiron.showNeironTree();
        System.out.println("===========================");
        System.out.println(exitNeiron.toString());
        neiron1.setValue(0.0);
        exitNeiron.reCalculateWeight();
        System.out.println(exitNeiron.toString());
        neiron.setValue(1.0);
        neiron2.setValue(1.0);
        exitNeiron.reCalculateWeight();
        System.out.println(exitNeiron.toString());
    }

}
