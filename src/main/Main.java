package main;

import architecture.NoC;

public class Main {
	public static void main(String[] args) {

		// NoC instanciation
		NoC noc = new NoC(4, 4);
		noc.LinkingTiles();
		System.out.println(noc);
		/*
		 * Message m = new Message(64);
		 * 
		 * 
		 * noc.dimensionOrderedRouting(noc.getTile(6), noc.getTile(15), m);
		 */
	}
}
