package main;

import architecture.NoC;
import communication.Message;

public class Main {
    public static void main(String[] args) {

        // NoC instanciation
        NoC noc = new NoC(4, 6);
        noc.linkingTiles();

        noc.printTiles();

        // message sending
        Message message = new Message(1024);
        noc.sendMessage(noc.getTile(1), noc.getTile(2), message);
    }
}
