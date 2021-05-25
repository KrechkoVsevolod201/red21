package httpServerTest;

import java.io.*;
import java.net.*;

class HTTPClient
{
    // первый аргумент - имя файла, содержащего HTTP запрос
    // предполагается, что запрос не будет больше 64 килобайт
    // второй - имя файла, куда будет слит ответ сервера
    public static void main(String args[])
    {
        try
        {
            byte buf[] = new byte[64*1024];
            int r;

            // читаем файл с запросом в переменную header
            FileInputStream fis = new FileInputStream(args[0]);
            r = fis.read(buf);
            String header = new String(buf, 0, r);
            fis.close();

            // выделяем из строки запроса хост, порт и URL ресурса
            // для выделения используется специальнонаписанная ф-ия extract
            String host = extract(header, "Host:", "\n");

            // если не найден параметр Host - ошибка
            if(host == null)
            {
                System.out.println("invalid request:\n"+header);
                return;
            }

            // находим порт сервера, по умолчанию он - 80
            int port = host.indexOf(":",0);
            if(port < 0) port = 80;
            else
            {
                port = Integer.parseInt(host.substring(port+1));
                host = host.substring(0, port);
            }

            // открываем сокет до сервера
            Socket s = new Socket(host, port);

            // пишем туда HTTP request
            s.getOutputStream().write(header.getBytes());

            // получаем поток данных от сервера
            InputStream is = s.getInputStream();

            // Открываем для записи файл, куда будет слит лог
            FileOutputStream fos = new FileOutputStream(args[1]);

            // читаем ответ сервера, одновременно сливая его в открытый файл
            r = 1;
            while(r > 0)
            {
                r = is.read(buf);
                if(r > 0)
                    fos.write(buf, 0, r);
            }

            // закрываем файл
            fos.close();
            s.close();
        }
        catch(Exception e)
        {e.printStackTrace();} // вывод исключений
    }

    // "вырезает" из строки str часть, находящуюся между строками start и end
    // если строки end нет, то берётся строка после start
    // если кусок не найден, возвращается null
    // для поиска берётся строка до "\n\n" или "\r\n\r\n", если таковые присутствуют
    protected static String extract(String str, String start, String end)
    {
        int s = str.indexOf("\n\n", 0), e;
        if(s < 0) s = str.indexOf("\r\n\r\n", 0);
        if(s > 0) str = str.substring(0, s);
        s = str.indexOf(start, 0)+start.length();
        if(s < start.length()) return null;
        e = str.indexOf(end, s);
        if(e < 0) e = str.length();
        return (str.substring(s, e)).trim();
    }
}