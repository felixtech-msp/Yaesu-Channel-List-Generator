package at.oe5eir.yaesu.channels;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public final class GUI {
    private GUI() {}

    static void show() {
        String title = "OE5EIR Yaesu FT3D ÖVSV Repeater List Generator";

        JOptionPane.showMessageDialog(null, "GUI unavailable", title, JOptionPane.ERROR_MESSAGE);

        // abort program as the GUI is unreliable
        if (1 == 1)
            return;

        JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(false);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fc.showSaveDialog(null);

        if (result != JFileChooser.APPROVE_OPTION)
            return;

        File file = fc.getSelectedFile();

        if (!file.getName().endsWith(".csv"))
            file = new File(file.getAbsolutePath() + ".csv");

        if (file.exists()) {
            int overwrite = JOptionPane.showConfirmDialog(null, "The file already exists. Do you want to overwrite it?", title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (overwrite == JOptionPane.NO_OPTION)
                return;
        }

        try {
            Main.main(file.getAbsolutePath());
            JOptionPane.showMessageDialog(null, "Operation completed successfully.", title, JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), title, JOptionPane.ERROR_MESSAGE);
        }
    }
}
