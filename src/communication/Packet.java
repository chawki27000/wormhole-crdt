package communication;

import communication.Flit.FlitType;

import architecture.NoC;

public class Packet {

	// attribute
	private int num;

	public Packet(int num) {
		this.num = num;
		
		int packet_size = NoC.PACKET_SIZE / NoC.FLIT_SIZE;
		flit_Array = new Flit[packet_size + 2];

		flit_Array[0] = new Flit(FlitType.HEAD);
		for (int i = 0; i < packet_size; i++) {
			flit_Array[i + 1] = new Flit(FlitType.DATA);
		}
		flit_Array[packet_size] = new Flit(FlitType.TAIL);

	}

	Flit[] flit_Array;

	public int getNum() {
		return num;
	}

	public Flit[] getFlit_Array() {
		return flit_Array;
	}

	public void setFlit_Array(Flit[] flit_Array) {
		this.flit_Array = flit_Array;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
