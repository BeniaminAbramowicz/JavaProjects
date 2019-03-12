package paritycontrol;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ParityControl extends JFrame {

    private int prz;
    private int[] data;
    private int[] parityData;
    private Container container;
    private SpringLayout sprLay;
    private JLabel[] jLabel;
    private JTextField[] jTextField;
    private JButton[] jButton;
    private JComboBox jComboBox;
    String[] jComboBoxList = {"-", "tak", "nie"};

    public ParityControl() {
        addContainer();
        setFrameProperties();
        createJLabel(5);
        createJTextField(5);
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
        this.setTitle("Kontrola pażystości");
        this.setSize(550, 400);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createJLabel(int count) {
        jLabel = new JLabel[count];
        for (int i = 0; i < count; i++) {
            jLabel[i] = new JLabel();
        }
        jLabel[0].setText("Wprowadź tekst");
        jLabel[1].setText("Wygenerowany ciąg bitowy");
        jLabel[2].setText("Kod z bitem parzystości");
        jLabel[3].setText("Czy ustawić przekłamanie");
        jLabel[4].setText("Przesyłane dane");
        for (JLabel jl : jLabel) {
            container.add(jl);
        }
    }

    private void createJTextField(int count) {
        jTextField = new JTextField[count];
        for (int i = 0; i < count; i++) {
            jTextField[i] = new JTextField();
        }
        jTextField[0].setColumns(30);
        jTextField[1].setColumns(30);
        jTextField[2].setColumns(30);
        jTextField[3].setColumns(30);
        jTextField[4].setColumns(30);
        for (JTextField jtf : jTextField) {
            container.add(jtf);
        }
    }

    private void createJButton(int count) {
        jButton = new JButton[count];
        for (int i = 0; i < count; i++) {
            jButton[i] = new JButton();
        }
        jButton[0].setText("Generuj dane");
        jButton[1].setText("Wyślij dane");
        for (JButton jb : jButton) {
            container.add(jb);
        }
    }

    private void addJComboBox() {
        jComboBox = new JComboBox(jComboBoxList);
        container.add(jComboBox);
    }

    private void setSpringLayoutConstraint() {
        sprLay.putConstraint(SpringLayout.NORTH, jLabel[0],
                20,
                SpringLayout.NORTH, container);
        sprLay.putConstraint(SpringLayout.NORTH, jTextField[0],
                20,
                SpringLayout.NORTH, container);
        sprLay.putConstraint(SpringLayout.WEST, jTextField[0],
                20,
                SpringLayout.EAST, jLabel[0]);
        sprLay.putConstraint(SpringLayout.NORTH, jLabel[1],
                20,
                SpringLayout.SOUTH, jLabel[0]);
        sprLay.putConstraint(SpringLayout.NORTH, jTextField[1],
                15,
                SpringLayout.SOUTH, jTextField[0]);
        sprLay.putConstraint(SpringLayout.WEST, jTextField[1],
                20,
                SpringLayout.EAST, jLabel[1]);
        sprLay.putConstraint(SpringLayout.NORTH, jLabel[2],
                20,
                SpringLayout.SOUTH, jLabel[1]);
        sprLay.putConstraint(SpringLayout.NORTH, jTextField[2],
                15,
                SpringLayout.SOUTH, jTextField[1]);
        sprLay.putConstraint(SpringLayout.WEST, jTextField[2],
                20,
                SpringLayout.EAST, jLabel[2]);
        sprLay.putConstraint(SpringLayout.NORTH, jLabel[3],
                20,
                SpringLayout.SOUTH, jButton[0]);
        sprLay.putConstraint(SpringLayout.NORTH, jComboBox,
                15,
                SpringLayout.SOUTH, jButton[0]);
        sprLay.putConstraint(SpringLayout.WEST, jComboBox,
                20,
                SpringLayout.EAST, jLabel[3]);
        sprLay.putConstraint(SpringLayout.NORTH, jLabel[4],
                20,
                SpringLayout.SOUTH, jLabel[3]);
        sprLay.putConstraint(SpringLayout.WEST, jTextField[3],
                20,
                SpringLayout.EAST, jLabel[4]);
        sprLay.putConstraint(SpringLayout.NORTH, jTextField[3],
                15,
                SpringLayout.SOUTH, jComboBox);
        sprLay.putConstraint(SpringLayout.NORTH, jButton[0],
                20,
                SpringLayout.SOUTH, jLabel[2]);
        sprLay.putConstraint(SpringLayout.NORTH, jButton[1],
                20,
                SpringLayout.SOUTH, jLabel[4]);
        sprLay.putConstraint(SpringLayout.NORTH, jTextField[4],
                15,
                SpringLayout.SOUTH, jButton[1]);
    }

    private void addJButtonListener() {
        jButton[0].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                generateInput();
                jTextField[1].setText(createNormalString(data));
                generateParityData();
                jTextField[2].setText(createNormalString(parityData));
                jTextField[3].setText(createNormalString(parityData));
            }
        });
        
        jButton[1].addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e){
                if(prz == 1){
                    jTextField[4].setText("Wystąpiło przekłamanie");
                }
                else if(prz == 0){
                    jTextField[4].setText("Pomyślnie przesłano dane");
                }
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
                        case "tak": {
                            prz = 1;
                            break;
                        }
                        case "nie": {
                            prz = 0;
                            break;
                        }
                    }

                }
            }
        });
    }

    private void generateInput() {
        String generatedInput = jTextField[0].getText().toString();
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
        int dataBits = binaryText.length();
        data = new int[dataBits];
        parityData = new int[dataBits + 1];

        for (int i = 0; i < dataBits; i++) {
            data[i] = Integer.parseInt(String.valueOf(binaryText.charAt(i)));
        }
    }

    private void generateParityData() {
        parityData[0] = getParityBit(data);
        for (int i = 0; i < data.length; i++) {
            parityData[i + 1] = data[i];
        }
    }

    private int getParityBit(int[] inputData) {
        int parityBit = inputData[0] ^ inputData[1];
        for (int i = 2; i < inputData.length; i++) {
            parityBit = parityBit ^ inputData[i];
        }
        return parityBit;
    }

    private String createNormalString(int[] generatedInput) {
        StringBuilder builder = new StringBuilder();

        for (int gi : generatedInput) {
            builder.append(gi);
        }
        return builder.toString();
    }

    private void receiveSentData() {
        int parityCheck = getParityBit(parityData);
        if (parityCheck != 0) {
            jTextField[4].setText("Bez błędów");
        } else {
            jTextField[4].setText("Wystąpiły przekłamania");
        }
    }

    public static void main(String[] args) {
        ParityControl k = new ParityControl();
        k.setVisible(true);

    }

}
