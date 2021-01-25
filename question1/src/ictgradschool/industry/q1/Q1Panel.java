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

public class Q1Panel extends JPanel implements KeyListener, ActionListener, MouseListener {

    private Van mrWhippy;
    private Timer timer;


    public Q1Panel() {
        mrWhippy = new Van(40, 40, "Mr Whippy");  // Construct the Van
        addKeyListener(this);
        this.timer = new Timer(30, this);

        addMouseListener(this);


    }

    public void actionPerformed(ActionEvent e) {
        mrWhippy.move();
        requestFocusInWindow();
        repaint();

    }

    public void mousePressed(MouseEvent e) {
        if (!timer.isRunning())
            timer.start();
//        if (e.getClickCount() == 1) {
//            mrWhippy.move();
//        } else if (e.getClickCount() / 2 == 1) {
//            mrWhippy.move();
//        } else
//            try {
//                mrWhippy.wait(200000);
//            } catch (InterruptedException interruptedException) {
//                interruptedException.printStackTrace();
//            }


    }


    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                mrWhippy.setDirection(Van.LEFT);
                timer.start();
                break;
            case KeyEvent.VK_RIGHT:
                mrWhippy.setDirection(Van.RIGHT);
                timer.start();
                break;
            case KeyEvent.VK_C:
                mrWhippy.changeColour();
                timer.start();
                break;
            case KeyEvent.VK_S:
                timer.stop();
                break;
            case KeyEvent.VK_UP:
                mrWhippy.speedUp();
                timer.start();
                break;
            case KeyEvent.VK_DOWN:
                mrWhippy.slowDown();
                timer.start();
                break;
        }


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

