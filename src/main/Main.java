package main;

import architecture.NoC;
import communication.Message;

public class Main {
    public static void main(String[] args) {

        // NoC instanciation
        NoC noc = new NoC(4, 6);
        noc.linkingTiles();

        // printing a NoC Mesh 2D topology
//        noc.printTiles();

        // Building a 1024 size message
        Message m = new Message(64);

        // message sending
        noc.dimensionOrderedRouting(noc.getTile(6), noc.getTile(15), m);
    }
}
