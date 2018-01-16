package communication;

import architecture.NoC;

public class Message {

	// attribute
	Packet[] packets_array;

	public Packet[] getPackets_array() {
		return packets_array;
	}

	public void setPackets_array(Packet[] packets_array) {
		this.packets_array = packets_array;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	private int size;

	// constructor
	public Message(int size) {
		this.size = size;

		int packet_number = (int) (Math.ceil(this.size / NoC.PACKET_SIZE));
		packets_array = new Packet[packet_number];

		for (int i = 0; i < packet_number; i++) {
			packets_array[i] = new Packet(i);
		}

	}

}
