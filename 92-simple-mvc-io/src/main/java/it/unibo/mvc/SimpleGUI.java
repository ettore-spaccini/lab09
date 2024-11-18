package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();
    /**
     * Constructor.
     * @param controller
     */
    public SimpleGUI(final Controller controller) {
        final JPanel panel = new JPanel(new BorderLayout());
        frame.setContentPane(panel);
        final JButton save = new JButton("Save");
        panel.add(save, BorderLayout.SOUTH);
        final JTextArea tArea = new JTextArea();
        panel.add(tArea, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
        * Handlers
        */
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
     * Lanch the application.
     * @param args
     */
    public static void main(final String[] args) {
        final var view = new SimpleGUI(new Controller());
        view.display();
    }
}
