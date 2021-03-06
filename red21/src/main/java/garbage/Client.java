package garbage;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
public int portClient;
    /**
     *
     * @param args
     * @throws InterruptedException
     */
    //public static void main(String[] args) throws InterruptedException {
 public Client(int args){

// запускаем подключение сокета по известным координатам и нициализируем приём сообщений с консоли клиента      
        try(Socket socket = new Socket("127.0.0.1", args);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream()); )
        {

            System.out.println("garbage.Client connected to socket.");
            System.out.println();
            System.out.println("garbage.Client writing channel = dos & reading channel = ois initialized.");

// проверяем живой ли канал и работаем если живой           
            while(socket.isOutputShutdown()) {
                try {

                    while (true) {


// ждём консоли клиента на предмет появления в ней данных                   
                        if (br.ready()) {

// данные появились - работаем                      
                            System.out.println("garbage.Client start writing in channel...");
                            Thread.sleep(1000);
                            String clientCommand = br.readLine();

                            try (FileWriter writer = new FileWriter("saved\\playerOneSum.txt", false)) {
                                // запись всей строки
                                //numberStr2 = smallField4.getText();
                                writer.write(clientCommand);
                                writer.flush();
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }

// пишем данные с консоли в канал сокета для сервера            
                            dos.writeUTF(clientCommand);
                            dos.flush();
                            System.out.println("Clien sent message " + clientCommand + " to server.");
                            Thread.sleep(1000);
// ждём чтобы сервер успел прочесть сообщение из сокета и ответить      

// проверяем условие выхода из соединения           
                            if (clientCommand.equalsIgnoreCase("quit")) {

// если условие выхода достигнуто разъединяемся             
                                System.out.println("garbage.Client kill connections");
                                Thread.sleep(2000);

// смотрим что нам ответил сервер на последок перед закрытием ресурсов          
                                if (ois.read() > -1) {
                                    System.out.println("reading...");
                                    String in = ois.readUTF();
                                    System.out.println(in);
                                }

// после предварительных приготовлений выходим из цикла записи чтения               
                                break;
                            }

// если условие разъединения не достигнуто продолжаем работу            
                            System.out.println("garbage.Client sent message & start waiting for data from server...");
                            Thread.sleep(2000);

// проверяем, что нам ответит сервер на сообщение(за предоставленное ему время в паузе он должен был успеть ответить)           
                            if (ois.read() > -1) {

// если успел забираем ответ из канала сервера в сокете и сохраняем её в ois переменную,  печатаем на свою клиентскую консоль                       
                                System.out.println("reading...");
                                String in = ois.readUTF();
                                System.out.println(in);
                            }
                        }
                    }
                } finally {
                    // если условие выхода - верно выключаем соединения
                    System.out.println("Client disconnected");
                    System.out.println("Closing connections & channels.");

                    // закрываем сначала каналы сокета !
                    ois.close();
                    dos.close();

                    // потом закрываем сам сокет общения на стороне сервера!
                    socket.close();
                    System.out.println("Closing connections & channels - DONE.");
                }
            }
// на выходе из цикла общения закрываем свои ресурсы
            System.out.println("Closing connections & channels on clientSide - DONE.");

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 }
}