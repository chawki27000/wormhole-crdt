package main;

import architecture.NoC;

public class Main {
    public static void main(String[] args) {

        // NoC instanciation
        NoC noC = new NoC(6, 4);
        noC.linkingTiles();

        noC.printTiles();
    }
}
