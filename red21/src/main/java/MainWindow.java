// Использование текстовых полей JTextField
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainWindow extends JFrame
{
    // Текстовые поля
    JTextField smallField1, smallField2;
    private Timer timer;
    public final JButton throwButton1 = new JButton("Бросить кубик p1");
    public final JButton throwButton2 = new JButton("Бросить кубик p2");
    public final JButton rickButton = new JButton("Rick Roll");
    Font font = new Font("Serif", Font.BOLD, 35);
    public MainWindow()
    {
        super("21");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Определяем размер окна и выводим его на экран
        setSize(640, 360);
        setVisible(true);
        try {
            setContentPane(createContentPane()); // передаем как параметр в коструктор
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        setVisible(true); //  форма будет видимой
    }




    public JPanel createContentPane() throws URISyntaxException  {

        // We create a bottom JPanel to place everything on.
        // сначала создаётся "панель", на которой и размещаюся
        // остальные компоненты
        JPanel totalGUI = new JPanel(); // создаём "поверхность"
        totalGUI.setLayout(null);


        // Создание текстовых полей
        smallField1 = new JTextField("0");
        smallField1.setToolTipText("Короткое поле");
        smallField1.setLocation(100, 50); /* надпись синего цвета*/
        smallField1.setSize(150, 60); // размер области надписи
        // Слушатель окончания ввода
        smallField1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Отображение введенного текста
                JOptionPane.showMessageDialog(MainWindow.this,
                        "Ваше слово: " + smallField1.getText());
            }
        });
        smallField1.setHorizontalAlignment(JTextField.CENTER);
        smallField1.setFont(font);
        smallField1.setEditable(false);
        totalGUI.add(smallField1);

        // Создание текстовых полей
        smallField2 = new JTextField("0");
        smallField2.setToolTipText("Короткое поле");
        smallField2.setLocation(350, 50); /* надпись синего цвета*/
        smallField2.setSize(150, 60); // размер области надписи
        // Слушатель окончания ввода
        smallField2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Отображение введенного текста
                JOptionPane.showMessageDialog(MainWindow.this,
                        "Ваше слово: " + smallField2.getText());
            }
        });
        smallField2.setHorizontalAlignment(JTextField.CENTER);
        smallField2.setEditable(false);
        smallField2.setFont(font);
        totalGUI.add(smallField2);



        // Создадим ярлык (надпись) синего цвета
        JLabel blueLabel = new JLabel("Добро пожаловать, добро пожаловать в программу 17");
        blueLabel.setLocation(100, -40); /* надпись синего цвета*/
        blueLabel.setFont(new Font("Dialog", Font.ROMAN_BASELINE, 17));
        blueLabel.setSize(640, 100); // размер области надписи
        //blueLabel.setHorizontalAlignment(0);
        blueLabel.setForeground(Color.blue); // задаём цвет
        totalGUI.add(blueLabel); // добавляем текстовую метку на поверхность

        // Создадим ярлык (надпись) синего цвета
        JLabel poweredLabel = new JLabel("Powered by CHPOK Labs");
        poweredLabel.setLocation(480, 210); /* надпись синего цвета*/
        poweredLabel.setFont(new Font("Dialog", Font.ROMAN_BASELINE, 12));
        poweredLabel.setSize(640, 100); // размер области надписи
        //blueLabel.setHorizontalAlignment(0);
        poweredLabel.setForeground(Color.blue); // задаём цвет
        totalGUI.add(poweredLabel); // добавляем текстовую метку на поверхность

        // Создаём кнопку---------------
        throwButton1.setLocation(100, 200); // расположение кнопки
        throwButton1.setSize(200, 40); // размер кнопки
        throwButton1.setBackground(new Color(0xC175EF));
        // создаём объект-обработчик события


        throwButton1.setActionCommand("Open");
        totalGUI.add(throwButton1); // добавляем кнопку на поверхность

        // Создаём кнопку---------------
        throwButton2.setLocation(300, 200); // расположение кнопки
        throwButton2.setSize(200, 40); // размер кнопки
        throwButton2.setBackground(new Color(0xC175EF));
        // создаём объект-обработчик события


        throwButton2.setActionCommand("Open");
        totalGUI.add(throwButton2); // добавляем кнопку на поверхность

        // Создаём кнопку---------------
        rickButton.setLocation(200, 250); // расположение кнопки
        rickButton.setSize(200, 40); // размер кнопки
        rickButton.setBackground(new Color(0xFF0000));

        final URI uri = new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstleyVEVO");
        class OpenUrlAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                open(uri);
            }
        }
        rickButton.setActionCommand("Open");
        rickButton.addActionListener(new OpenUrlAction());
        totalGUI.add(rickButton); // добавляем кнопку на поверхность
/*
        String path = "C:\\Users\\GachiBoy\\Documents\\GitHub\\red21\\red21\\images\\tenor.gif";
        File file = new File(path);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel label = new JLabel(new ImageIcon(image));
        label.setLocation(200, 350); // расположение кнопки
        label.setSize(250, 300); // размер кнопки
        totalGUI.add(label);

 */

        JLabel label = new JLabel("test");
        Image image = Toolkit.getDefaultToolkit().createImage("images/tenor.gif");
        ImageIcon imageIcon = new ImageIcon(image);
        imageIcon.setImageObserver(label);
        label.setIcon(imageIcon);
        label.setLocation(200, 350); // расположение кнопки
        label.setSize(250, 300); // размер кнопки
        totalGUI.add(label);

        totalGUI.setOpaque(true);
        totalGUI.setBackground(Color.DARK_GRAY);
        return totalGUI; // возвращаем внешний вид
    }

    private static void open(URI uri) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) { /* TODO: error handling */ }
        } else { /* TODO: error handling */ }
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}