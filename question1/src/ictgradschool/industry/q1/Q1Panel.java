package ictgradschool.industry.q1;
/**
  * The JPanel class for an ice cream van, Mr Whippy.
  * 
  * Modify this class appropriately for the van to move.
  * 
  * Write your UPI here.
  * 
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Q1Panel extends JPanel {

    private Van mrWhippy;
    
    public Q1Panel() {
        
        mrWhippy = new Van(40,40, "Mr Whippy");  // Construct the Van
        
    }
    
    public void actionPerformed(ActionEvent e) {
    	
    }
    
    public void mousePressed(MouseEvent e) {

    }
    
    public void keyPressed(KeyEvent e) {
        
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        mrWhippy.draw(g);
        g.setFont(new Font("Times New Roman", 0, 24));
        g.drawString("Press mouse to start van moving", 100, 170);
        g.drawString("Left Arrow to go left", 100, 200);
        g.drawString("Right Arrow to go right", 100, 230);
        g.drawString("Up Arrow to increase speed", 100, 260);
        g.drawString("Down Arrow to decrease speed", 100, 290);
        g.drawString("c to change colour", 100, 320);
        g.drawString("s to stop", 100, 350);
    }
    
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

}

