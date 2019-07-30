/* 
 * TCSS 305 - Aissngment 6: Tetris 
 */
package myactions;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import model.Board;
/**
 * This class represents the KeyListener for the Tetris Game. 
 * @author Jabo Johnigan
 * @version March 1, 2016
 */
public class BoardKeyListener extends KeyAdapter {
    /** The Tetris board used to add the Key Listener to. */
    private final Board myTetrisBoard;


    /** A boolean to check if game is paused or not. */
    private boolean myKeyPaused;

    /**
     * A constructor for a Key Listener designed specifically for this board.
     * @param theBoard a Tetris game board object from the gui class.
     */
    public BoardKeyListener(final Board theBoard) {
        myTetrisBoard = theBoard;
        myKeyPaused = true;

    }

    
    /**
     * Set the value of myKeyPaused to whatever boolean passed.
     * @param theFlag a boolean expression passed to change the enabling of the keys
     */
    public void setMyKeyPaused(final boolean theFlag) {
        myKeyPaused = theFlag;
    }



    @Override
    public void keyPressed(final KeyEvent theEvent) {
        
        if (myKeyPaused) {
            final int aKey = theEvent.getKeyCode();
        
            if (aKey == KeyEvent.VK_RIGHT) {
                myTetrisBoard.right();
            } else if (aKey == KeyEvent.VK_UP) {
                myTetrisBoard.drop();
            } else if (aKey == KeyEvent.VK_LEFT) {
                myTetrisBoard.left();
            } else if (aKey == KeyEvent.VK_DOWN) {
                myTetrisBoard.down();
            } else if (aKey == KeyEvent.VK_A) {
                myTetrisBoard.rotateCCW();
            } else if (aKey == KeyEvent.VK_S) {
                myTetrisBoard.rotateCW();

            }
        } 

    }
}
