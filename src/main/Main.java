package main;

import architecture.NoC;

public class Main {
    public static void main(String[] args) {

        // NoC instanciation
        NoC noC = new NoC(4, 6);
        noC.linkingTiles();

        noC.printTiles();
    }
}
