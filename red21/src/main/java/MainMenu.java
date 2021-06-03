import instruments.Sound;
import offline.OfflineGame;
import online.JsonOrTxt;
import online.OnlineChoice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

public class MainMenu extends JFrame {

    private final  JButton online = new JButton("Online");
    private final  JButton offline = new JButton("Offline");

    public MainMenu()
    {
        super("21");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Определяем размер окна и выводим его на экран
        setSize(450, 350);
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
/*
        JLabel label = new JLabel();
        Image image = Toolkit.getDefaultToolkit().createImage("images/menuTheme.jpg");
        ImageIcon imageIcon = new ImageIcon(image);
        imageIcon.setImageObserver(label);
        label.setIcon(imageIcon);
        //label.setLocation(100, 100); // расположение кнопки
        //label.setSize(1000, 1000); // размер кнопки
        menushka.add(label);
*/
        // Создаём кнопку---------------
        online.setLocation(200, 100); // расположение кнопки
        online.setSize(200, 40); // размер кнопки
        online.setBackground(new Color(0x6DC911));
        online.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sound.playSound("sounds\\boyNextDoor.wav");
                new JsonOrTxt();
            }
        });


        online.setActionCommand("Open");
        menushka.add(online); // добавляем кнопку на поверхность

        // Создаём кнопку---------------
        offline.setLocation(200, 200); // расположение кнопки
        offline.setSize(200, 40); // размер кнопки
        offline.setBackground(new Color(0x157AA1));
        offline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OfflineGame();
            }
        });

        offline.setActionCommand("Open");
        menushka.add(offline); // добавляем кнопку на поверхность

        menushka.setOpaque(true);
        //menushka.setBackground(Color.DARK_GRAY);
        return menushka; // возвращаем внешний вид


    }
    public static void main(String[] args) {
        new MainMenu();
        Sound.playSound("sounds\\welcomeToTheClubBuddy.wav");
    }
}
