package ml;

/**
 * Created by vkurilo on 7/27/17.
 */
public class test2 {
    public static void main(String[] args) {

        Network network = new Network();
        network.addNeiron(new Neiron("Vodka", NeironType.INPUT));
        network.addNeiron(new Neiron("Rain", NeironType.INPUT));
        network.addNeiron(new Neiron("Friend", NeironType.INPUT));


        network.addNeiron(new Neiron("Hidden1" , NeironType.HIDDEN, network.getNeironByName("Vodka"), 0.2 )).
                addNeirons(network.getNeironByName("Rain"), 0.5).
                addNeirons(network.getNeironByName("Friend"), 0.0);

        network.addNeiron(new Neiron("Hidden2" , NeironType.HIDDEN,  network.getNeironByName("Vodka"), 0.2 )).
                addNeirons(network.getNeironByName("Vodka"),0.7).
                addNeirons(network.getNeironByName("Rain"),0.6).
                addNeirons(network.getNeironByName("Friend"), 0.8);

        network.addExitNeiron(new Neiron("Exit", NeironType.EXIT))
                .addNeirons(network.getNeironByName("Hidden1"),0.5)
                .addNeirons(network.getNeironByName("Hidden2"),0.52);
        NeironNetworkValue v1 = new NeironNetworkValue();
        v1.setValues("Vodka",0);
        v1.setValues("Rain",0);
        v1.setValues("Friend",0);
        network.setInputNeironsValue(v1);
        System.out.println(network.getNeironByName("Exit"));
        Double result1 = network.getExitValue();
        v1.setValues("Vodka",1);
        v1.setValues("Rain",0);
        v1.setValues("Friend",1);
        network.setInputNeironsValue(v1);

        System.out.println(network.getNeironByName("Exit"));
        Double result2 = network.getExitValue();

        System.out.println("\nR1 =" + result1 + "\nR2 =" +result2);
    }
}
