package ictgradschool.industry.q1;

/**
 * The JFrame class for the simple graphical user interface.
 * An instance of the JPanel class is created and added to the content
 * pane of the JFrame.
 * 
 * Do not modify this class.
*/

import java.awt.*;
import javax.swing.*;

public class Q1Frame extends JFrame {
    public Q1Frame(String title, int x, int y, int width, int height) {
        
        // Set the title, top left location, and close operation for the frame
        setTitle(title);
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create an instance of the JPanel class, and set this to define the
        // content of the window
        JPanel frameContent = new Q1Panel();
        Container visibleArea = getContentPane();
        visibleArea.add(frameContent);
        
        // Set the size of the content pane of the window, resize and validate the
        // window to suit, obtain keyboard focus, and then make the window visible
        frameContent.setPreferredSize(new Dimension(width, height));
        pack();
        frameContent.requestFocusInWindow();
        setVisible(true);
    }
}
