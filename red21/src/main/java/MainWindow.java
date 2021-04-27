// Использование текстовых полей JTextField
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame
{
    // Текстовые поля
    JTextField smallField, bigField;

    public MainWindow()
    {
        super("Текстовые поля");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Создание текстовых полей
        smallField = new JTextField(15);
        smallField.setToolTipText("Короткое поле");
        bigField = new JTextField("Текст поля", 15);
        bigField.setToolTipText("Длиное поле");
        // Настройка шрифта
        bigField.setFont(new Font("Dialog", Font.PLAIN, 15));
        bigField.setHorizontalAlignment(JTextField.RIGHT);
        // Слушатель окончания ввода
        smallField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Отображение введенного текста
                JOptionPane.showMessageDialog(MainWindow.this,
                        "Ваше слово: " + smallField.getText());
            }
        });
        // Поле с паролем
        JPasswordField password = new JPasswordField(15);
        password.setEchoChar('*');
        // Создание панели с текстовыми полями
        JPanel contents = new JPanel(new FlowLayout(FlowLayout.CENTER));

        contents.add(smallField);
        contents.add(bigField);
        contents.add(password);
        setContentPane(contents);
        // Определяем размер окна и выводим его на экран
        setSize(480, 640);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MainWindow();
    }
}