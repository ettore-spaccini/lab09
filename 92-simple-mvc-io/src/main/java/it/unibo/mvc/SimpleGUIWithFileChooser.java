package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();

    /**
     * Constructor of SimpleGUIWithFileChooser.
     * @param controller
     */
    private SimpleGUIWithFileChooser(final Controller controller) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //panel 1
        final JPanel panel1 = new JPanel(new BorderLayout());
        frame.setContentPane(panel1);
        //Save button
        final JButton save = new JButton("Save");
        panel1.add(save, BorderLayout.SOUTH);
        //textArea
        final JTextArea tArea = new JTextArea();
        panel1.add(tArea, BorderLayout.CENTER);
        //panel 2
        final JPanel panel2 = new JPanel(new BorderLayout());
        panel1.add(panel2, BorderLayout.NORTH);
        //textField
        final JTextField textField = new JTextField();
        panel2.add(textField, BorderLayout.CENTER);
        //Browser Button
        final JButton browserButton = new JButton("Browse..."); 
        panel2.add(browserButton, BorderLayout.EAST);
        /**
         * handler
         */
        textField.setEditable(false);
        textField.setText(controller.getFilePath());
        browserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser();
                final var result = fileChooser.showSaveDialog(browserButton);
                switch (result) {
                    case JFileChooser.APPROVE_OPTION:
                        controller.setFileAsCurrent(fileChooser.getSelectedFile());
                        textField.setText(fileChooser.getSelectedFile().getPath());
                        break; 
                    case JFileChooser.CANCEL_OPTION:
                        break; 
                    default:
                        JOptionPane.showMessageDialog(frame, result, "An error has occurred", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.writeOnFile(tArea.getText());
                } catch (final IOException e2) {
                    JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e2.printStackTrace(); // NOPMD: allowed as this is just an exercise
                }
            }
        });
    }
    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    /**
     * Launch the application.
     * @param args
     */
    public static void main(final String[] args) {
        final var view = new SimpleGUIWithFileChooser(new Controller());
        view.display();
    }
}
