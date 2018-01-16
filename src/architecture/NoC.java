package architecture;

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
		this.TileInitialization(n* m);
	}

	// - - - functions member - - -

	/**
	 * This function aims to create many tiles and refers at the n, m numbers
	 * 
	 * @param n
	 *            number of row
	 * @param m
	 *            number of column
	 */
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

	/**
	 * This function build a NoC topology by linking the tiles between them like
	 * a 2D Mesh
	 */

	// refaite par Houssam
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

	/**
	 * Printing
	 */

	// on fait souvent une méthode toString !!

	@Override
	public String toString() {
		String str = "";
		for (int i = 1; i < tiles.size() + 1; i++)
			str += tiles.get(i).toString() + "\n";
		return str;
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

	/**
	 * @param sender
	 *            The tile which wants to send a message
	 * @param receiver
	 *            The tile which receiving a message
	 * @param m
	 */


	/**
	 * @param fsender
	 * @param freceiver
	 * 
	 */
	

	public Tile getTile(int id) {
		return tiles.get(id);
	}

}
