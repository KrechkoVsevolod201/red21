package online.server.httpServerTest;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    private int port;

    private String directory;

    public Server(int port, String directory) {
        this.port = port;
        this.directory = directory;
    }

    public void start() {
        try (var server = new ServerSocket(this.port)) {
            while (true) {
                var socket = server.accept();
                var thread = new Handler(socket, this.directory);
                thread.start();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        var port = 1234; //= Integer.parseInt(args[0]);
        var directory = "saved"; //= args[1];
        new Server(port, directory).start();
    }
}