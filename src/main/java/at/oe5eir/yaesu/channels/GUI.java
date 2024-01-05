package at.oe5eir.yaesu.channels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static java.awt.GridBagConstraints.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public final class GUI {
    private static final String TITLE = "OE5EIR Yaesu FT3D ÖVSV Repeater List Generator";

    private JFrame window;
    private File file = null;
    private JTextField tfFilename;
    private JButton btnStart, selectFileButton;
    private JTextArea console;
    private JRadioButton rbMountain, rbCity;

    GUI() {
        window = new JFrame(TITLE);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setLayout(new GridBagLayout());
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        Font defaultFont = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
        UIManager.put("Label.font", defaultFont);
        UIManager.put("RadioButton.font", defaultFont);
        UIManager.put("TextField.font", defaultFont);
        UIManager.put("TextField.inactiveForeground", Color.black);
        UIManager.put("TextArea.font", new Font(Font.MONOSPACED, Font.PLAIN, 14));
        UIManager.put("TextArea.inactiveForeground", Color.black);

        selectFileButton = new JButton(new AbstractAction("Select File") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectFile()) {
                    btnStart.setEnabled(true);
                }
            }
        });
        add(window, new JLabel("Output File:"), 0, 0, 1, EAST);
        tfFilename = new JTextField(30);
        tfFilename.setEnabled(false);
        add(window, tfFilename, 1, 0, 1, WEST);
        add(window, selectFileButton, 2, 0, 1, WEST);

        add(window, new JLabel("Channel Name:"), 0, 1, 1, EAST);
        rbMountain = new JRadioButton("Mountain");
        rbCity = new JRadioButton("City");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbMountain);
        buttonGroup.add(rbCity);
        rbMountain.setSelected(true);
        JPanel panelRb = new JPanel(new GridLayout(1,2));
        panelRb.add(rbMountain);
        panelRb.add(rbCity);
        add(window, panelRb, 1, 1, 1, WEST);

        console = new JTextArea(10,74);
        console.setEnabled(false);
        add(window, new JScrollPane(console), 0, 2, 3, CENTER);
        System.setOut(new PrintStream(new TextAreaConsole(console)));
        System.setErr(new PrintStream(new TextAreaConsole(console)));

        btnStart = new JButton(new AbstractAction("Generate...") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rbMountain.isSelected()) {
                    btnStart.setEnabled(false);
                    runTask(false);
                } else if (rbCity.isSelected()) {
                    btnStart.setEnabled(false);
                    runTask(true);
                }
            }
        });
        btnStart.setEnabled(false);
        add(window, btnStart, 2, 3, 1, EAST);

        window.pack();
    }

    void show() {
        window.setVisible(true);
    }

    private static void add(JFrame window, JComponent component, int x, int y, int w, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.anchor = anchor;
        window.add(component, gbc);
    }

    private boolean selectFile() {
        JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(false);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fc.showSaveDialog(null);

        if (result != JFileChooser.APPROVE_OPTION)
            return false;

        file = fc.getSelectedFile();

        if (!file.getName().endsWith(".csv"))
            file = new File(file.getAbsolutePath() + ".csv");

        if (file.exists()) {
            int overwrite = JOptionPane.showConfirmDialog(null, "The file already exists. Do you want to overwrite it?", TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (overwrite == JOptionPane.NO_OPTION)
                return false;
        }

        tfFilename.setText(file.getAbsolutePath());
        return true;
    }

    private void runTask(boolean cityAsName) {
        selectFileButton.setEnabled(false);
        rbMountain.setEnabled(false);
        rbCity.setEnabled(false);

        Thread thread = new Thread(() -> {
            try {
                Main.start(file.getAbsolutePath(), cityAsName);
                JOptionPane.showMessageDialog(window, "Operation completed successfully.", TITLE, JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(window, ex.getMessage(), TITLE, JOptionPane.ERROR_MESSAGE);
            }

            selectFileButton.setEnabled(true);
            tfFilename.setText(null);
            rbMountain.setEnabled(true);
            rbCity.setEnabled(true);
            file = null;
        });
        thread.start();
    }
}
