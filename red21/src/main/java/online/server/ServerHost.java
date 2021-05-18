package online.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHost {
    public ServerHost(int hostPort) throws IOException {
        ServerSocket serverSocket = new ServerSocket(hostPort);
        serverSocket.accept();
        serverSocket.close();
    }
    public static void main(String[] args) throws IOException{
        new ServerHost(8000);
    }
}