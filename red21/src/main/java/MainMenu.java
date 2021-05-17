import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainMenu extends JFrame {

    private final  JButton online = new JButton("Online");
    private final  JButton offline = new JButton("Offline");

    public MainMenu()
    {
        super("21");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Определяем размер окна и выводим его на экран
        setSize(640, 850);
        setVisible(true);
        try {
            setContentPane(panel()); // передаем как параметр в коструктор
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        setVisible(true); //  форма будет видимой
    }

    public JPanel panel() throws URISyntaxException  {
        // We create a bottom JPanel to place everything on.
        // сначала создаётся "панель", на которой и размещаюся
        // остальные компоненты
        JPanel menushka = new JPanel(); // создаём "поверхность"
        menushka.setLayout(null);

        // Создаём кнопку---------------
        online.setLocation(200, 400); // расположение кнопки
        online.setSize(200, 40); // размер кнопки
        online.setBackground(new Color(0xFF0000));


        online.setActionCommand("Open");
        menushka.add(online); // добавляем кнопку на поверхность

        menushka.setOpaque(true);
        menushka.setBackground(Color.DARK_GRAY);
        return menushka; // возвращаем внешний вид


    }

}
