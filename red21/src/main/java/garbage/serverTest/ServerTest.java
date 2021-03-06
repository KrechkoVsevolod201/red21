package garbage.serverTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет
    public ServerTest(int args){
    //public static void main(String[] args) {
        try {
            try {
                    server = new ServerSocket(8080); // серверсокет прослушивает порт 4004
                    System.out.println("Сервер запущен!"); // хорошо бы серверу
                    //   объявить о своем запуске
                    clientSocket = server.accept(); // accept() будет ждать пока
                    //кто-нибудь не захочет подключиться
                    try { // установив связь и воссоздав сокет для общения с клиентом можно перейти
                        // к созданию потоков ввода/вывода.
                        // теперь мы можем принимать сообщения
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        // и отправлять
                        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                        String word = in.readLine(); // ждём пока клиент что-нибудь нам напишет
                        try (FileWriter writer = new FileWriter("saved\\playerTwoSum.txt", false)) {
                            // запись всей строки
                            //numberStr2 = smallField4.getText();
                            writer.write(word);
                            writer.flush();
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                        System.out.println(word);
                        // не долго думая отвечает клиенту
                        out.write("Привет, это Сервер! Подтверждаю, вы написали : " + word + "\n");
                        out.flush(); // выталкиваем все из буфера

                    } finally { // в любом случае сокет будет закрыт
                        clientSocket.close();
                        // потоки тоже хорошо бы закрыть
                        in.close();
                        out.close();
                    }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        new ServerTest(4004);
    }
}
