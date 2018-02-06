package architecture;



public class Tile {

	// attribute
	private int id;
	private PE pe;

	public PE getPE() {
		return pe;
	}

	public void setPE(PE pe) {
		this.pe = pe;
	}

	public void setId(int id) {
		this.id = id;
	}

	private Router r;

	private Tile north;
	private Tile south;
	private Tile west;
	private Tile east;

	// constructor
	public Tile(int id) {
		this.id = id;
		r = new Router();
		pe = new PE();
	}

	// - - - functions member - - -

	@Override
	public String toString() {
		String neighbors = "";
		if (east != null)
			neighbors += east.getId() + ",";
		else
			neighbors += "null,";

		if (west != null)
			neighbors += west.getId() + ",";
		else
			neighbors += "null,";

		if (north != null)
			neighbors += north.getId() + ",";
		else
			neighbors += "null,";

		if (south != null)
			neighbors += south.getId() + "";
		else
			neighbors += "null";

		return id + "-> (" + neighbors + ")";
	}

	public Router getRouter() {
		return r;
	}

	public void setNorth(Tile north) {
		this.north = north;
	}

	public void setSouth(Tile south) {
		this.south = south;
	}

	public void setWest(Tile west) {
		this.west = west;
	}

	public void setEast(Tile east) {
		this.east = east;
	}

	public Tile getNorth() {
		return north;
	}

	public Tile getSouth() {
		return south;
	}

	public Tile getWest() {
		return west;
	}

	public Tile getEast() {
		return east;
	}

	public int getId() {
		return id;
	}


	
	
	
	
	
	
	/*public void sendMessage(Tile receiver, Message m) {

		Router routerReceiver = receiver.getRouter();

		// send all packets to the next router
		for (int i = 0; i < m.getPackets_array().length; i++) {
			this.r.sendPacket(routerReceiver, m.getPackets_array()[i]);
		}
	}*/
	
	
	
	
	
	
	
	/*
	
	
	public void dimensionOrderedRouting(Tile fsender, Tile freceiver, Message m) {

		// compute the coordinates of tiles
		int[] senderCoord = getMeshCoordinate(fsender);
		int[] receiverCoord = getMeshCoordinate(freceiver);
		int diff;

		Tile last = fsender;

		// Transmit to the left tile
		if (senderCoord[0] > receiverCoord[0]) {
			diff = senderCoord[0] - receiverCoord[0];
			for (int i = 0; i < diff; i++) {
				sendMessage(last, last.getWest(), m);
				last = last.getWest();
			}
		}

		// Transmit to the right tile
		else if (senderCoord[0] < receiverCoord[0]) {
			diff = receiverCoord[0] - senderCoord[0];
			for (int i = 0; i < diff; i++) {
				sendMessage(last, last.getEast(), m);
				last = last.getEast();
			}
		}

		// Transmit to the upper tile
		if (senderCoord[1] > receiverCoord[1]) {
			diff = senderCoord[1] - receiverCoord[1];
			for (int i = 0; i < diff; i++) {
				sendMessage(last, last.getNorth(), m);
				last = last.getNorth();
			}
		}

		// Transmit to the lowest tile
		else if (senderCoord[1] < receiverCoord[1]) {
			diff = receiverCoord[1] - senderCoord[1];
			for (int i = 0; i < diff; i++) {
				sendMessage(last, last.getSouth(), m);
				last = last.getSouth();
			}
		}
	}

	
	*/
	/*private int[] getMeshCoordinate(Tile tile) {
		int idx = tile.getId();
		System.out.println("TILE : " + idx);
		int[] coordinate = new int[2];

		coordinate[0] = (idx % m == 0) ? m : idx % m;
		coordinate[1] = (int) Math.ceil((float) idx / m);
		return coordinate;

	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
