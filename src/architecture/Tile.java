package architecture;

public class Tile {

    // attribute
    private int id;
    private PE pe;
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
}
