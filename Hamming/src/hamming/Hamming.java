package hamming;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import javax.swing.JComboBox;


public class Hamming extends JFrame {

    private final String JFRAME_TITLE = "Kodowanie Hamminga";
    private final String INPUT_JLABEL = "Dane wejściowe:";
    private final String BINARY_OUTPUT_JLABEL = "Wygenerowany ciąg bitowy:";
    private final String HAMMING_JLABEL = "Wygenerowany kod Hamminga:";
    private final String ERROR_BIT_POSITION_JLABEL = "Dane do wysłania:";
    private final String DATA_GENERATE_BUTTON = "GENERUJ DANE WEJŚCIOWE";
    private final String ERROR_SET_BUTTON = "WYŚLIJ DANE";
    private final String ERROR_CLEAR_BUTTON = "WYCZYŚĆ BŁĄD";
    private final int EMPTY_BORDER_PADING = 5;
    private final int JTEXT_FIELD_WIDTH = 35;
    private int data[];
    private int[] hamming;
    private int errorBit;
    private final ArrayList parityBitsPositions = new ArrayList();
    private JLabel resultInfo;
    private int[] rem = new int[50];
    private int[] divisor = new int[5];
    
    private Container cont;
    private JTextField inputDataField, binaryOutputField,binaryOutputField2, 
            inputErrorPositionField;
    
    public Hamming() {
        createContainer();
        setJFrameProperties();
        addInputDataLayout();
        addGenerateButtonLayout();
        addBinaryOutputDataLayout();
        addHammingDataLayout();
        addErrorComboBox();
        addErrorPositionLayout();
        addResultField();
        setJFrameSize();
        
    }
    
    private void createContainer() {
        cont = getContentPane();
        cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
    }
    
    private void addResultField() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        resultInfo = new JLabel("Brak informacji o wyniku");
        
