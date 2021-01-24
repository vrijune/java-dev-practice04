package ictgradschool.industry.q1;
/**
  * A class representing a Van.
  * Modify this class appropriately for the van to move.
  * 
  * Write your UPI here.
  * 
*/
import java.awt.*;

public class Van {
    
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    
    private int x;
    private int y;
    private int size;
    private int speed;
    private int direction;
    private Color vanColour;
    private String description;
    
    public Van(int left, int top, String description) {
        
        changeColour();
        x = left;
        y = top;
        size = (int)(Math.random() * 10 + 5);
        // Initially, the van will move 4 pixels with every tick of the Timer.
        speed = 4;
        // Initially, the direction is to the right
        direction = RIGHT;
        this.description = description;
    }
    
    // Change the direction
    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    public void changeColour() {
        int red = (int)(Math.random()*256);
        int green = (int)(Math.random()*256);
        int blue = (int)(Math.random()*256);
        vanColour = new Color(red, green, blue);
    }
    
    public void slowDown() {
        // Decrease the speed by 2 pixels.
        // Do not let the speed go below 2 (set it back to 2 if it does)

        if (direction == RIGHT) {
            x = x + speed - 2;
        }
        if (direction == LEFT) {
            x = x - speed + 2;
            if (speed <= 2){
                speed = 2;
            }
        }
    }

    public void speedUp() {
        // Increase the speed by 2 pixels.
        if (direction == RIGHT) {
            x = x + speed + 2;
        }
        if (direction == LEFT) {
            x = x - speed - 2;
        }
    }

    public void move() {
        // Move the van in the current direction
        if (direction == RIGHT) {
            x = x + speed;
        }
        // Add the code to move to the left
        if (direction == LEFT) {
            x = x - speed;
        }
    }

    // Draw the van
    public void draw(Graphics g) {
        g.fillOval(x + 3 * size, y + 55 * size / 8, 2 * size, 2 * size);
        g.fillOval(x + 3 * size, y + 55 * size / 8, 2 * size, 2 * size);
        g.drawOval(x + 3 * size, y + 55 * size / 8, 2 * size, 2 * size);
        g.fillOval(x + 10 * size, y + 55 * size / 8, 2 * size, 2 * size);
        g.fillOval(x + 10 * size, y + 55 * size / 8, 2 * size, 2 * size);
        g.drawOval(x + 10 * size, y + 55 * size / 8, 2 * size, 2 * size);
        g.setColor(vanColour);
        if (direction == RIGHT) {
            g.fillRect(x + 11 * size, y + 5 * size, 5 * size / 2, 2 * size);
        } else if (direction == LEFT) {
            g.fillRect(x + 3 * size/ 2, y + 5 * size, 5 * size / 2, 2 * size);
        }
        g.setColor(Color.BLACK);
        if (direction == RIGHT) {
            g.drawRect(x + 11 * size, y + 5 * size, 5 * size / 2, 2 * size);
        } else if (direction == LEFT) {
            g.drawRect(x + 3 * size / 2, y + 5 * size, 5 * size / 2, 2 * size);
        }
        g.setColor(vanColour);
        g.fillRect(x + 3 * size, y + 3 * size, 9 * size, 4 * size);
        g.setColor(Color.BLACK);
        g.drawRect(x + 3 * size, y + 3 * size, 9 * size, 4 * size);
        g.setColor(Color.WHITE);
        g.fillRect(x + 4 * size, y + 4 * size, size*2, size);
        g.fillRect(x + 6 * size, y + 4 * size, size, size);
        g.fillRect(x + 10 * size, y + 4 * size, size, size);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, size * 3/2));
        g.drawString(description, x + size * 15 / 4, y + size * 25 / 4);
        
    }
    
    public boolean contains(Point p) {
        Rectangle rect1 = new Rectangle(x + 3 * size, y + 3 * size, 9 * size, 4 * size);
        Rectangle rect2 = new Rectangle(x + 11 * size, y + 5 * size, 5 * size / 2, 2 * size);
        return rect1.contains(p) || rect2.contains(p);
    }

}

