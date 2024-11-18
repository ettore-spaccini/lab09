package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();
    /**
     * Constructorup the whole view is set up here
     */
    public SimpleGUI(final Controller controller) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set the main panel
        final JPanel mainPanel = new JPanel(new BorderLayout());
        frame.setContentPane(mainPanel);
        //create JTextField(NORTH) and JTextArea(CENTER)
        final JTextField textField = new JTextField();
        mainPanel.add(textField, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        mainPanel.add(textArea, BorderLayout.CENTER);
        //create two buttons(SOUTH)
        final JPanel southJPanel = new JPanel(); 
        southJPanel.setLayout(new BorderLayout());
        mainPanel.add(southJPanel, BorderLayout.SOUTH);
        final JButton print = new JButton("Print");
        southJPanel.add(print, BorderLayout.CENTER);
        final JButton showHistoryButton= new JButton("Show History");
        southJPanel.add(showHistoryButton, BorderLayout.EAST);
        /*
         * Handler
         */
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String stringToPrint = textField.getText();
                controller.nextStringToPrint(stringToPrint);
                controller.printCurrentString();
            }  
        });
        showHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (final String string: controller.getHistoryOfPrintedString()) {
                    textArea.append(string);
                    textArea.append("\n");
                }
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
        final var view = new SimpleGUI(new SimpleController());
        view.display();
    }
}
