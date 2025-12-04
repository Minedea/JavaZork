import javax.swing.*;

public class Printer {
    private static JTextArea textArea;

    public Printer(JTextArea textAreabox){
        textArea=textAreabox;
    }
    public static void addText(String text){
        textArea.append(text + "\n");
    }
}
