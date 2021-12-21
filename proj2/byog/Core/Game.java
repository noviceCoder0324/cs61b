package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        char[] chars = input.toCharArray();
        int charsLength = chars.length;
        String seed = "";
        Long sd = null;

        // create a new game with seed directly, no n.
        if (Character.isDigit(chars[0])) {
            for (char aChar : chars) {
                if (Character.isDigit(aChar)) {
                    seed += Character.toString(aChar);
                    sd = Long.parseLong(seed);
                } else {
                    break;
                }
            }
        }
        // create a new game with seed
        if (chars[0] == 'n') {
            // get the seed from the String
            for (int i = 1; i < chars.length; i++) {
                if (Character.isDigit(chars[i])) {
                    seed += Character.toString(chars[i]);
                    sd = Long.parseLong(seed);
                } else {
                    break;
                }
            }

            // check if it will need a save.
            if (chars[charsLength-1] == 'q' && chars[charsLength-2] == ':') {
                Save.saveSeed(seed);
            }
        }

        // load a game from file
        if (chars[0] == 'l') {
            sd = Save.loadSeed();
        }

        // quit the game.
        //TODO how to do this part?

        MapGenerator map = new MapGenerator(WIDTH, HEIGHT, sd);
        return map.makeMap();
    }
}
