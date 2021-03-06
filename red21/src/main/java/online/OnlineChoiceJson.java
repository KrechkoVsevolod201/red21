package online;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

public class OnlineChoiceJson extends JFrame{

    private final JButton hostButton = new JButton("ХОСТ");
    private final  JButton clientButton = new JButton("КЛИЕНТ");

    public OnlineChoiceJson()
    {
        super("21");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Определяем размер окна и выводим его на экран
        setSize(375, 200);
        setVisible(true);
        try {
            setContentPane(panelChoice()); // передаем как параметр в коструктор
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        setVisible(true); //  форма будет видимой
    }

    public JPanel panelChoice() throws URISyntaxException  {
        // We create a bottom JPanel to place everything on.
        // сначала создаётся "панель", на которой и размещаюся
        // остальные компоненты
        JPanel menu = new JPanel(); // создаём "поверхность"
        menu.setLayout(null);

        // Создаём кнопку---------------
        hostButton.setLocation(50, 50); // расположение кнопки
        hostButton.setSize(100, 50); // размер кнопки
        hostButton.setBackground(new Color(0x6DC911));
        hostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OnlineJsonHost();
                OnlineChoiceJson.super.setVisible(false);
            }
        });

        hostButton.setActionCommand("Open");
        menu.add(hostButton); // добавляем кнопку на поверхность

        // Создаём кнопку---------------
        clientButton.setLocation(200, 50); // расположение кнопки
        clientButton.setSize(100, 50); // размер кнопки
        clientButton.setBackground(new Color(0x157AA1));
        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OnlineJsonClient();
                OnlineChoiceJson.super.setVisible(false);

            }
        });

        clientButton.setActionCommand("Open");
        menu.add(clientButton); // добавляем кнопку на поверхность

        menu.setOpaque(true);
        return menu; // возвращаем внешний вид


    }

}