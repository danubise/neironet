package ml;

/**
 * Created by vkurilo on 7/27/17.
 */
public class test3 {
    public static void main(String[] args) {
        Network network = new Network();
        network.addNeiron(new Neiron("Vodka", NeironType.INPUT));
        network.addNeiron(new Neiron("Rain", NeironType.INPUT));
        network.addNeiron(new Neiron("Friend", NeironType.INPUT));


        network.addExitNeiron(new Neiron("Hidden1", NeironType.HIDDEN , network.getNeironByName("Vodka"), 0.2 )).
                addNeirons(network.getNeironByName("Rain"), 0.5).
                addNeirons(network.getNeironByName("Friend"), 0.4);

        System.out.println(network.getExitValue());
        NeironNetworkValue v1 = new NeironNetworkValue();
        v1.setValues("Vodka",1);
        v1.setValues("Rain",0);
        v1.setValues("Friend",1);
        network.setInputNeironsValue(v1);
        System.out.println(network.getExitValue());
    }
}
