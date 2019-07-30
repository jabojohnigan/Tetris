/* 
 * TCSS 305 - Aissngment 6: Tetris 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.Board;
import myactions.BoardKeyListener;

/**
 * This class runs the Tetris game and sets up the main GUI with all of the 
 * encompasing components. 
 * @author Jabo Johnigan
 * @version March 1, 2016
 */
public class TetrisGUI extends JFrame implements Observer {
 
    /** The preferred window size. */
    public static final Dimension PREFERRED_SIZE = new Dimension(347, 451);
  
    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 4633858385078578545L;
    
    /** The delay of the game.*/
    private static final int DELAY = 1000;
    
    /** The Board Default Width. */
    private static final int TWIDTH = 10;

    /** The Board Default Height. */
    private static final int THEIGHT = 20;
    
    /** The Tetris Board Panel. */
    private static TetrisBoardPanel myBoardPanel;
    
    /** The preview Panel. */
    private static TetrisEastPanel myEastPanel;
    
    /** The timer for the game. */
    private Timer myTimer;
    
    /** The key listener for pausing the game. */
    private PauseKeyListener myPKL;
    
    /** The key listener controls for the game. */
    private BoardKeyListener myBKL;

    /** A new game board. */
    private Board myTetrisBoard;
    
    /** An int to control the resulting restart game option. */
    private int myResult;
    
    /** A boolean for game over status. */
    private boolean myGameOver;
    /**
     * Constructor for the GUI Frame of the Tetris Game. 
     */
    public TetrisGUI() {
        super("TCSS 305 - Jabo's Tetris");
        setResizable(false);
        setSize(PREFERRED_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
        myGameOver = true; 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    
    /**
     * The main method that kicks off the program.
     * @param theArgs 
     */
    public static void main(final String[] theArgs) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final TetrisGUI tetris = new TetrisGUI();
                tetris.build();
            }
        });
    }
    /**
     * Builds the GUI frame and its components.
     */
    private void build() {
        final ImageIcon titleImage = new ImageIcon("support_files/tetris-love.jpg");
        setIconImage(titleImage.getImage());
       
        final TetrisMenuBar tmb = new TetrisMenuBar(this);
        setJMenuBar(tmb);
        final TimerListener timer = new TimerListener();
        myTimer = new Timer(DELAY, timer);
        add(buildBoardPanel(), BorderLayout.CENTER);
        add(buildPreviewPanel(), BorderLayout.EAST);
     
        
        startUp();

        myTimer.start();
        myBKL = new BoardKeyListener(myTetrisBoard);
        myPKL = new PauseKeyListener();
        addKeyListener(myPKL);
        addKeyListener(myBKL);
    }
    
    /**
     * The Panel that contains the Tetris Board.
     * @return a TetrisBoardPanel Object that is a panel
     */
    private JPanel buildBoardPanel() {
        myBoardPanel = new TetrisBoardPanel();
        myBoardPanel.setVisible(true);
       
        return myBoardPanel;
    }
    
    /**
     * The Panel that contains the preview piece and the controls for the game.
     * @return a PreviewPanel Object that is a panel
     */
    private JPanel buildPreviewPanel() {
        myEastPanel = new TetrisEastPanel(myTimer);
        return myEastPanel;
    }

    /**
     * Starts up a new Tetris board game.
     */
    private void startUp() {
        myTetrisBoard = new Board(TWIDTH, THEIGHT);
        
        myTetrisBoard.addObserver(this);
        myTetrisBoard.addObserver(myEastPanel.myNextPiece);
        myTetrisBoard.addObserver(myEastPanel.myScorePanel);
        myTetrisBoard.addObserver(myBoardPanel);
        myTetrisBoard.newGame();

    }
    
    /**
     * Restarts the game by starting a new game and unpausing the game.
     */
    private void restart() {

        if (myResult == 0) {
            myBKL.setMyKeyPaused(true);
            myEastPanel.myScorePanel.resetScorePanel();           
            myTetrisBoard.newGame();  
            myTimer.start();
        } else if (myResult == 1) {
            dispose();
        } 
 
    }
    
    @Override
    public void update(final Observable arg0, final Object arg1) {
        if (arg1 instanceof Boolean) {
            myBKL.setMyKeyPaused(false);
            myTimer.stop();
            if (myGameOver) {
                myResult = JOptionPane.showConfirmDialog(this, "Start a new game?",
                                          "Game Over", JOptionPane.YES_NO_OPTION);
            }
            myGameOver ^= true; 
            restart();  
        }
    }
    
    
    ///////////////inner classes
    /**
     * An inner class that adds the timer to the GUI.
     * @author Jabo Johnigan
     * @version March 1, 2016
     */
    class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent arg0) {
            myTetrisBoard.step();

        }

    }

    /**
     * An innerclass for the Pause Button.
     * @author Jabo Johnigan
     * @version March 1, 2016
     */
    class PauseKeyListener extends KeyAdapter {
        
        /** A boolean to check if game is paused or not. */
        private boolean myPaused;
  
        /**
         * Checkes the current state of paused and changes it to the inverse. 
         * @return the new status of the paused state as a boolean
         */
        public boolean isPaused() {
            if (!myPaused) {
                myTimer.start();
                
                myPaused = true;
              
            } else {
                myPaused = false;
                myTimer.stop();
            }
            return myPaused;
        }
        
        /**
         * Sets the Pause Event to the "P" key.
         * @param theEvent P key is pressed
         */
        public void keyPressed(final KeyEvent theEvent) {
            final int aKey = theEvent.getKeyCode();
            if (aKey == KeyEvent.VK_P) {
                isPaused();
            } else if (aKey == KeyEvent.VK_N) {
                myResult = 0;
                restart();
                                
            }
                
        }
        
    }
    
}




