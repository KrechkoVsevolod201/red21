package online.server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHost {
public String massage1;
public int x1;
    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public ServerHost(int args){
//  стартуем сервер на порту args

        try (ServerSocket server= new ServerSocket(args)){
// становимся в ожидание подключения к сокету под именем - "client" на серверной стороне
            Socket client = server.accept();

// после хэндшейкинга сервер ассоциирует подключающегося клиента с этим сокетом-соединением
            System.out.print("Connection accepted.");

// инициируем каналы для  общения в сокете, для сервера

// канал записи в сокет
            DataOutputStream dout = new DataOutputStream(client.getOutputStream());
            System.out.println("DataOutputStream  created");
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // канал чтения из сокета
            DataInputStream in = new DataInputStream(client.getInputStream());
            System.out.println("DataInputStream created");

// начинаем диалог с подключенным клиентом в цикле, пока сокет не закрыт
            while(!client.isClosed()){

                System.out.println("Server reading from channel");

// сервер ждёт в канале чтения (inputStream) получения данных клиента
                String entry = in.readUTF();

// после получения данных считывает их
                System.out.println("READ from client message - "+entry);

// и выводит в консоль
                System.out.println("Server try writing to channel");

// инициализация проверки условия продолжения работы с клиентом по этому сокету по кодовому слову       - quit
                if(entry.equalsIgnoreCase("quit")){
                    System.out.println("Client initialize connections suicide ...");
                    dout.writeUTF("Server reply - "+entry + " - OK");
                    dout.flush();
                    Thread.sleep(3000);
                    break;
                }

// если условие окончания работы не верно - продолжаем работу - отправляем эхо-ответ  обратно клиенту
                try(FileReader fr = new FileReader("saved\\playerOneSum.txt"))
                {
                    // читаем посимвольно
                    BufferedReader reader = new BufferedReader(fr);
                    String line = reader.readLine();
                    while (line != null) {
                        System.out.println(line);
                        // считываем остальные строки в цикле
                        line = reader.readLine();
                        massage1 = line;
                    }
                }
                catch(IOException ex){

                    System.out.println(ex.getMessage());
                }
                dout.writeUTF(massage1);
                System.out.println("Server Wrote message to client.");

// освобождаем буфер сетевых сообщений (по умолчанию сообщение не сразу отправляется в сеть, а сначала накапливается в специальном буфере сообщений, размер которого определяется конкретными настройками в системе, а метод  - flush() отправляет сообщение не дожидаясь наполнения буфера согласно настройкам системы
                dout.flush();

            }

// если условие выхода - верно выключаем соединения
            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");

            // закрываем сначала каналы сокета !
            in.close();
            dout.close();

            // потом закрываем сам сокет общения на стороне сервера!
            client.close();

            // потом закрываем сокет сервера который создаёт сокеты общения
            // хотя при многопоточном применении его закрывать не нужно
            // для возможности поставить этот серверный сокет обратно в ожидание нового подключения

            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}