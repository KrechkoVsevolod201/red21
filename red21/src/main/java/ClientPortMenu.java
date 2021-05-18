import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

public class ClientPortMenu extends JFrame {
    JTextField port;
    Font font = new Font("Serif", Font.BOLD, 35);
    private String portStr;
    public int xPort;
    public ClientPortMenu()
    {
        super("21");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Определяем размер окна и выводим его на экран
        setSize(450, 250);
        setVisible(true);
        try {
            setContentPane(panelClient()); // передаем как параметр в коструктор
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        setVisible(true); //  форма будет видимой
    }

    public JPanel panelClient() throws URISyntaxException  {
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
        port.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                portStr = port.getText();

                try(FileWriter writer = new FileWriter("saved\\playerTwoHostPort.txt", false))
                {
                    // запись всей строки
                    writer.write(portStr);
                    writer.flush();
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }

                new Client();
                new ClientOnlineGame();
                ClientPortMenu.super.setVisible(false);
            }
        });

        portMenu.add(port);

        portMenu.setOpaque(true);
        portMenu.setBackground(Color.DARK_GRAY);
        return portMenu; // возвращаем внешний вид


    }
    /*
    public static void main(String[] args) {
        new ClientMenu();
    }
     */
}
