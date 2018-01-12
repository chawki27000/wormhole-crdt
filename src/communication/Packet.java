package communication;

import architecture.NoC;

public class Packet {

    // attribute
    private int num;

    public Packet(int num) {
        this.num = num;
    }

    // - - - functions member - - -
    public Flit[] slising() {
        int flit_number = NoC.PACKET_SIZE / NoC.FLIT_SIZE;
        Flit[] flits = new Flit[flit_number];

        flits[0] = new Flit("Header");
        for (int i = 1; i < flit_number - 1; i++) {
            flits[i] = new Flit("Payload");
        }
        flits[flit_number-1] = new Flit("Tail");

        return flits;
    }
}
