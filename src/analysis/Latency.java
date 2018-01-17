package analysis;

public class Latency {

    // With one network flow in the NoC
    public static double networkLatencyOne(int nP, int nR, double latPack) {
        return (nP + nR - 1) * latPack;
    }

    // Adding credit-based control flow
    public static double numberIteration(int nP, int aVflow) {
        return (int) Math.ceil((double) nP / aVflow);
    }

    public static double networkLatencyWH(int nI, int aVflow, int nR, double latPack) {
        return (nI * (aVflow + 1) + (nR - 1)) * latPack;
    }

    // With multiple control flow in the NoC
    public static double networkLatency(int nI, int oV, int nR, double latPack) {
        return ((nI * oV) + (nR - 1)) * latPack;
    }

}
