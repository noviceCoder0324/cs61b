package byog.Core;

public class Room {
    private final int length;
    private final int width;

    private final Position p;

    public Room(int l, int w, Position lowerLeft) {
        length = l;
        width = w;
        p = lowerLeft;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public Position getP() {
        return p;
    }

}
