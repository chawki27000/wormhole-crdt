package main;

import architecture.NoC;
import communication.Message;

public class Main {
	public static void main(String[] args) {

		// NoC instanciation
		NoC noc = new NoC(4, 6);
		noc.linkingTiles();

		// With one Network flow
		Message m = new Message(1024);
		System.out.println("network latency : "+noc.sendMessage(8,16, m));


	}
}
