package architecture;

import communication.Message;
import communication.Packet;

import java.util.Hashtable;

public class NoC {

    // constant
    public final static int CREDIT_NUMBER = 1;
    public final static int PACKET_SIZE = 32;
    public final static int FLIT_SIZE = 4;

    public final static int VC_NUMBER = 4;

    // attribut
    private int n;
    private int m;
    private Hashtable<Integer, Tile> tiles;

    public NoC(int n, int m) {
        this.n = n;
        this.m = m;
        tiles = new Hashtable<>();

        this.tileInitialization(n, m);
    }

    // - - - functions member - - -

    /**
     * This function aims to create many tiles and refers
     * at the n, m numbers
     * @param n number of row
     * @param m number of column
     */
    private void tileInitialization(int n, int m) {

        Tile tile;
        int idx = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // tile initialization
                tile = new Tile(idx);
                tiles.put(idx, tile);
                idx++;

            }
        }

    }

    /**
     * This function build a NoC topology by linking the tiles
     * between them like a 2D Mesh
     */
    public void linkingTiles() {
        int idx = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (i == 0) { // the tiles in the first row
                    if (j == 0) { // the tile in the first column
                        tiles.get(idx).setEast(tiles.get(idx + 1));

                        tiles.get(idx).setSouth(tiles.get(idx + m));

                    } else if (j == m - 1) { // the tile in the last column
                        tiles.get(idx).setWest(tiles.get(idx - 1));

                        tiles.get(idx).setSouth(tiles.get(idx + m));

                    } else { // the tiles in the middle columns
                        tiles.get(idx).setEast(tiles.get(idx + 1));
                        tiles.get(idx).setWest(tiles.get(idx - 1));
                        tiles.get(idx).setSouth(tiles.get(idx + m));
                    }

                } else if (i == n - 1) { // the tiles in the last row
                    if (j == 0) { // the tile in the first column
                        tiles.get(idx).setEast(tiles.get(idx + 1));

                        tiles.get(idx).setNorth(tiles.get(idx - m));

                    } else if (j == m - 1) { // the tile in the last column
                        tiles.get(idx).setWest(tiles.get(idx - 1));

                        tiles.get(idx).setNorth(tiles.get(idx - m));

                    } else { // the tiles in the middle columns
                        tiles.get(idx).setEast(tiles.get(idx + 1));
                        tiles.get(idx).setWest(tiles.get(idx - 1));
                        tiles.get(idx).setNorth(tiles.get(idx - m));
                    }

                } else { // the tiles in the middle row
                    if (j == 0) { // the tile in the first column
                        tiles.get(idx).setEast(tiles.get(idx + 1));

                        tiles.get(idx).setNorth(tiles.get(idx - m));
                        tiles.get(idx).setSouth(tiles.get(idx + m));

                    } else if (j == m - 1) { // the tile in the last column
                        tiles.get(idx).setWest(tiles.get(idx - 1));

                        tiles.get(idx).setNorth(tiles.get(idx - m));
                        tiles.get(idx).setSouth(tiles.get(idx + m));

                    } else { // the tiles in the middle columns
                        tiles.get(idx).setEast(tiles.get(idx + 1));
                        tiles.get(idx).setWest(tiles.get(idx - 1));
                        tiles.get(idx).setNorth(tiles.get(idx - m));
                        tiles.get(idx).setSouth(tiles.get(idx + m));
                    }
                }

                idx++;
            }
        }
    }

    /**
     * Printing
     */
    public void printTiles() {
        int idx = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(idx + " -- ");
                idx++;
            }
            System.out.println();
        }

    }


    /**
     * @param sender   The tile which wants to send a message
     * @param receiver The tile which receiving a message
     * @param m
     */
    private void sendMessage(Tile sender, Tile receiver,
                            Message m) {

        System.out.println("Sending message form Tile (" + sender.getId() + ")" +
                " to Tile (" + receiver.getId() + ")");

        // Spliting a message to a multiple packets
        Packet[] packets = m.slising();

        // the 2 routers belonging to tiles
        Router routerSender = sender.getRouter();
        Router routerReceiver = receiver.getRouter();

        // send all packets to the next router
        for (int i = 0; i < packets.length; i++) {
            routerSender.sendPacket(routerReceiver, packets[i]);
        }


    }

    /**
     * @param fsender
     * @param freceiver
     *
     */
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

    private int[] getMeshCoordinate(Tile tile) {
        int idx = tile.getId();
        System.out.println("TILE : "+idx);
        int[] coordinate = new int[2];

        coordinate[0] = (idx % m == 0) ? m : idx % m;
        coordinate[1] = (int) Math.ceil((float)idx/m);
        return coordinate;

    }

    public Tile getTile(int id) {
        return tiles.get(id);
    }

}
