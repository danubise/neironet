package ml;

/**
 * Created by vkurilo on 7/27/17.
 */
public class testTrainer {
    public static void main(String[] args) {
        Training training = new Training();
        Network network = new Network();
        network.addNeiron(new Neiron("Vodka", NeironType.INPUT));
        network.addNeiron(new Neiron("Rain", NeironType.INPUT));
        network.addNeiron(new Neiron("Friend", NeironType.INPUT));
        network.addNeiron(new Neiron("Zont", NeironType.INPUT));
        network.addNeiron(new Neiron("Probka", NeironType.INPUT));


        network.addNeiron(new Neiron("Hidden1", NeironType.HIDDEN , network.getNeironByName("Vodka"), 0.5 )).
               // addNeirons(network.getNeironByName("Rain"),  0.8).
                addNeirons(network.getNeironByName("Friend"), 0.4);
               // .addNeirons(network.getNeironByName("Zont"), 0.5);



        network.addNeiron(new Neiron("Hidden2", NeironType.HIDDEN , network.getNeironByName("Vodka"), 0.5 )).
                addNeirons(network.getNeironByName("Rain"),  0.8).
                addNeirons(network.getNeironByName("Friend"), 0.4).
                addNeirons(network.getNeironByName("Zont"), 0.5);

        network.addNeiron(new Neiron("Hidden3", NeironType.HIDDEN , network.getNeironByName("Rain"), 0.5 )).
                addNeirons(network.getNeironByName("Zont"), 0.5) .addNeirons(network.getNeironByName("Probka"), 0.9);

        network.addExitNeiron(new Neiron("Exit", NeironType.EXIT, network.getNeironByName("Hidden1"),0.5)).
                addNeirons(network.getNeironByName("Hidden2"), 0.5).addNeirons(network.getNeironByName("Hidden3"), 0.8);

        training.setNetwork(network);
        training.test();
    }
}
