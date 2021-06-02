package garbage;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonReader {
    private String memory = "";

    public JsonReader(String file) {
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
            memory = line;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        try(FileWriter writer = new FileWriter("saved\\" + file + ".txt", false))
        {
            writer.write(memory);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

    }

    public static void main(String[] args) {
        new JsonReader("Yuh");
    }
}
