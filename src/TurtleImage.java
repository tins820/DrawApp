package drawapp;

import javafx.scene.Group;
import java.lang.Math;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class TurtleImage extends Group
{
    
    private int x, y, angle;
    private Color colour;

    void startAt(int xStart, int yStart, int angleStart)
    {        
        TurtleImage turtleImage = new TurtleImage();
        
        x = xStart;
        y= yStart;
        angle = angleStart;
    }

    void forward(int distance)
    {
        int xEnd, yEnd;
        
        xEnd = (int) ((-distance)*Math.sin(Math.toRadians(angle)));
        yEnd = (int) ((distance)*Math.cos(Math.toRadians(angle)));
        
        Line line = new Line(x, y, xEnd, yEnd) ;
        line.setStroke(colour);
        this.getChildren().add(line);
    }

    void direction(boolean left, int angleTurn)
    {
        if (left == true)
        {
            angle = angle - angleTurn;
        } else
        {
            angle = angle + angleTurn;
        }
    }

    void pen(boolean up)
    {
        if (up == true)
        {
           colour = null;
        } else
        {
           colour = Color.BLACK;
        }
    }
}
