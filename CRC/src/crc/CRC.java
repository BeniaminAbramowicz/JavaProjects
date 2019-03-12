package crc;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

public class CRC extends JFrame {

    private int[] data;
    private int[] div;
    private int[] divisor;
    private int[] rem;
    private int[] crc;
    private int[] crc2;
    private int jcrc;

    private int selectedCrc;
    private int dataBits;
    private int divisorBits;
    private int errorBit;

    private Container container;
    private SpringLayout sprLay;
    private JLabel[] jLabel;
    private JTextField[] jTextField;
    private JButton[] jButton;
    private JComboBox jComboBox;
    private JComboBox jComboBox2;
    String[] jComboBoxList = {"-", "CRC-12", "CRC-16", "CRC-32"};
    String[] jComboBoxList2 = {"-", "tak", "nie"};

    public CRC() {
        addContainer();
        setFrameProperties();
        createJLabel(7);
        createJTextField(6);
        createJButton(2);
        addJComboBox();
        setSpringLayoutConstraint();
        addJButtonListener();
        addJComboBoxListener();
    }

    private void addContainer() {
        sprLay = new SpringLayout();
        container = this.getContentPane();
        container.setLayout(sprLay);
    }

    private void setFrameProperties() {
        this.setTitle("CRC");
        this.setSize(1320, 600);
        this.setLocation(50, 50);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createJLabel(int count) {
        jLabel = new JLabel[count];
        for (int i = 0; i < count; i++) {
            jLabel[i] = new JLabel();
        }
        jLabel[0].setText("Wybierz typ CRC");
        jLabel[1].setText("Wprowadz tekst");
        jLabel[2].setText("Ciag bitowy");
        jLabel[3].setText("Kod nadmiarowy");
        jLabel[4].setText("Wygenerowany kod");
        jLabel[5].setText("Przekłamanie bitu");
        jLabel[6].setText("Kod po rozkodowaniu");

        for (JLabel jl : jLabel) {
            container.add(jl);
        }
    }

    private void createJTextField(int count) {
        jTextField = new JTextField[count];
        for (int i = 0; i < count; i++) {
            jTextField[i] = new JTextField();
        }
        jTextField[0].setColumns(100);
        jTextField[1].setColumns(100);
        jTextField[2].setColumns(100);
        jTextField[3].setColumns(100);
        jTextField[4].setColumns(100);
        jTextField[5].setColumns(30);
        for (JTextField jtf : jTextField) {
            container.add(jtf);
        }
    }

    private void createJButton(int count) {
        jButton = new JButton[count];
        for (int i = 0; i < count; i++) {
            jButton[i] = new JButton();
        }
        jButton[0].setText("Generowanie danych");
        jButton[1].setText("Wyslij dane");
        for (JButton jb : jButton) {
            container.add(jb);
        }
    }

    private void addJComboBox() {
        jComboBox = new JComboBox(jComboBoxList);
        container.add(jComboBox);
        jComboBox2 = new JComboBox(jComboBoxList2);
        container.add(jComboBox2);
    }

    private void setSpringLayoutConstraint() {
        sprLay.putConstraint(SpringLayout.NORTH, jLabel[0],
                20,
                SpringLayout.NORTH, container);

        sprLay.putConstraint(SpringLayout.NORTH, jComboBox,
                20,
                SpringLayout.NORTH, container);
        sprLay.putConstraint(SpringLayout.WEST, jComboBox,
                25,
                SpringLayout.EAST, jLabel[0]);
        sprLay.putConstraint(SpringLayout.NORTH, jLabel[1],
                30,
                SpringLayout.SOUTH, jLabel[0]);
        sprLay.putConstraint(SpringLayout.NORTH, jLabel[1],
                30,
                SpringLayout.SOUTH, jLabel[0]);
        sprLay.putConstraint(SpringLayout.NORTH, jTextField[0],
                20,
                SpringLayout.SOUTH, jComboBox);
        sprLay.putConstraint(SpringLayout.WEST, jTextField[0],
                30,
                SpringLayout.EAST, jLabel[1]);
        sprLay.putConstraint(SpringLayout.NORTH, jLabel[2],
                30,
                SpringLayout.SOUTH, jLabel[1]);
        sprLay.putConstraint(SpringLayout.NORTH, jTextField[1],
                25,
                SpringLayout.SOUTH, jTextField[0]);
        sprLay.putConstraint(SpringLayout.WEST, jTextField[1],
                30,
                SpringLayout.EAST, jLabel[2]);
        sprLay.putConstraint(SpringLayout.NORTH, jLabel[3],
                30,
                SpringLayout.SOUTH, jLabel[2]);
        sprLay.putConstraint(SpringLayout.NORTH, jTextField[2],
                25,
                SpringLayout.SOUTH, jTextField[1]);
        sprLay.putConstraint(SpringLayout.WEST, jTextField[2],
                30,
                SpringLayout.EAST, jLabel[3]);
        sprLay.putConstraint(SpringLayout.NORTH, jButton[0],
                25,
                SpringLayout.SOUTH, jLabel[4]);
        sprLay.putConstraint(SpringLayout.NORTH, jLabel[4],
                25,
                SpringLayout.SOUTH, jLabel[3]);
        sprLay.putConstraint(SpringLayout.NORTH, jTextField[3],
                25,
                SpringLayout.SOUTH, jTextField[2]);
        sprLay.putConstraint(SpringLayout.WEST, jTextField[3],
                25,
                SpringLayout.EAST, jLabel[4]);
        sprLay.putConstraint(SpringLayout.NORTH, jLabel[5],
                25,
                SpringLayout.SOUTH, jButton[0]);
        sprLay.putConstraint(SpringLayout.NORTH, jComboBox2,
                65,
                SpringLayout.SOUTH, jTextField[3]);
        sprLay.putConstraint(SpringLayout.WEST, jComboBox2,
                25,
                SpringLayout.EAST, jLabel[5]);
        sprLay.putConstraint(SpringLayout.NORTH, jButton[1],
                50,
                SpringLayout.SOUTH, jComboBox2);
        sprLay.putConstraint(SpringLayout.NORTH, jLabel[6],
                50,
                SpringLayout.SOUTH, jButton[1]);
        sprLay.putConstraint(SpringLayout.NORTH, jTextField[4],
                125,
                SpringLayout.SOUTH, jComboBox2);
        sprLay.putConstraint(SpringLayout.WEST, jTextField[4],
                50,
                SpringLayout.EAST, jLabel[6]);
        sprLay.putConstraint(SpringLayout.NORTH, jTextField[5],
                50,
                SpringLayout.SOUTH, jTextField[4]);
        sprLay.putConstraint(SpringLayout.WEST, jTextField[5],
                20,
                SpringLayout.WEST, container);
    }

    private void addJButtonListener() {
        jButton[0].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gdw();
                jTextField[1].setText(createNormalString(data));
                gcrc();
                jTextField[2].setText(createNormalString(div));
                jTextField[3].setText(createNormalString(crc));

            }
        });

        jButton[1].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(Arrays.toString(rem));
                detectErrorInData();
                System.out.println(Arrays.toString(rem));
                jTextField[4].setText(createNormalString(rem));
            }
        });
    }

    private void addJComboBoxListener() {
        jComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object item = e.getItem();
                    switch (item.toString()) {
                        case "CRC-12": {
                            divisorBits = CrcHelper.CRC12_DIVISOR_BITS_COUNT;
                            divisor = CrcHelper.CRC12_DIVISOR;
                            break;
                        }
                        case "CRC-16": {
                            divisorBits = CrcHelper.CRC16_DIVISOR_BITS_COUNT;
                            divisor = CrcHelper.CRC16_DIVISOR;
                            break;
                        }
                        case "CRC-32": {
                            divisorBits = CrcHelper.CRC32_DIVISOR_BITS_COUNT;
                            divisor = CrcHelper.CRC32_DIVISOR;
                            break;
                        }
                    }

                }
            }
        });
        jComboBox2.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object item = e.getItem();
                    switch (item.toString()) {
                        case "tak": {
                           
                            crc2 = new int[crc.length];
                            System.arraycopy(crc, 0, crc2, 0, crc.length);
                            crc2[5] = (crc2[5] + 1) % 2;
                            jcrc = 1;
                            
                            break;
                           
                        }
                        case "nie":{
                            jcrc = 0;
                            break;
                        }
                    }
                }
            }
        });
    }

    private void gdw() {
        String generatedInput = jTextField[0].getText();
        byte[] inputBytes = generatedInput.getBytes();

        StringBuilder binaryInput = new StringBuilder();
        for (byte b : inputBytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binaryInput.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        String binaryText = binaryInput.toString();
        dataBits = binaryText.length();

        data = new int[dataBits];
        div = new int[dataBits + divisorBits - 1];
        rem = new int[dataBits + divisorBits - 1];
        crc = new int[dataBits + divisorBits - 1];

        for (int i = 0; i < dataBits; i++) {
            data[i] = Integer.parseInt(String.valueOf(binaryText.charAt(i)));
        }
    }

    private void gcrc() {
        System.arraycopy(data, 0, div, 0, data.length);
        System.arraycopy(div, 0, rem, 0, div.length);
        rem = divide(divisor, rem);
        for (int i = 0; i < div.length; i++) {
            crc[i] = (div[i] ^ rem[i]);
        }
    }

    private int[] divide(int divisor[], int rem[]) {
        int current = 0;

        while (true) {
            for (int i = 0; i < divisor.length; i++) {
                rem[current + i] = rem[current + i] ^ divisor[i];
            }
            while (rem[current] == 0 && current != rem.length - 1) {
                current++;
            }
            if ((rem.length - current) < divisor.length) {
                break;
            }
        }
        return rem;
    }

    private String createNormalString(int[] generatedInput) {
        StringBuilder builder = new StringBuilder();

        for (int gi : generatedInput) {
            builder.append(gi);
        }
        return builder.toString();
    }

    private void detectErrorInData() {
        int [] receivedData;
        if(jcrc == 1){
            receivedData = crc2;
        }
        else{
            receivedData = crc;
        }
        for (int i = 0; i < crc.length; i++) {
            crc[i] = receivedData[i];
            rem[i] = crc[i];
        }
        rem = divide(divisor, rem);
        for (int i = 0; i < data.length; i++) {
            data[i] = (crc[i] ^ rem[i]);
        }
        for (int i = 0; i < rem.length; i++) {
            if (rem[i] != 0) {

                jTextField[5].setText("Wykryto błąd w odebranych danych");
                break;
            }
            if (i == (rem.length - 1)) {

                jTextField[5].setText("Dane przesłano bez przekłamań");
                break;
            }
        }
    }

    public static void main(String[] args) {
        CRC k = new CRC();
        k.setVisible(true);
    }

}