        panel.add(resultInfo);
        panel.setMaximumSize(new Dimension(panel.getPreferredSize()));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cont.add(panel);
    }
    
    private void setJFrameProperties() {
        setResizable(false);
        setTitle(JFRAME_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void setJFrameSize() {
        pack();
    }
    
    private void addErrorComboBox() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        String[] list = { "-", "Tak", "Nie" };
        JComboBox comboBox = new JComboBox(list);
        setErrorComboBoxListener(comboBox);
        
        panel.add(new JLabel("Ustawić przekłamanie?"));
        panel.add(comboBox);
        panel.setMaximumSize(new Dimension(panel.getPreferredSize()));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cont.add(panel); 
    }
    
    private void setErrorComboBoxListener(JComboBox comboBox) {
      comboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object item = e.getItem();
                    switch (item.toString()) {
                        case "Tak": {
                            if (errorBit != -1) {
                                hamming[hamming.length - errorBit - 1] = 
                                        (hamming[hamming.length - errorBit - 1] + 1) 
                                        % 2;
                                inputErrorPositionField.setText(createNormalString(hamming));
                                errorBit = -1;
                            } else {
                                inputErrorPositionField.setText(createNormalString(hamming));    
                            }
                            receiveSentData(hamming.length - data.length);
                            resultInfo.setText("Odebrano błędne dane!");
                            break;
                        }
                        
                        case "Nie": {
                            int errorPosition = 0;
                            if (errorBit == -1) {
                                hamming[hamming.length - errorPosition - 1] = 
                                        (hamming[hamming.length - errorPosition - 1] 
                                        + 1) % 2;
                            } else {
                                hamming[hamming.length - errorBit - 1] = 
                                        (hamming[hamming.length - errorBit - 1] + 1) 
                                        % 2;
                                hamming[hamming.length - errorPosition - 1] = 
                                        (hamming[hamming.length - errorPosition - 1] 
                                        + 1) % 2;
                            }
                            errorBit = errorPosition;
                            inputErrorPositionField.setText(createNormalString(hamming));
                            receiveSentData(hamming.length - data.length);
                            resultInfo.setText("Odebrano prawidłowe dane!");
                            break;
                        }
                        
                    }
                }
            }
            
        });   
    }
    
    private void addInputDataLayout() {
        JPanel inputDataPanel = new JPanel(new FlowLayout());
        inputDataField = new JTextField(JTEXT_FIELD_WIDTH);
        
        inputDataPanel.setBorder(new EmptyBorder(EMPTY_BORDER_PADING, 
                EMPTY_BORDER_PADING, 0, EMPTY_BORDER_PADING));
        inputDataPanel.add(new JLabel(INPUT_JLABEL));
        inputDataPanel.add(inputDataField);
        inputDataPanel.setMaximumSize(inputDataPanel.getPreferredSize());
        inputDataPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        cont.add(inputDataPanel);
    }
    
    private void addGenerateButtonLayout() {
        JPanel generateButtonPanel = new JPanel(new FlowLayout());
        JButton dataGenerateButton = new JButton(DATA_GENERATE_BUTTON);
        
        addDataGenerateButtonListener(dataGenerateButton);
        
        generateButtonPanel.setBorder(new EmptyBorder(0, EMPTY_BORDER_PADING, 
                0, EMPTY_BORDER_PADING));
        generateButtonPanel.add(dataGenerateButton);
        generateButtonPanel.setMaximumSize(generateButtonPanel
                .getPreferredSize());
        generateButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        cont.add(generateButtonPanel);
    }
    
    private void addDataGenerateButtonListener(JButton button) {
        button.addActionListener((ActionEvent e) -> {
            generateInput();
            binaryOutputField.setText(createNormalString(data));
            generateHamming();
            binaryOutputField2.setText(createNormalString(hamming));
        });
    }
    
    private void addBinaryOutputDataLayout() {
        JPanel binaryOutputDataPanel = new JPanel(new FlowLayout());
        binaryOutputField = new JTextField(JTEXT_FIELD_WIDTH);
        
        binaryOutputDataPanel.setBorder(new EmptyBorder(0, EMPTY_BORDER_PADING, 
                0, EMPTY_BORDER_PADING));
        binaryOutputDataPanel.add(new JLabel(BINARY_OUTPUT_JLABEL));
        binaryOutputDataPanel.add(binaryOutputField);
        binaryOutputDataPanel.setMaximumSize(binaryOutputDataPanel
                .getPreferredSize());
        binaryOutputDataPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        cont.add(binaryOutputDataPanel);
    }
    
    private void addHammingDataLayout() {
        JPanel hammingDataPanel = new JPanel(new FlowLayout());
        binaryOutputField2 = new JTextField(JTEXT_FIELD_WIDTH);
        
        hammingDataPanel.setBorder(new EmptyBorder(0, EMPTY_BORDER_PADING, 
                0, EMPTY_BORDER_PADING));
        hammingDataPanel.add(new JLabel(HAMMING_JLABEL));
        hammingDataPanel.add(binaryOutputField2);
        hammingDataPanel.setMaximumSize(hammingDataPanel.getPreferredSize());
        hammingDataPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        cont.add(hammingDataPanel);
    }
    
    private void addErrorPositionLayout() {
        JPanel errorPositionPanel = new JPanel(new FlowLayout());
        inputErrorPositionField = new JTextField(JTEXT_FIELD_WIDTH);
        
        errorPositionPanel.setBorder(new EmptyBorder(0, EMPTY_BORDER_PADING, 
                0, EMPTY_BORDER_PADING));
        errorPositionPanel.add(new JLabel(ERROR_BIT_POSITION_JLABEL));
        errorPositionPanel.add(inputErrorPositionField);
        errorPositionPanel.setMaximumSize(errorPositionPanel
                .getPreferredSize());
        errorPositionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        cont.add(errorPositionPanel);
    }
    
    
    

     private void generateInput() {
        String generatedInput = inputDataField.getText().toString();
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

        for (int i = 1; i < dataBits; i++) {
            data[dataBits - i - 1] = Integer.parseInt(String.valueOf(binaryText
                    .charAt(dataBits - i - 1)));
        }
        
    }
    
    private void generateHamming() {
        int i = 0;
        int parityBitsCount = 0;
        int j = 0;
        int k = 0;


        // 1st stage
        while (i < data.length) {
            if (Math.pow(2, parityBitsCount) == i + parityBitsCount + 1) {
                parityBitsCount++;
            } else {
                i++;
            }
        }
       
        // 2nd stage
        hamming = new int[data.length + parityBitsCount];
        for (i = 1; i <= hamming.length; i++) {
            if (Math.pow(2, j) == i) {
                hamming[i - 1] = 2;
                parityBitsPositions.add(i - 1);
                j++;
            } else {
                hamming[k + j] = data[k++];
            }
        }

        // 3rd stage
        for (i = 0; i < parityBitsCount; i++) {
            hamming[(int) Math.pow(2, i) - 1] = getParity(i);
        }
    }
    
    private int getParity(int power) {
        int parity = 0;

        for (int i = 0; i < hamming.length; i++) {
            if (hamming[i] != 2) {
                int k = i + 1;
                String s = Integer.toBinaryString(k);
                int x = (Integer.parseInt(s) / (int) Math.pow(10, power)) % 10;
                if (x == 1) {
                    if (hamming[i] == 1) {
                        parity = (parity + 1) % 2;
                    }
                }
            }
        }
        return parity;
    }
     
    private String createNormalString(int[] generatedInput) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < generatedInput.length; i++) {
            builder.append(generatedInput[generatedInput.length - i - 1]);
        }
        return builder.toString();
    }
    
    private void receiveSentData(int parityBitsCount) {
        int power;
        int[] parity = new int[parityBitsCount];
        String syndrome = "";

        for (power = 0; power < parityBitsCount; power++) {
            for (int i = 0; i < hamming.length; i++) {
                int k = i + 1;
                String s = Integer.toBinaryString(k);
                int bit = ((Integer.parseInt(s)) / ((int) Math.pow(10, power))) % 10;
                if (bit == 1) {
                    if (hamming[i] == 1) {
                        parity[power] = (parity[power] + 1) % 2;
                    }
                }
            }
            syndrome = parity[power] + syndrome;
        }

        StringBuilder originalData = new StringBuilder();
        power = parityBitsCount - 1;
        for (int i = hamming.length; i > 0; i--) {
            if (Math.pow(2, power) != i) {
                originalData.append(hamming[i - 1]);
            } else {
                power--;
            }
        }
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Hamming ham = new Hamming();
            ham.setLocationRelativeTo(null);
            ham.setVisible(true);
        });
    }
    
}
