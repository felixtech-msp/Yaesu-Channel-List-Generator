package at.oe5eir.yaesu.channels;

import javax.swing.*;
import java.io.OutputStream;

public class TextAreaConsole extends OutputStream {
    private JTextArea textArea;

    public TextAreaConsole(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) {
        textArea.append(String.valueOf((char) b));
    }
}
