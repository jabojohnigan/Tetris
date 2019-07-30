/* 
 * TCSS 305 - Aissngment 6: Tetris 
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Point;
import model.TetrisPiece;

/**A simple panel container that is specfically designed to hold the preview piece.
 * 
 * @author Jabo Johnigan
 * @version March 1, 2016
 */
public class MyPreviewPiece extends JPanel implements Observer {

    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 8006950936206903912L;

    /**Int  value for the size of the preview piece. */
    private static final int SIZE = 20;

    /**The dimensions of the next Tetris Piece Preview Box. */
    private static final Dimension T_BOX = new Dimension(140 , 105);

    /**The next tetris piece. */
    private TetrisPiece myPiece;



    /**
     * A panel that contains the preview piece.
     */
    public MyPreviewPiece() {
        super();
        setPreferredSize(T_BOX);
        setVisible(true);
        setBackground(Color.GRAY);
        setBorder(BorderFactory.createTitledBorder("My Next Piece"));
        
    }

    /**
     * And update method to be fired when the board sends an TetrisPiece.
     */
    @Override
    public void update(final Observable arg0, final Object arg1) {
        if (arg1 instanceof TetrisPiece) {
            myPiece = (TetrisPiece) arg1;
            repaint();
        }
    }
    /**
     * Overrides the paintComponent method for this JPanel.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.WHITE);

        if (myPiece != null) {
            final Point[] tetrisPoints = myPiece.getPoints();
          
            for (final Point p : tetrisPoints) {
                if (myPiece == TetrisPiece.I) {  
                    g2d.setPaint(Color.CYAN);
                    g2d.fillRect(p.y() * SIZE + SIZE, p.x() * SIZE + SIZE, SIZE , SIZE);
                    g2d.setColor(Color.WHITE);
                    g2d.drawRect(p.y() * SIZE + SIZE, p.x() * SIZE + SIZE, SIZE , SIZE);
                } else if (myPiece == TetrisPiece.J) {
                    g2d.setPaint(Color.BLUE);
                    g2d.fillRect(p.y() * SIZE + SIZE, p.x() * SIZE + SIZE, SIZE , SIZE);
                    g2d.setColor(Color.WHITE);
                    g2d.drawRect(p.y() * SIZE + SIZE, p.x() * SIZE + SIZE, SIZE , SIZE);
                } else if (myPiece == TetrisPiece.L) {
                    g2d.setPaint(Color.ORANGE);
                    g2d.fillRect(p.y() * SIZE + SIZE, p.x() * SIZE + SIZE, SIZE , SIZE);
                    g2d.setColor(Color.WHITE);
                    g2d.drawRect(p.y() * SIZE + SIZE, p.x() * SIZE + SIZE, SIZE , SIZE);
                } else if (myPiece == TetrisPiece.O) {
                    g2d.setPaint(Color.YELLOW);
                    g2d.fillRect(p.y() * SIZE + SIZE, p.x() * SIZE + SIZE, SIZE , SIZE);
                    g2d.setColor(Color.WHITE);
                    g2d.drawRect(p.y() * SIZE + SIZE, p.x() * SIZE + SIZE, SIZE , SIZE);
                } else if (myPiece == TetrisPiece.S) {
                    g2d.setPaint(Color.GREEN);
                    g2d.fillRect(p.y() * SIZE + SIZE, p.x() * SIZE + SIZE, SIZE , SIZE);
                    g2d.setColor(Color.WHITE);
                    g2d.drawRect(p.y() * SIZE + SIZE, p.x() * SIZE + SIZE, SIZE , SIZE);
                } else if (myPiece == TetrisPiece.T) {
                    g2d.setPaint(Color.MAGENTA);
                    g2d.fillRect(p.y() * SIZE + SIZE, p.x() * SIZE + SIZE, SIZE , SIZE);
                    g2d.setColor(Color.WHITE);
                    g2d.drawRect(p.y() * SIZE + SIZE, p.x() * SIZE + SIZE, SIZE , SIZE);
                } else if (myPiece == TetrisPiece.Z) {
                    g2d.setPaint(Color.RED);
                    g2d.fillRect(p.y() * SIZE + SIZE, p.x() * SIZE + SIZE, SIZE , SIZE);
                    g2d.setColor(Color.WHITE);
                    g2d.drawRect(p.y() * SIZE + SIZE, p.x() * SIZE + SIZE, SIZE , SIZE);
                }

            }
        }

    }
}
