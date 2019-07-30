/* 
 * TCSS 305 - Aissngment 6: Tetris 
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


import javax.swing.JPanel;

import model.Block;



/**
 * This class contains the tetris board panel and its encompasing components. 
 * @author Jabo Johnigan
 * @version March 1, 2016
 */
public class TetrisBoardPanel extends JPanel implements Observer {

    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 379053860726408694L;


    /**The Panel Default Width. */
    private static final int PWIDTH = 200;

    /**The Panel Default Height. */
    private static final int PHEIGHT = 378;

    /** An Array List of Blocks. */
    private ArrayList<Block[]> myRow = new ArrayList<Block[]>();

    /**
     * The constructor for a TetrisBoardPanel type object.
     */
    public TetrisBoardPanel() {
        super();
        setOpaque(true);
        setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
        setBackground(Color.GRAY);

    }



    @Override
    public void update(final Observable arg0, final Object arg1) {
        if (arg1 instanceof ArrayList) {
            myRow = (ArrayList<Block[]>) arg1;
            for (int i = 0; i < myRow.size(); i++) {
                for (int j = 0; j < myRow.get(i).length; j++) {
                    if (myRow.get(i)[j] == null) {
                        repaint();
                    }
                }
            }

        } 

    }
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < myRow.size(); i++) {
            for (int j = 0; j < myRow.get(i).length; j++) {
                final Shape rectangle = new Rectangle2D.Double(20 * j, PHEIGHT
                                                               - (i * 20), 20,
                                                               20);
                if (myRow.get(i)[j] == Block.I) {
                    g2d.setColor(Color.CYAN);
                    g2d.fill(rectangle);
                    g2d.setColor(Color.WHITE);
                    g2d.draw(rectangle);
                }   else if (myRow.get(i)[j] == Block.J) {
                    g2d.setColor(Color.BLUE);
                    g2d.fill(rectangle);
                    g2d.setColor(Color.WHITE);
                    g2d.draw(rectangle);
                } else if (myRow.get(i)[j] == Block.L) {
                    g2d.setColor(Color.ORANGE);
                    g2d.fill(rectangle);
                    g2d.setColor(Color.WHITE);
                    g2d.draw(rectangle);
                } else if (myRow.get(i)[j] == Block.O) {
                    g2d.setColor(Color.YELLOW);
                    g2d.fill(rectangle);
                    g2d.setColor(Color.WHITE);
                    g2d.draw(rectangle);
                } else if (myRow.get(i)[j] == Block.S) {
                    g2d.setColor(Color.GREEN);
                    g2d.fill(rectangle);
                    g2d.setColor(Color.WHITE);
                    g2d.draw(rectangle);
                } else if (myRow.get(i)[j] == Block.T) {
                    g2d.setColor(Color.MAGENTA);
                    g2d.fill(rectangle);
                    g2d.setColor(Color.WHITE);
                    g2d.draw(rectangle);
                } else if (myRow.get(i)[j] == Block.Z) {
                    g2d.setColor(Color.RED);
                    g2d.fill(rectangle);
                    g2d.setColor(Color.WHITE);
                    g2d.draw(rectangle);
                }
            }
        }
    }
}
