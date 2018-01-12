package architecture;

import communication.Message;
import communication.Packet;

import java.util.Hashtable;

public class NoC {

    // constant
    public final static int CREDIT_NUMBER = 5;
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
    private void tileInitialization(int n, int m) {

        Tile tile;
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // tile initialization
                tile = new Tile(idx);
                tiles.put(idx, tile);
                idx++;

            }
        }

    }

    public void linkingTiles() {
        int idx = 0;
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


    public void sendMessage(Tile sender, Tile receiver,
                            Message message) {

        // Spliting a message to a multiple packets
        Packet[] packets = message.slising();

        // the 2 routers
        Router routerSender = sender.getRouter();
        Router routerReceiver = receiver.getRouter();

        routerSender.sendPacket(routerReceiver, packets[0]);

    }

    public Tile getTile(int id) {
        return tiles.get(id);
    }

}
