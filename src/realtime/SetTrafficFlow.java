package realtime;

import java.util.ArrayList;

public class SetTrafficFlow {

    private ArrayList<TrafficFlow> set;

    public SetTrafficFlow() {
        this.set = new ArrayList<>();
    }

    public void addTrafficFlow(TrafficFlow flow) {
        set.add(flow);
    }

    public TrafficFlow getTrafficFlow(int index) {
        return set.get(index);
    }

    /**
     * @param observed our observed traffic flow
     * @param other    others traffic flow belonging to setTrafficFlow
     * @return if they have a flow intersection
     */
    private boolean haveAnIntersection(TrafficFlow observed,
                                       TrafficFlow other) {

        ArrayList<Edge> observedEdge = observed.getEdges();
        ArrayList<Edge> otherEdge = other.getEdges();

        for (Edge edge1 : observedEdge) {
            for (Edge edge2 : otherEdge) {
                if (edge1.getSource() == edge2.getSource() &&
                        edge1.getDestination() == edge2.getDestination())
                    return true;
            }
        }

        return false;
    }

    /**
     * This member function returns an array of traffic flow
     * which have a direct interference with this one (observed)
     */
    public ArrayList<Integer> directInterferenceSet(int observed) {
        ArrayList<Edge> edgesObserved; // observed's edges
        ArrayList<Edge> edgesTmp; // other's edges

        TrafficFlow observedFlow = set.get(observed);

        ArrayList<Integer> intersection = new ArrayList<>();

        for (int i = 0; i < set.size() && i != observed; i++) {
            if (haveAnIntersection(observedFlow, set.get(i)))
                intersection.add(i);
        }

        return intersection;
    }

    public double networkLatency(int observed, double Ci) {
        // get intersection set
        ArrayList<Integer> intersectionSet = directInterferenceSet(observed);

        // default first network latency value
        // Ri will have Ci in the first iteration
        double Ri = Ci;
        double tmp;

        // iteration
        while (true){
            tmp = Ri;
            Ri += Ci;
            for (int i :intersectionSet) {
                Ri += Math.ceil(((tmp+ set.get(observed).getJr())/(double) set.get(i).getT())
                        + set.get(i).getC());
            }

            if (Ri > set.get(observed).getD())
                break;

        }

        return Ri;

    }

}
