package communication;

import architecture.NoC;

import java.util.ArrayList;

public class Message {

    // attribute
    private ArrayList<Packet> packets;
    private int size;

    // constructor
    public Message(int size) {
        this.size = size;
    }


    // - - - functions member - - -

    public Packet[] slising() {

        int packet_number = size/ NoC.PACKET_SIZE;
        Packet[] packets = new Packet[packet_number];

        for (int i = 0; i < packet_number; i++) {
            packets[i] = new Packet(i);
        }

        return packets;
    }


}
