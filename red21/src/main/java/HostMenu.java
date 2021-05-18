import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class HostMenu extends JFrame{
    JTextField port;
    Font font = new Font("Serif", Font.BOLD, 35);
    public HostMenu()
    {
        super("21");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Определяем размер окна и выводим его на экран
        setSize(450, 250);
        setVisible(true);
        try {
            setContentPane(panelHost()); // передаем как параметр в коструктор
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        setVisible(true); //  форма будет видимой
    }

    public JPanel panelHost() throws URISyntaxException  {
        // We create a bottom JPanel to place everything on.
        // сначала создаётся "панель", на которой и размещаюся
        // остальные компоненты
        JPanel portMenu = new JPanel(); // создаём "поверхность"
        portMenu.setLayout(null);

        // Создание текстовых полей
        port = new JTextField("0");
        port.setToolTipText("Короткое поле");
        port.setLocation(100, 50); /* надпись синего цвета*/
        port.setSize(150, 60); // размер области надписи

        port.setHorizontalAlignment(JTextField.CENTER);
        port.setFont(font);
        port.setDocument
                (new JTextFieldLimit(4));
        portMenu.add(port);

        portMenu.setOpaque(true);
        portMenu.setBackground(Color.DARK_GRAY);
        return portMenu; // возвращаем внешний вид


    }
    public static void main(String[] args) {
        new HostMenu();
    }
}
