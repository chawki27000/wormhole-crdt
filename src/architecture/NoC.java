package architecture;

import analysis.Latency;
import communication.Message;

import java.util.Hashtable;

public class NoC {

    // constant
    public final static int CREDIT_NUMBER = 1;
    public final static int PACKET_SIZE = 48; // 1 Head + 2 Data
    public final static int FLIT_SIZE = 16;
    public final static int NUMBER_FLIT_PER_PACKET = 32;
    public final static int VC_NUMBER = 8;
    public final static double LATENCY_PER_PACKET = 0.0075e-6;

    // attribut
    private int n;
    private int m;
    private Hashtable<Integer, Tile> tiles;

    public NoC(int n, int m) {
        this.n = n;
        this.m = m;
        tiles = new Hashtable<>();
        this.TileInitialization(n * m);
    }

    // - - - functions member - - -

    // refaite par Houssam
    private void TileInitialization(int tileNumber) {

        for (int i = 1; i < tileNumber + 1; i++)
            tiles.put(i, new Tile(i));

    }

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


    // refaite par Houssam
    // TODO : it is gives a false linking
    public void LinkingTiles() {
        for (int i = 1; i < tiles.size() + 1; i++) {
            Tile tile = getTile(i);
            int id = i;
            int idEst = id + 1;
            int idWest = id - 1;
            int idNord = id - n;
            int idSud = id + n;

            if (idEst % n != 1)
                tile.setEast(tiles.get(idEst));
            else
                tile.setEast(null);

            if (idWest % n != 0 && idWest > 0)
                tile.setWest(tiles.get(idWest));
            else
                tile.setWest(null);

            if (idNord > 0)
                tile.setNorth(tiles.get(idNord));
            else
                tile.setNorth(null);

            if (idSud < n * m + 1) {
                tile.setSouth(tiles.get(idSud));

            } else
                tile.setSouth(null);
        }
    }

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

    @Override
    public String toString() {
        String str = "";
        for (int i = 1; i < tiles.size() + 1; i++)
            str += tiles.get(i).toString() + "\n";
        return str;
    }


    public Tile getTile(int id) {
        return tiles.get(id);
    }

    public int routingDistance(int sender, int receiver) {
        return (int) (Math.abs((sender % m) - (receiver % m)) +
                Math.abs(Math.floor((float) sender / m) -
                        Math.floor((float) receiver / m)) + 1);
    }

    public double sendMessage(int sender, int receiver, Message m) {
        int nR = routingDistance(sender, receiver);
        int nF = (int) Math.ceil((float) m.getSize()/NoC.FLIT_SIZE);
        int nP = (int) ((float) nF/NoC.NUMBER_FLIT_PER_PACKET);
        int nI = (int) ((float) nP/ NoC.VC_NUMBER);
        int oV = tiles.get(sender).getRouter().getTotalVC(true, tiles.size());

        return Latency.networkLatency(nI, oV, nR, NoC.LATENCY_PER_PACKET);
    }


}
