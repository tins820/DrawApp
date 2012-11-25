package drawapp;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class ImagePanel extends Group
{

    private Paint colourView = null;
    private Rectangle background;
    public TurtleImage turtleImage = new TurtleImage();
    
    public ImagePanel(int width, int height)
    {
        setImageSize(width, height);
        
    }

    public void setImageSize(int width, int height)
    {
        background = new Rectangle(0, 0, width, height);
        background.setFill(null);
        this.getChildren().add(background);
    }

    /*protected void paintComponent(Graphics g)
     {
     g.setColor(Color.GREY);
     g.fillRect(0, 0, image.getWidth(), image.getHeight());
     g.drawImage(image,0,0,this);
     }*/
    public void setBackgroundColour(Color colour)
    {
        background.setFill(colourView);
    }

    public void clear(Color colour)
    {
        setBackgroundColour(colour);
    }

    public void setColour(Color colour)
    {
        colourView = colour;
    }

    public void drawLine(int x1, int y1, int x2, int y2)
    {
        Line line = new Line(x1, y1, x2, y2);
        this.getChildren().add(line);
    }

    public void drawRect(int x1, int y1, int x2, int y2)
    {
        Rectangle rect = new Rectangle(x1, y1, x2, y2);
        rect.setStroke(null);
        rect.setFill(colourView);
        this.getChildren().add(rect);
    }

    public void fillRect(int x1, int y1, int x2, int y2)
    {
        Rectangle rect = new Rectangle(x1, y1, x2, y2);
        rect.setStroke(colourView);
        rect.setFill(null);
        this.getChildren().add(rect);
    }

    public void drawString(int x, int y, String s)
    {
        Label label = new Label(s);
        this.getChildren().add(label);
    }

    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
    {
        Arc arc = new Arc(x, y, width, height, startAngle, arcAngle);
        this.getChildren().add(arc);
    }

    public void drawOval(int x, int y, int width, int height)
    {
        Ellipse oval = new Ellipse(x, y, width, height);
        this.getChildren().add(oval);
    }

    public void colourGradient(Color colour1, Color colour2)
    {
        LinearGradient gradient1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[]
                {
                    new Stop(0, colour1),
                    new Stop(1, colour2)
                });
    }

    public void loadImage(int x, int y, int w, int h, String fileName)
    {
        Image image = new Image("fileName");
        ImageView imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitHeight(h);
        imageView.setFitWidth(w);
        this.getChildren().add(imageView);
    }
}
