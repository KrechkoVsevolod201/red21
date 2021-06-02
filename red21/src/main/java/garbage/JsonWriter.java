package garbage;

import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {

    public JsonWriter(String number){
    //public static void main(String[] args) {
        try(
                FileWriter writer = new FileWriter("saved\\Yuh.json", false))
        {
            // запись всей строки
            final char dm = (char) 34;
            final String quotes = "" + dm;
            writer.write("{\n");
            writer.write(dm + "playerOneScore" + dm + " : " + number + "\n");
            writer.write("}");
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new JsonWriter("1234");
    }
}
