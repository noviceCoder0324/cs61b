package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    /**the world's width and height.*/
    private static final int WIDTH = 90;
    private static final int HEIGHT = 50;

    private static final Random RANDOM = new Random();
    private static final TERenderer ter = new TERenderer();
    private static final TETile[][] hexTiles = new TETile[WIDTH][HEIGHT];

    /**fill everything with empty nothing.*/
    private static void fillWithEmpty(TETile[][] world){
        for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < HEIGHT; j++){
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    /**build a hexagon with size in the world.*/
    public static void addHexagon(int size, TETile[][] world, Position p, TETile t) {
        int width = 3 * size - 2;
        int height = 2 * size;

        for(int i = p.x; i <= width + p.x; i++) {
            for(int j = p.y; j < height + p.y; j++) {
                if ((j < p.y + size && i >= p.x + size - 1 - (j - p.y) && i <= p.x + width - size + (j - p.y))
                        || (j >= p.y + size && i >= p.x + Math.abs(size - (j - p.y)) && i < p.x + width - Math.abs(size - (j - p.y)))) {
                    world[i][j] = TETile.colorVariant(t,50,50,50, RANDOM);
                }
            }
        }
    }

    /**Draw Random Vertical Hexes.*/
    public static void drawRandomVerticalHexes(int size, TETile[][] world, Position p, TETile t, int n){
        for (int i = 0; i < n; i++) {
            addHexagon(size, world, p, chooseATile());
            p = new Position(p.x, p.y+2*size);
        }
    }

    /**return one kind of tile.*/
    public static TETile chooseATile(){
        int tileNum = RANDOM.nextInt(8);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.SAND;
            case 3: return Tileset.MOUNTAIN;
            case 4: return Tileset.FLOOR;
            case 5: return Tileset.GRASS;
            case 6: return Tileset.WATER;
            case 7: return Tileset.LOCKED_DOOR;
            case 8: return Tileset.PLAYER;
            default: return Tileset.NOTHING;
        }
    }

    /**provide a position for the hexagon.*/
    private static class Position {
        public int x;
        public int y;
        public Position(int positionX, int positionY) {
            x = positionX;
            y = positionY;
        }
    }

    public static void drawAGroupHex(Position p, int size) {
        drawRandomVerticalHexes(size, hexTiles, p, chooseATile(), 6);
        Position b = new Position(p.x + (2*size-1), p.y+size);
        drawRandomVerticalHexes(size, hexTiles, b, chooseATile(), 5);
    }



    public static void main(String[] args) {
        ter.initialize(WIDTH, HEIGHT);

        fillWithEmpty(hexTiles);
        Position a = new Position(0, 0);
        int size = 4;
        for (int i = 0; i < 6; i++){
            drawAGroupHex(a, size);
            a = new Position(a.x+(2*size-1)*2, a.y);
        }

        ter.renderFrame(hexTiles);
    }
}
