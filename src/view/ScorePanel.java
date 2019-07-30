/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.TetrisPiece;

/** This panel contains the score, level, and lines cleared.
 * 
 * @author Jabo Johnigan
 * @version March 1, 2016
 */
public class ScorePanel extends JPanel implements Observer {
    /** The current level. */
    private static int myLevel = 1;

    /** The current score. */
    private static int myScore = -4;

    /** The total number of lines cleared. */
    private static int myTotalLines;
    
    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = -1720668120098752756L;

    /**The dimensions of the next Tetris Piece Preview Box. */
    private static final Dimension T_BOX = new Dimension(140 , 100);

    /** The delay after the first level is passed. */
    private static final int GAME_DELAY = 500;
    
    /** A magic string for the int 3. */
    private static final int M3 = 3;
    
    /** A magic string for the int 4. */
    private static final int M4 = 4;
    
    /** Multplier for 1 line. */
    private static final int L1 = 40;

    /** Multplier for 2 lines. */
    private static final int L2 = 100;

    /** Multplier for 3 lines. */
    private static final int L3 = 300;

    /** Multplier for 4 lines. */
    private static final int L4 = 1200; 

    /** Min Number of lines for Lv.2. */
    private static final int LV_2 = 4;
    
    /** Min Number of lines for Lv.3. */
    private static final int LV_3 = 9;
    
    /** Min Number of lines for Lv.4. */
    private static final int LV_4 = 14;
    
    /** Min Number of lines for Lv.5. */
    private static final int LV_5 = 19;
    
    /** Frozen Points. */
    private static final int FROZ = 4;
    
    /** A string for score. */
    private static final String S = "Score ";
    
    /** A string for Cleared Lines. */
    private static final String CL = "Cleared Lines ";
    
    /** A string for Levels. */
    private static final String LV = "Level ";
    
  

    /** The number of lines cleared as an array. */
    private Integer[] myClearLineList;
    
    /** The timer of the game to be changed as the game continues. */
    private Timer myGameTimer;
    
    /** The Label for the score. */    
    private JLabel myScoreLabel;
    
    /** The Label for the number of lines cleared. */
    private JLabel myLineLabel;
    
    /**The Label for the current Level. */
    private JLabel myLevelLabel;
    
    
    /**
     * Creates a panel object that updates the score, level, and the number of rows cleared. 
     * @param theTimer the timer from the main gui class.
     */
    public ScorePanel(final Timer theTimer) {
        super();
        setBackground(Color.GRAY);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("My Score"));
        setSize(T_BOX);
       
        myGameTimer = theTimer;
        buildLabels();
       
    }
    
    /**
     * Builds the labels for the Scoring Panel.
     */
    private void buildLabels() {
        myScoreLabel = new JLabel(S + myScore);
        add(myScoreLabel);
        myLineLabel = new JLabel(CL + myTotalLines);
        add(myLineLabel);
        myLevelLabel = new JLabel(LV + myLevel);
        add(myLevelLabel);
    }

    /**
     * Calculates and updates the total number of lines cleared.
     * @param theNum the number of lines cleared in a row
     */
    private void updateLines(final int theNum) {
        if (theNum == 1) {
            myTotalLines = myTotalLines + theNum;
        } else if (theNum == 2) {
            myTotalLines = myTotalLines + theNum; 
        } else if (theNum == M3) {
            myTotalLines = myTotalLines + theNum; 
        } else if (theNum == M4) {
            myTotalLines = myTotalLines + theNum; 
        } 

  
        myLineLabel.setText(CL + myTotalLines);
    }
    /** Adds the frozen piece to the score. */
    private void frozen() {
        myScore = myScore + FROZ;
        myScoreLabel.setText(S + myScore);
    }

    /** Updates the Score on the panel. 
     * @param theNum the number of rows cleared.
     */
    private void updateScore(final int theNum) {
        if (theNum == 1) {
            myScore = myScore + (myLevel * L1);
        } else if (theNum == 2) {
            myScore = myScore + (myLevel * L2);
        } else if (theNum == M3) {
            myScore = myScore + (myLevel * L3);
        } else if (theNum == M4) {
            myScore = myScore + (myLevel * L4);
        }
        myScoreLabel.setText(S + myScore);
    }
    /** Updates the current level.
     *  @param theNum the total number of lines cleared in the game.
     *  */
    private void updateLevel(final int theNum) {
        if (theNum > LV_2 && myLevel == 1) {
            myGameTimer.setDelay(GAME_DELAY / myLevel);
            myLevel = myLevel + 1;
         
        } else if (theNum > LV_3 && myLevel == 2) {
            myGameTimer.setDelay(GAME_DELAY / myLevel);
            myLevel = myLevel + 1;
           
        } else if (theNum > LV_4 && myLevel == M3) {
            myGameTimer.setDelay(GAME_DELAY / myLevel);
            myLevel =  myLevel + 1;
          
        } else if (theNum > LV_5 && myLevel == M4) {
            myGameTimer.setDelay(GAME_DELAY / myLevel);
            myLevel = myLevel + 1;
        
        }
        myLevelLabel.setText(LV + myLevel);
    }
    
    /**
     * Resets the Score, Lines Cleared, and Level to 0.
     */
    protected void resetScorePanel() {
        myScore = 0;
        myScore = myScore - FROZ * 2;
        myTotalLines = 0;
        myLevel = 1;
        myLineLabel.setText(CL + myTotalLines);
        myScoreLabel.setText(S + myScore);
        myLevelLabel.setText(LV + myLevel);
        
    }
    @Override
    public void update(final Observable arg0, final Object arg1) {
        if (arg1 instanceof Integer[]) {
            myClearLineList = (Integer[]) arg1;
            final int clearedLines = myClearLineList.length;
            updateLines(clearedLines);
            updateScore(clearedLines);
            updateLevel(myTotalLines);

        } else if (arg1 instanceof TetrisPiece) {
            frozen();
        }
        repaint();

    }

}
