package garbage;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonReader {
    private String memory = "";

    public JsonReader(String file, String ip, String port) {

        try {
            URL url = new URL("http://" + ip + ":" + port + "/" + file + ".json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());

            File f1 = new File("saved\\" + file + ".json");
            FileOutputStream fw = new FileOutputStream(f1);

            byte[] b = new byte[1024];
            int count = 0;

            while ((count=bis.read(b)) != -1)
                fw.write(b,0,count);

            fw.close();
        } catch (IOException ex) {
        }


        try (FileReader fr = new FileReader("saved\\" + file + ".json")) {
            // читаем посимвольно
            BufferedReader reader = new BufferedReader(fr);
            String line = "";
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
        new JsonReader("Yuh", "192.168.1.50", "1234");
    }
}
