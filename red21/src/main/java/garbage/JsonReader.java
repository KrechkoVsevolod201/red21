package garbage;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonReader {

    public JsonReader(String file) {
        //public static void main(String[] args) {
        try (FileReader fr = new FileReader("saved\\" + file + ".json")) {
            // читаем посимвольно
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            line = reader.readLine();
            char[] result = line.toCharArray();
            line = "";
            for (int i = 19; i < result.length; i++) {
                line = line + result[i];
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new JsonReader("Yuh");
    }
}
