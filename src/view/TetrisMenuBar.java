/*
 * TCSS 305 - Assignment 6: Tetris
 */
package view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * This class contains all the componets of the menu bar.
 * @author Jabo Johnigan
 * @version March 1, 2016
 */
public class TetrisMenuBar extends JMenuBar {

    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = -5688716705020672204L;
    /** The Frame that contains the menu bar.    */
    private JFrame myFrame;
    
    /**
     * Construcs the Menu Bar for this Tetris game.
     * @param theFrame the outter most JFrame
     */
    public TetrisMenuBar(final JFrame theFrame) {
        myFrame = theFrame;
        add(buildFileMenu(myFrame));
        add(buildHelpMenu());
        
    }
    
    /**
     * Builds the File menu.
     * @param theFrame the frame used for linking the exit action
     * @return the File menu as a JMenu object
     */
    private JMenu buildFileMenu(final JFrame theFrame) {
        final JMenu filemenu = new JMenu("File");
        filemenu.setMnemonic(KeyEvent.VK_F);
        
//        //file menu items
//        final JMenuItem newGame = new JMenuItem("New Game");
//        newGame.setMnemonic(KeyEvent.VK_N);
//        filemenu.add(newGame);
//        filemenu.addSeparator();
//        
        final JMenuItem exitGame = new JMenuItem("Exit");
        exitGame.setMnemonic(KeyEvent.VK_X);
        exitGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                theFrame.dispatchEvent(new WindowEvent(theFrame, WindowEvent.WINDOW_CLOSING));
            }
        });
        filemenu.add(exitGame);
        return filemenu;
    }
    
    /**
     * Builds the help menu with the about button.
     * @return the helpmenu as a JMenu object
     */
    private JMenu buildHelpMenu() {
        final JMenu helpmenu = new JMenu("Help");
        helpmenu.setMnemonic(KeyEvent.VK_H);
        final JMenuItem aboutButton = new JMenuItem("About...");
        aboutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                ImageIcon titleImage = new ImageIcon("support_files/tetris-love.jpg");
                final int dimw = 120;
                final int dimh = 100;
                final Image newimg = titleImage.getImage().getScaledInstance(dimw, dimh,
                                                          java.awt.Image.SCALE_SMOOTH);
                titleImage = new ImageIcon(newimg);
                JOptionPane.showMessageDialog(aboutButton, helpful(), 
                                "Jabo's Tetris", JOptionPane.INFORMATION_MESSAGE, titleImage);

            }
        });

        aboutButton.setMnemonic(KeyEvent.VK_A);
        helpmenu.add(aboutButton);
        return helpmenu;
    }
    
    /**
     * I just made this method to create a really long about me section with love.
     * @return a helpful string
     */
    public String helpful() {
        final String n = "\n";
        final String help = "Welcome to Jabo's Tetris."
                        + " A world of joy, love, and excitment. " + n
                        + "Let me show you a few tips on how to get started:"
                        + n + "First: Scoring is essential to this game and the"
                        + " only meaning of life, and why you should be playing it."
                        + n + "The Scoring Algorithim is as follows:" + n 
                        + "There are only 5 levels in this beta so far and the"
                        + " general rule is this" + n
                        + "Level #n: 1 LINE CLEARED = 40*(n)  "
                        + "  2 LINES CLEARED = 100 * (n)"
                        + "     3 LINES CLEARED = 300 * (n) "
                        + "    4 LINES CLEARED = 1200 * (n)" + n
                        + "Now you also get 4 points for every piece you land"
                        + " on the board and sticks."
                        + n + "Don't worry yourself too much if you can't get the hang"
                        + " of the controls. They come with time." + n
                        + "Be looking for" + " and update and a spinoff." + n
                        + "Your friend in the diamond" + " business."
                        + n + "- Jabo Johnigan :)";
        return help;
    }
}
