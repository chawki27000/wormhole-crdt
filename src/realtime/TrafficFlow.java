package realtime;

import java.util.ArrayList;

public class TrafficFlow {

    // Real time characteristics
    private double c;
    private int p;
    private int t;
    private int d;
    private int jr;

    // Set of edges
    // Each edge represents a transfer between 2 vertices (from x to y)
    private ArrayList<Edge> edges;


    public TrafficFlow(int p, int t, int d, int jr) {
        this.p = p; // priority
        this.t = t; // period
        this.d = d; // deadline
        this.jr = jr; // release jitter

        edges = new ArrayList<>();
    }

    /**
     * @param lmax  maximum packet size belonging to traffic flow
     * @param ladd  wormhole additional data
     * @param fsize packet size
     * @param blink link bandwith
     * @param h     hops between source and destination router
     * @param s     processing delay in router
     * @return the maximum basic network latency of a traffic flow
     */
    public void maximumBasicNetworkLatency(int lmax, int ladd, int fsize,
                                           int blink, int h, int s) {

        this.c = Math.ceil((double) (lmax + ladd) / (double) fsize) * fsize
                / (double) blink + (h * s);
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }
}
