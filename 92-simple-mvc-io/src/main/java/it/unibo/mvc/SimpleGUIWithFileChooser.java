package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public SimpleGUIWithFileChooser(final Controller controller) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //panel 1
        final JPanel panel1 = new JPanel(new BorderLayout());
        frame.setContentPane(panel1);
        final JButton save = new JButton("Save");
        panel1.add(save, BorderLayout.SOUTH);
        final JTextArea tArea = new JTextArea();
        panel1.add(tArea, BorderLayout.CENTER);
        //panel 2
        final JPanel panel2 = new JPanel(new BorderLayout());
        panel1.add(panel2, BorderLayout.NORTH);
        final JTextField textField = new JTextField();
        panel2.add(textField, BorderLayout.CENTER);
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
                final String content = tArea.getText();
                controller.writeOnFile(content);
            }
        });
    }
    private void display() {
        /*
            * Make the frame one fifth the resolution of the screen. This very method is
            * enough for a single screen setup. In case of multiple monitors, the
            * primary is selected. In order to deal coherently with multimonitor
            * setups, other facilities exist (see the Java documentation about this
            * issue). It is MUCH better than manually specify the size of a window
            * in pixel: it takes into account the current resolution.
            */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
            * Instead of appearing at (0,0), upper left corner of the screen, this
            * flag makes the OS window manager take care of the default positioning
            * on screen. Results may vary, but it is generally the best choice.
            */
        frame.setLocationByPlatform(true);
        /*
            * OK, ready to push the frame onscreen
            */
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
