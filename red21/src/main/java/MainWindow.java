// Использование текстовых полей JTextField
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainWindow extends JFrame
{
    // Текстовые поля
    JTextField smallField;
    public final JButton arrayButton = new JButton("Массив");
    public final JButton functionButton = new JButton("Функция");
    public final JButton rickButton = new JButton("Rick Roll");
    private final JButton calculatorButton = new JButton("Калькулятор");

    public MainWindow()
    {
        super("Текстовые поля");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Определяем размер окна и выводим его на экран
        setSize(640, 320);
        setVisible(true);
        try {
            setContentPane(createContentPane()); // передаем как параметр в коструктор
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        setVisible(true); //  форма будет видимой
    }
    public JPanel createContentPane() throws URISyntaxException {

        // We create a bottom JPanel to place everything on.
        // сначала создаётся "панель", на которой и размещаюся
        // остальные компоненты
        JPanel totalGUI = new JPanel(); // создаём "поверхность"
        totalGUI.setLayout(null);

        // Создание текстовых полей
        smallField = new JTextField();
        smallField.setToolTipText("Короткое поле");
        smallField.setLocation(100, 50); /* надпись синего цвета*/
        smallField.setSize(140, 30); // размер области надписи
        // Слушатель окончания ввода
        smallField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Отображение введенного текста
                JOptionPane.showMessageDialog(MainWindow.this,
                        "Ваше слово: " + smallField.getText());
            }
        });
        totalGUI.add(smallField);



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
        arrayButton.setLocation(300, 100); // расположение кнопки
        arrayButton.setSize(100, 40); // размер кнопки
        arrayButton.setBackground(new Color(0xC175EF));
        // создаём объект-обработчик события


        arrayButton.setActionCommand("Open");
        totalGUI.add(arrayButton); // добавляем кнопку на поверхность

        // Создаём кнопку---------------
        functionButton.setLocation(200, 100); // расположение кнопки
        functionButton.setSize(100, 40); // размер кнопки
        functionButton.setBackground(new Color(0xC175EF));
        // создаём объект-обработчик события


        functionButton.setActionCommand("Open");
        totalGUI.add(functionButton); // добавляем кнопку на поверхность

        // Создаём кнопку---------------
        rickButton.setLocation(200, 150); // расположение кнопки
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

        // Создаём кнопку---------------
        calculatorButton.setLocation(200, 200); // расположение кнопки
        calculatorButton.setSize(200, 40); // размер кнопки
        calculatorButton.setBackground(new Color(0x28A888));



        totalGUI.setOpaque(true);
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