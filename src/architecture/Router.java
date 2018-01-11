package architecture;

import communication.Flit;

import java.util.ArrayList;

public class Router {

    // constant


    // attribute
    private int credit;
    private ArrayList<Flit> buffer;

    // constructor
    public Router() {
        credit = NoC.CREDIT_NUMBER;
        buffer = new ArrayList<>();
    }

    // - - - functions member - - -

    public void sendPacket(Router receiver, Flit flit) {


    }

    public void ReceivePacket(Flit flit) {
        buffer.add(flit);

    }

    // credit function member
    public void creditized() {
        credit++;
    }

    public void decreditized() {
        credit--;
    }

    public int getCredit() {
        return credit;
    }

    // buffer function member
    public void bufferized(Flit flit) {
        buffer.add(flit);
    }

    public void debufferized() {

    }

}
