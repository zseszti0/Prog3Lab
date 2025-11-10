import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CaesarFrame extends JFrame {
    // Komponensek privát tagváltozóként (3. feladat) [cite: 48, 50]
    private JTextField inputField;
    private JTextField outputField;
    private JButton codeButton;
    private JComboBox<Character> offsetCombo;

    private boolean topFieldHasFocus = true; // [cite: 81]

    public CaesarFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SwingLab");
        setSize(400, 150); // 110px kicsi, 150-re állítva a jobb láthatóságért
        setResizable(true);

        //set the layout
        //main concept
        //two panel grid
        //----offsetter-----input field-----button
        //------output field----------------------
        setLayout(new GridLayout(2, 1));

        // Komponensek létrehozása
        inputField = new JTextField(20);
        outputField = new JTextField(20);
        codeButton = new JButton("Code!");

        //add the alphabet to the Jcombobox
        Character[] abc = new Character[26];
        for (int i = 0; i < 26; i++) {
            abc[i] = (char) ('A' + i);
        }
        offsetCombo = new JComboBox<>(abc);


        JPanel topPanel = new JPanel();
        topPanel.add(offsetCombo);
        topPanel.add(inputField);
        topPanel.add(codeButton);


        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JLabel("Output:"));
        bottomPanel.add(outputField);

        //add the panels to the frame
        add(topPanel);
        add(bottomPanel);

        // Alsó mező ne legyen szerkeszthető (3. feladat) [cite: 55]
        outputField.setEditable(false);

        //add listeners to the buttons/fields
        codeButton.addActionListener(new OkButtonActionListener()); // [cite: 62]
        inputField.getDocument().addDocumentListener(new CodingDocumentListener());

    }


    private class OkButtonActionListener implements ActionListener { // [cite: 59]
        @Override
        public void actionPerformed(ActionEvent e) {
            performCoding();
        }
    }
    private class CodingDocumentListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            performCoding();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {

        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }

    private void performCoding() {
        char offset = (char) offsetCombo.getSelectedItem();
        String input = inputField.getText();
        String output = CaesarCipher.encode(input, offset);
        outputField.setText(output);
    }


}
