package online;

import instruments.JsonReader;
import instruments.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Random;


public class OnlineJsonClient extends JFrame{
    // Текстовые поля
    JTextField smallField1, smallField2, smallField3, smallField4, smallField5, smallField6;
    private final JButton reload2 = new JButton("Обновить");
    private final JButton throwButton2 = new JButton("Бросить кубик p1");
    private final JButton rickButton = new JButton("Rick Roll");
    Font font = new Font("Serif", Font.BOLD, 35);
    public int number1, number2, sum1 = 0, sum2 = 0, x1, x2, score1 = 0, score2 = 0, step;
    public String port = "4321";
    public String ip = "192.168.1.44";
    public String numberStr1, numberStr2, stepStr;
    public OnlineJsonClient()
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

        try(FileWriter writer = new FileWriter("saved\\playerOneThrow.txt", false))
        {
            // запись всей строки
            writer.write("0");
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        new JsonWriter("playerOneThrow");

        try(FileWriter writer = new FileWriter("saved\\playerOneSum.txt", false))
        {
            // запись всей строки
            writer.write("0");
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        new JsonWriter("playerOneSum");

        try(FileWriter writer = new FileWriter("saved\\playerOneScore.txt", false))
        {
            // запись всей строки
            writer.write("0");
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        new JsonWriter("playerOneScore");

        // Создаём кнопку---------------
        throwButton2.setLocation(300, 350); // расположение кнопки
        throwButton2.setSize(200, 40); // размер кнопки
        throwButton2.setBackground(new Color(0xC175EF));
        // создаём объект-обработчик события
        throwButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //new ClientTest(port);
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
                new JsonWriter("playerTwoThrow");
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
                new JsonWriter("playerTwoThrow");
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
                new JsonWriter("playerTwoSum");

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
                new JsonWriter("playerTwoScore");

                try(FileReader fr = new FileReader("saved\\playerOneSum.txt"))
                {
                    // читаем посимвольно
                    BufferedReader reader = new BufferedReader(fr);
                    String line = reader.readLine();
                    while (line != null) {
                        System.out.println(line);
                        // считываем остальные строки в цикле
                        line = reader.readLine();
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                step++;
                stepStr = String.valueOf(step);

                try(FileWriter writer = new FileWriter("saved\\step.txt", false))
                {
                    // запись всей строки
                    writer.write(stepStr);
                    writer.flush();
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
                new JsonWriter("step");

                throwButton2.setEnabled(false);

            }

        });
        throwButton2.setEnabled(false);
        throwButton2.setActionCommand("Open");
        totalGUI.add(throwButton2); // добавляем кнопку на поверхность



        // Создаём кнопку---------------
        reload2.setLocation(100, 350); // расположение кнопки
        reload2.setSize(200, 40); // размер кнопки
        reload2.setBackground(new Color(0xC175EF));
        // создаём объект-обработчик события
        reload2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new JsonReader("playerOneScore", ip, port);
                new JsonReader("playerOneSum", ip, port);
                new JsonReader("playerOneThrow", ip, port);
                new JsonReader("playerTwoThrow", ip, port);
                new JsonReader("playerTwoSum", ip, port);
                new JsonReader("playerTwoScore", ip, port);

                //Чтение

                try(FileReader fr = new FileReader("saved\\playerOneThrow.txt"))
                {
                    // читаем посимвольно
                    BufferedReader reader = new BufferedReader(fr);
                    String line = reader.readLine();
                    smallField1.setText(line);

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                try(FileReader fr = new FileReader("saved\\playerTwoThrow.txt"))
                {
                    // читаем посимвольно
                    BufferedReader reader = new BufferedReader(fr);
                    String line = reader.readLine();
                    smallField2.setText(line);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                try(FileReader fr = new FileReader("saved\\playerOneSum.txt"))
                {
                    // читаем посимвольно
                    BufferedReader reader = new BufferedReader(fr);
                    String line = reader.readLine();
                    smallField3.setText(line);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                try(FileReader fr = new FileReader("saved\\playerTwoSum.txt"))
                {
                    // читаем посимвольно
                    BufferedReader reader = new BufferedReader(fr);
                    String line = reader.readLine();
                    smallField4.setText(line);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                try(FileReader fr = new FileReader("saved\\playerOneScore.txt"))
                {
                    // читаем посимвольно
                    BufferedReader reader = new BufferedReader(fr);
                    String line = reader.readLine();
                    smallField5.setText(line);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                try(FileReader fr = new FileReader("saved\\playerTwoScore.txt"))
                {
                    // читаем посимвольно
                    BufferedReader reader = new BufferedReader(fr);
                    String line = reader.readLine();
                    smallField6.setText(line);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                new JsonReader("step", ip, port);

                try(FileReader fr = new FileReader("saved\\step.txt"))
                {
                    // читаем посимвольно
                    BufferedReader reader = new BufferedReader(fr);
                    String line = reader.readLine();
                    stepStr = line;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                step = Integer.valueOf(stepStr);

                if(step%2 == 1){
                    throwButton2.setEnabled(true);
                }

            }
        });

        reload2.setActionCommand("Open");
        totalGUI.add(reload2); // добавляем кнопку на поверхность

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
}