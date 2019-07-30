/**
 * 
 */
package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** This panel contains the controls.
 * 
 * @author Jabo Johnigan
 * @version March 1, 2016
 */
public class Controls extends JPanel {

    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 7076832384911731216L;

    /**
     * Creates a panel object that holds the controls for the game.
     */
    public Controls() {
        setVisible(true);
        setBackground(Color.GRAY);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("The Controls"));
        setUpControls();
    }

    /**
     * Builds the labels with the specfic control function. 
     */
    private void setUpControls() {
        final JLabel down = new JLabel("Down Arrow: step down");
        add(down);
        final JLabel up = new JLabel("Up Arrow: drop piece");
        add(up);
        final JLabel left = new JLabel("Left Arrow: step left");
        add(left);
        final JLabel right = new JLabel("Right Arrow: step right");
        add(right);
        final JLabel ccw = new JLabel("'A' Key: rotate CCW");
        add(ccw);
        final JLabel cw = new JLabel("'S' Key: rotate CW");
        add(cw);
        final JLabel pause = new JLabel("'P' Key: pause game");
        add(pause);
        final JLabel newGame = new JLabel("'N' Key: New Game");
        add(newGame);
    }
}
