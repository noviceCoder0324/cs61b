package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MapGenerator {

    private final Random RANDOM; //randomizer.
    private final int width;  // the width of the world.
    private final int height; // the height of the world.
    private final TETile[][] world;  // the final world made by seed.
    private boolean worldUsed[][];  //check if a position is used.
    private List<Room> rooms = new ArrayList<>();
    private final static int DENSITY = 100;

    private final Comparator<Room> c = (o1, o2) -> o1.getP().getX() - o2.getP().getX();

    /**constructor for the map.*/
    public MapGenerator(int width, int height, Long seed) {
        // random seed from outside.
        this.width = width;
        this.height = height;
        world = new TETile[width][height];
        worldUsed = new boolean[width][height];
        RANDOM = new Random(seed);
    }

    // make a room in the map
    private void makeRooms() {
        //make a random room
        int l = RandomUtils.uniform(RANDOM, 1,10);
        int w = RandomUtils.uniform(RANDOM, 1,10);

        int x = RandomUtils.uniform(RANDOM, 1,width-2-w);
        int y = RandomUtils.uniform(RANDOM, 1,height-2-l);
        Position p = new Position(x, y);

        Room room = new Room(l,w,p);
        //check if it will overlap with any other room
        if (!isOverlapping(room)) {
            //if not, put it in the rooms list
            rooms.add(room);
            markWorld(room);
        }

    }

    // mark the floor in the world
    private void markWorld(Room room) {
        int px = room.getP().getX();
        int py = room.getP().getY();
        int tx = px + room.getWidth();
        int ty = py + room.getLength();
        for (int i = px; i < tx; i++) {
            for (int j = py; j < ty; j++) {
                world[i][j] = TETile.colorVariant(Tileset.FLOOR,50,50,50, RANDOM);
                worldUsed[i][j] = true;
            }
        }

    }

    // check if a room is overlapping with another one
    private boolean isOverlapping(Room room) {
        int px = room.getP().getX();
        int py = room.getP().getY();
        int tx = px + room.getWidth();
        int ty = py + room.getLength();
        for (int i = px; i < tx; i++) {
            for (int j = py; j < ty; j++) {
                if (worldUsed[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    //build the hallway for each room in the list.
    private void makeHallWays() {
        for (int i = 0; i < rooms.size()-1; i++) {
            linkTwoPosition(rooms.get(i).getP(), rooms.get(i+1).getP());
        }
    }

    // link two positions on the map.
    private void linkTwoPosition(Position p1, Position p2) {
        int x1 = p1.getX();
        int y1 = p1.getY();
        int x2 = p2.getX();
        int y2 = p2.getY();
        while (x1 < x2) {
            world[x1][y1] = TETile.colorVariant(Tileset.FLOOR,50,50,50, RANDOM);
            worldUsed[x1][y1] = true;
            x1 += 1;
        }
        if (y2 > y1) {
            while (y2 > y1) {
                world[x1][y1] = TETile.colorVariant(Tileset.FLOOR,50,50,50, RANDOM);
                worldUsed[x1][y1] = true;
                y1 += 1;
            }
        } else {
            while (y1 > y2) {
                world[x1][y1] = TETile.colorVariant(Tileset.FLOOR,50,50,50, RANDOM);
                worldUsed[x1][y1] = true;
                y1 -= 1;
            }
        }
    }


    /**return the width of the map.*/
    public int getWidth() {
        return width;
    }

    /**return the height of the map.*/
    public int getHeight() {
        return height;
    }

    /**return the final map made by the seed.*/
    public TETile[][] makeMap() {
        fillWithEmpty();
        makeALotRooms();
        linkRooms();
        buildWall();
        makeADoor();
        return world;
    }

    // make a door for this map
    private void makeADoor() {
        int numI = RandomUtils.uniform(RANDOM, 2,width/2);
        int numJ = RandomUtils.uniform(RANDOM, 2,height/2);

        for (int i = numI; i < width-2; i++) {
            for (int j = numJ; j < height-2; j++) {
                TETile t = world[i][j];
                if (t.equals(Tileset.WALL) && isNotConor(new Position(i, j))) {
                    world[i][j] = Tileset.LOCKED_DOOR;
                    return;
                }
            }
        }


    }

    private boolean isNotConor(Position position) {
        int px = position.getX();
        int py = position.getY();
        return worldUsed[px + 1][py] || worldUsed[px - 1][py] || worldUsed[px][py + 1] || worldUsed[px][py - 1];
    }


    //build all the walls for the map
    private void buildWall() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (worldUsed[i][j]){
                    tileAllAround(new Position(i, j));
                }
            }
        }
    }

    //tile all around one position.
    private void tileAllAround(Position position) {
        int lowerPy = position.getY() - 1;
        int py = position.getY();
        int upperPy = position.getY() + 1;
        int leftPx = position.getX() - 1;
        int rightPx = position.getX() + 1;

        for (int i = leftPx; i < leftPx+3; i++) {
            if (!worldUsed[i][lowerPy]){
                world[i][lowerPy] = TETile.colorVariant(Tileset.WALL,50,50,50, RANDOM);
            }
            if (!worldUsed[i][upperPy]){
                world[i][upperPy] = TETile.colorVariant(Tileset.WALL,50,50,50, RANDOM);
            }
        }
        if (!worldUsed[leftPx][py]){
            world[leftPx][py] = TETile.colorVariant(Tileset.WALL,50,50,50, RANDOM);
        }
        if (!worldUsed[rightPx][py]){
            world[rightPx][py] = TETile.colorVariant(Tileset.WALL,50,50,50, RANDOM);
        }
    }

    //link all the rooms.
    private void linkRooms() {
        rooms.sort(c);
        makeHallWays();
    }

    // make a lot of rooms.
    private void makeALotRooms() {
        for (int i = 0; i < DENSITY; i++) {
            makeRooms();
        }
    }

    /**fill the map with empty nothing for now.*/
    private void fillWithEmpty() {
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                world[i][j] = Tileset.NOTHING;
            }
        }
    }



}
