package architecture;

public class Tile {

    // attribute
    private String id;
    private PE pe;
    private Router r;

    private Tile north;
    private Tile south;
    private Tile west;
    private Tile east;

    // constructor
    public Tile(String id, Tile north, Tile south, Tile west, Tile east) {
        this.id = id;
        this.north = north;
        this.south = south;
        this.west = west;
        this.east = east;
    }

    // - - - functions member - - -


}
