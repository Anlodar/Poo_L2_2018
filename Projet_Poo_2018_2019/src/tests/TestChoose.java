package tests;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class TestChoose {

  public static void main(String[] a) {
    JFrame frame = new JFrame("JFileChooser Popup");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JFileChooser fileChooser = new JFileChooser(".");
    fileChooser.setControlButtonsAreShown(false);
    frame.add(fileChooser, BorderLayout.CENTER);

    ActionListener actionListener = new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser theFileChooser = (JFileChooser) actionEvent.getSource();
        String command = actionEvent.getActionCommand();
        if (command.equals(JFileChooser.APPROVE_SELECTION)) {
          File selectedFile = theFileChooser.getSelectedFile();
          System.out.println(selectedFile.getParent());
          System.out.println(selectedFile.getName());
        } else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
          System.out.println(JFileChooser.CANCEL_SELECTION);
        }
      }
    };
    fileChooser.addActionListener(actionListener);
    frame.pack();
    frame.setVisible(true);
  }
}
