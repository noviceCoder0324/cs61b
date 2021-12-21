package byog.Core;

import java.io.*;

public class Save {


    public static void saveSeed(String seed) {
        try {
            File saveFile = new File("save.txt");
            FileWriter save = new FileWriter(saveFile);
            PrintWriter pw = new PrintWriter(save);
            pw.println(seed);
            pw.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static int loadSeed() {
        try {
            FileReader save = new FileReader("save.txt");
            BufferedReader bw = new BufferedReader(save);
            String seed = bw.readLine();
            bw.close();
            return Integer.parseInt(seed);
        } catch(IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
