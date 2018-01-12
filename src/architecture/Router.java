package architecture;

import communication.Flit;
import communication.Packet;

import java.util.ArrayList;

public class Router {

    // attribute
    private int credit;
    private ArrayList<Flit> buffer;

    // constructor
    public Router() {
        credit = NoC.CREDIT_NUMBER;
        buffer = new ArrayList<>();
    }

    // - - - functions member - - -

    // Sender functions

    public void sendPacket(Router receiver, Packet packet) {

        System.out.println("Sending packet : "+packet.getNum());
        Flit[] flits = packet.slising();

        // Sending all flits from the same packet
        for (int i = 0; i < flits.length; i++) {
            // prepare to sending
            if (this.decreditized()){
                if (receiver.receiveFlit(flits[i])){
                    this.creditized();
                }
            }
        }



    }

    private boolean decreditized() {
        if (credit <= 0){
            System.out.println("Impossible to send. buffer's full");
            return false;
        }
        else {
            credit--;
            return true;
        }

    }

    // Receiver functions

    private boolean receiveFlit(Flit flit) {
        buffer.add(flit);
        System.out.println("Receiving flit ("+flit.getType()+")");

        // ** Leaving the router **
        return true;

    }

    // credit function member
    private void creditized() {
        credit++;
    }

    // Other functions

    public int getCredit() {
        return credit;
    }


}
