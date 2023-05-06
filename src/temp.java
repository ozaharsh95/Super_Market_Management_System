 package employee.management.system;
 
import java.awt.event.ActionEvent;
import javax.swing.*;

import java.awt.BorderLayout;
import java.io.*;

import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
class TextEditor extends JFrame {

   int count = 2;
   JTextArea textArea = new JTextArea(10, 30);
   final JFileChooser fc = new JFileChooser();

   public TextEditor() {

      add(new JScrollPane(textArea));
      add(new JPanel(){{add(new JButton(new AbstractAction("Save As") {

         @Override
         public void actionPerformed(ActionEvent arg0) {
            saveAs();
         }
      }));}}, BorderLayout.SOUTH);
   }

   public void saveAs() {
      FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Text File", "txt");
      final JFileChooser saveAsFileChooser = new JFileChooser();
      saveAsFileChooser.setApproveButtonText("Save");
      saveAsFileChooser.setFileFilter(extensionFilter);
      int actionDialog = saveAsFileChooser.showOpenDialog(this);
      if (actionDialog != JFileChooser.APPROVE_OPTION) {
         return;
      }

      // !! File fileName = new File(SaveAs.getSelectedFile() + ".txt");
      File file = saveAsFileChooser.getSelectedFile();
      if (!file.getName().endsWith(".txt")) {
         file = new File(file.getAbsolutePath() + ".txt");
      }

      BufferedWriter outFile = null;
      try {
         outFile = new BufferedWriter(new FileWriter(file));

         textArea.write(outFile);

      } catch (IOException ex) {
         ex.printStackTrace();
      } finally {
         if (outFile != null) {
            try {
               outFile.close();
            } catch (IOException e) {}
         }
      }
   }

   private static void createAndShowGui() {
      TextEditor frame = new TextEditor();

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }

} 