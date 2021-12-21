package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import java.util.Random;

public class MapVisualTest {
    /**provide the basic info about this world.*/
    private static final int WIDTH = 90;
    private static final int HEIGHT = 50;

    private static final Random RANDOM = new Random();
    private static final TERenderer ter = new TERenderer();
    private static final TETile[][] world = new TETile[WIDTH][HEIGHT];

    public static void main(String[] args) {
        ter.initialize(WIDTH, HEIGHT);

        MapGenerator map = new MapGenerator(WIDTH, HEIGHT, null);
        TETile[][] world = map.makeMap();

        ter.renderFrame(world);
    }


}
