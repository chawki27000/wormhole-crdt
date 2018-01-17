package architecture;

import communication.Flit;
import communication.Message;
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

    // Functions member - - -

    // Sender functions

    /**
     * @param receiver The tile receiver's router
     * @param packet
     */
    public void sendPacket(Router receiver, Packet packet) {

        System.out.println("Sending packet : " + packet.getNum());

        // Sending all flits from the same packet
        for (int i = 0; i < packet.getFlit_Array().length; i++) {
            // prepare to sending
            if (this.decreditized()) {
                if (receiver.receiveFlit(packet.getFlit_Array()[i])) {
                    this.creditized();
                }
            }
        }

    }

    /**
     * This function decrements the credit of the sender router if it's greater
     * than 0
     *
     * @return True if it has a credit False on the other hand
     */
    private boolean decreditized() {
        if (credit <= 0) {
            System.out.println("Impossible to send. buffer's full");
            return false;
        } else {
            credit--;
            return true;
        }

    }

    // Receiver functions

    private boolean receiveFlit(Flit flit) {
        buffer.add(flit);
        System.out.println("Receiving flit (" + flit.getType() + ")");

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

    // latency analysis
    public int getTotalVC(boolean sameDirection, int numberTiles) {
        numberTiles -= 1;
        return sameDirection ? NoC.VC_NUMBER + (NoC.VC_NUMBER *
                numberTiles) + 1 : NoC.VC_NUMBER + (NoC.VC_NUMBER *
                numberTiles);
    }

    public float getRatioVC(int aV, int oV) {
        return aV/oV;
    }
}
