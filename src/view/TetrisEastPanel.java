/* 
 * TCSS 305 - Aissngment 6: Tetris 
 */
package view;



import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This class contains the preview panel and its encompasing components. 
 * @author Jabo Johnigan
 * @version March 1, 2016
 */
public class TetrisEastPanel extends JPanel {

    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = -5937604642149844285L;

    /** The next piece panel used to display the next piece.*/
    protected MyPreviewPiece myNextPiece;

    /** The score panel used to display the score, level, and lines cleared.*/
    protected ScorePanel myScorePanel;

    /** The control panel used to display the controls for the game. */
    private Controls myControl;
    /**
     * The constructor for a PreviewPanel object. 
     * @param theTimer passed to this panel, to be then passed to the ScorePanel
     */
    public TetrisEastPanel(final Timer theTimer) {
        super();
        setOpaque(true);
        setBackground(Color.BLUE);
        setVisible(true);
        setLayout(new BorderLayout());
        myNextPiece = new MyPreviewPiece();
        myScorePanel = new ScorePanel(theTimer);
        myControl = new Controls();
        setUp();



    }
    /**
     * Set up the components of the Preview Panel.
     */
    private void setUp() {
        add(myNextPiece, BorderLayout.NORTH);
        add(myScorePanel, BorderLayout.CENTER);
        add(myControl, BorderLayout.SOUTH);
    }
}