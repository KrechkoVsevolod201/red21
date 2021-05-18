import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

public class ClientOnlineGame extends JFrame{
    // Текстовые поля
    JTextField smallField1, smallField2, smallField3, smallField4, smallField5, smallField6;
    public final JButton throwButton1 = new JButton("Бросить кубик p1");
    public final JButton throwButton2 = new JButton("Бросить кубик p2");
    public final JButton rickButton = new JButton("Rick Roll");
    Font font = new Font("Serif", Font.BOLD, 35);
    public int number1, number2, sum1 = 0, sum2 = 0, x1, x2, score1 = 0, score2 = 0;
    public String numberStr1, numberStr2;
    public ClientOnlineGame()
    {
        super("21");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Определяем размер окна и выводим его на экран
        setSize(640, 850);
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

        smallField1.setHorizontalAlignment(JTextField.CENTER);
        smallField1.setFont(font);
        smallField1.setEditable(false);
        totalGUI.add(smallField1);

        // Создание текстовых полей
        smallField2 = new JTextField("0");
        smallField2.setToolTipText("Короткое поле");
        smallField2.setLocation(350, 50); /* надпись синего цвета*/
        smallField2.setSize(150, 60); // размер области надписи

        smallField2.setHorizontalAlignment(JTextField.CENTER);
        smallField2.setEditable(false);
        smallField2.setFont(font);
        totalGUI.add(smallField2);

        // Создание текстовых полей
        smallField3 = new JTextField("0");
        smallField3.setToolTipText("Короткое поле");
        smallField3.setLocation(100, 150); /* надпись синего цвета*/
        smallField3.setSize(150, 60); // размер области надписи

        smallField3.setHorizontalAlignment(JTextField.CENTER);
        smallField3.setEditable(false);
        smallField3.setFont(font);
        totalGUI.add(smallField3);

        // Создание текстовых полей
        smallField4 = new JTextField("0");
        smallField4.setToolTipText("Короткое поле");
        smallField4.setLocation(350, 150); /* надпись синего цвета*/
        smallField4.setSize(150, 60); // размер области надписи

        smallField4.setHorizontalAlignment(JTextField.CENTER);
        smallField4.setEditable(false);
        smallField4.setFont(font);
        totalGUI.add(smallField4);

        // Создание текстовых полей
        smallField5 = new JTextField("0");
        smallField5.setToolTipText("Короткое поле");
        smallField5.setLocation(225, 250); /* надпись синего цвета*/
        smallField5.setSize(75, 60); // размер области надписи

        smallField5.setHorizontalAlignment(JTextField.CENTER);
        smallField5.setEditable(false);
        smallField5.setFont(font);
        totalGUI.add(smallField5);

        // Создание текстовых полей
        smallField6 = new JTextField("0");
        smallField6.setToolTipText("Короткое поле");
        smallField6.setLocation(300, 250); /* надпись синего цвета*/
        smallField6.setSize(75, 60); // размер области надписи

        smallField6.setHorizontalAlignment(JTextField.CENTER);
        smallField6.setEditable(false);
        smallField6.setFont(font);
        totalGUI.add(smallField6);

        // Создадим ярлык (надпись) синего цвета
        JLabel blueLabel = new JLabel("Добро пожаловать, добро пожаловать в очко");
        blueLabel.setLocation(100, -40); /* надпись синего цвета*/
        blueLabel.setFont(new Font("Dialog", Font.TYPE1_FONT, 20));
        blueLabel.setSize(640, 100); // размер области надписи
        //blueLabel.setHorizontalAlignment(0);
        blueLabel.setForeground(Color.blue); // задаём цвет
        totalGUI.add(blueLabel); // добавляем текстовую метку на поверхность

        // Создадим ярлык (надпись) синего цвета
        JLabel poweredLabel = new JLabel("Powered by CHPOK Labs");
        poweredLabel.setLocation(480, 725); /* надпись синего цвета*/
        poweredLabel.setFont(new Font("Dialog", Font.TYPE1_FONT, 12));
        poweredLabel.setSize(640, 100); // размер области надписи
        //blueLabel.setHorizontalAlignment(0);
        poweredLabel.setForeground(Color.blue); // задаём цвет
        totalGUI.add(poweredLabel); // добавляем текстовую метку на поверхность


        // Создаём кнопку---------------
        throwButton2.setLocation(300, 350); // расположение кнопки
        throwButton2.setSize(200, 40); // размер кнопки
        throwButton2.setBackground(new Color(0xC175EF));
        // создаём объект-обработчик события
        throwButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                number2 = new Random().nextInt(6);
                number2++;
                numberStr2 = String.valueOf(number2);
                // Отображение введенного текста
                smallField2.setText(numberStr2);
                try(FileWriter writer = new FileWriter("saved\\playerTwoThrow.txt", false))
                {
                    // запись всей строки
                    writer.write(numberStr2);
                    writer.flush();
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
                try(FileReader reader = new FileReader("saved\\playerTwoThrow.txt"))
                {
                    // читаем посимвольно
                    int c1;
                    while((c1=reader.read())!=-1){
                        x2 = Character.getNumericValue(c1);
                        System.out.print((char)c1);
                    }
                }
                catch(IOException ex){

                    System.out.println(ex.getMessage());
                }
                sum2 = sum2 + x2;
                numberStr2 = String.valueOf(sum2);
                smallField4.setText(numberStr2);
                if(sum2 >= 21){
                    sum2 = 0;
                    score2++;
                    numberStr2 = String.valueOf(score2);
                    smallField6.setText(numberStr2);
                }

                try(FileWriter writer = new FileWriter("saved\\playerTwoSum.txt", false))
                {
                    // запись всей строки
                    numberStr2 = smallField4.getText();
                    writer.write(numberStr2);
                    writer.flush();
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }

                try(FileWriter writer = new FileWriter("saved\\playerTwoScore.txt", false))
                {
                    // запись всей строки
                    numberStr2 = smallField6.getText();
                    writer.write(numberStr2);
                    writer.flush();
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }

            }

        });

        throwButton2.setActionCommand("Open");
        totalGUI.add(throwButton2); // добавляем кнопку на поверхность

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

        JLabel label = new JLabel();
        Image image = Toolkit.getDefaultToolkit().createImage("images/tenor.gif");
        ImageIcon imageIcon = new ImageIcon(image);
        imageIcon.setImageObserver(label);
        label.setIcon(imageIcon);
        label.setLocation(200, 450); // расположение кнопки
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
/*
    public static void main(String[] args) {
        new MainWindow();
    }

 */
}