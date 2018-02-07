package main;

import architecture.NoC;
import communication.Message;
import realtime.Edge;
import realtime.SetTrafficFlow;
import realtime.TrafficFlow;

public class Main {
	public static void main(String[] args) {

		// NoC instanciation
		NoC noc = new NoC(4, 6);
		noc.linkingTiles();

		// With one Network flow
		Message m = new Message(1024);
		System.out.println("network latency : "+noc.sendMessage(1,9, m));

		// Real-time traffic flow example
		TrafficFlow t1 = new TrafficFlow(2, 1, 6, 6, 0);
		TrafficFlow t2 = new TrafficFlow(1, 2, 5, 5, 0);
		TrafficFlow t3 = new TrafficFlow(3, 3, 10, 10, 0);
		TrafficFlow t4 = new TrafficFlow(4, 4, 15, 15, 0);

		// add edges to traffic flow
		Edge edge1 = new Edge(1,2);
		Edge edge2 = new Edge(2,3);
		Edge edge3 = new Edge(3,9);
		t1.setEdges(edge1);
		t1.setEdges(edge2);
		t1.setEdges(edge3);

		Edge edge4 = new Edge(2,3);
		Edge edge5 = new Edge(3,4);
		Edge edge6 = new Edge(4,5);
		t2.setEdges(edge4);
		t2.setEdges(edge5);
		t2.setEdges(edge6);

		// traffic flow set
		SetTrafficFlow setTrafficFlow = new SetTrafficFlow();
		setTrafficFlow.addTrafficFlow(t1);
		setTrafficFlow.addTrafficFlow(t2);
		setTrafficFlow.addTrafficFlow(t3);
		setTrafficFlow.addTrafficFlow(t4);

		// t1 Real-time network latency (with t2 contention)
		System.out.println("Real-time Network latency : "+setTrafficFlow.networkLatency(0, 2));

	}
}
