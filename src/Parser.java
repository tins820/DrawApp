package drawapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class Parser
{
    
    private BufferedReader reader;
    private ImagePanel image;
    private MainWindow frame;
    private Button drawButton;
    
    public Parser(Reader reader, ImagePanel image, MainWindow frame)
    {
        this.reader = new BufferedReader(reader);
        this.image = image;
        this.frame = frame;
        drawButton = this.frame.drawButton;
    }
    
    public void parse()
    {
        
        drawButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                try
                {
                    String line = reader.readLine();
                    if (line != null)
                    {
                        parseLine(line);
                    } else
                    {
                        drawButton.setVisible(false);
                    }
                    
                } catch (IOException ex)
                {
                    frame.postMessage("Parse failed.");
                    return;
                } catch (ParseException ex)
                {
                    frame.postMessage("Parse Exception: " + ex.getMessage());
                    return;
                }
                frame.postMessage("Drawing completed\n");
            }
        });
    }
    
    private void parseLine(String line) throws ParseException
    {
        if (line.length() < 2)
        {
            return;
        }
        String command = line.substring(0, 2);
        if (command.equals("DL"))
        {
            try
            {
                drawLine(line.substring(2, line.length()));
            } catch (ParseException ex)
            {
                throw new ParseException(ex.getMessage() + " drawLine");
            }
            return;
        }
        if (command.equals("DR"))
        {
            try
            {
                drawRect(line.substring(2, line.length()));
            } catch (ParseException ex)
            {
                throw new ParseException(ex.getMessage() + " drawRect");
            }
            return;
        }
        if (command.equals("FR"))
        {
            try
            {
                fillRect(line.substring(2, line.length()));
            } catch (ParseException ex)
            {
                throw new ParseException(ex.getMessage() + " fillRect");
            }
            return;
        }
        if (command.equals("SC"))
        {
            try
            {
                setColour(line.substring(3, line.length()));
            } catch (ParseException ex)
            {
                throw new ParseException(ex.getMessage() + " setColour");
            }
            return;
        }
        if (command.equals("DS"))
        {
            try
            {
                drawString(line.substring(3, line.length()));
            } catch (ParseException ex)
            {
                throw new ParseException(ex.getMessage() + " drawString");
            }
            return;
        }
        if (command.equals("DA"))
        {
            try
            {
                drawArc(line.substring(2, line.length()));
            } catch (ParseException ex)
            {
                throw new ParseException(ex.getMessage() + " drawArc");
            }
            return;
        }
        if (command.equals("DO"))
        {
            try
            {
                drawOval(line.substring(2, line.length()));
            } catch (ParseException ex)
            {
                throw new ParseException(ex.getMessage() + " drawOval");
            }
            return;
        }
        
        if (command.equals("CG"))
        {
            try
            {
                colourGradient(line.substring(2, line.length()));
            } catch (ParseException ex)
            {
                throw new ParseException(ex.getMessage() + " colourGradient");
            }
            return;
        }
        
        if (command.equals("IM"))
        {
            try
            {
                loadImage(line.substring(2, line.length()));
            } catch (ParseException ex)
            {
                throw new ParseException(ex.getMessage() + " colourGradient");
            }
            return;
        }
        if (command.equals("SD"))
        {
            try
            {
                setDimension(line.substring(2, line.length()));
            } catch (ParseException ex)
            {
                throw new ParseException(ex.getMessage() + " setDimension");
            }
            return;
        }
        
        if (command.equals("SA"))
        {
            try
            {
                startAt(line.substring(2, line.length()));
            } catch (ParseException ex)
            {
                throw new ParseException(ex.getMessage() + "startAt");
            }
            return;
        }
        
        if (command.equals("TF"))
        {
            try
            {
                forward(line.substring(2, line.length()));
            } catch (ParseException ex)
            {
                throw new ParseException(ex.getMessage() + " foward");
            }
            return;
        }
        
        if (command.equals("TD"))
        {
            try
            {
                direction(line.substring(2, line.length()));
            } catch (ParseException ex)
            {
                throw new ParseException(ex.getMessage() + " direction");
            }
            return;
        }
        
         if (command.equals("TP"))
        {
            try
            {
                pen(line.substring(2, line.length()));
            } catch (ParseException ex)
            {
                throw new ParseException(ex.getMessage() + " pen");
            }
            return;
        }
        
        
        throw new ParseException("Unknown drawing command");
    }
    
    private void drawLine(String args) throws ParseException
    {
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        x1 = getInteger(tokenizer);
        y1 = getInteger(tokenizer);
        x2 = getInteger(tokenizer);
        y2 = getInteger(tokenizer);
        image.drawLine(x1, y1, x2, y2);
    }
    
    private void drawRect(String args) throws ParseException
    {
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        x1 = getInteger(tokenizer);
        y1 = getInteger(tokenizer);
        x2 = getInteger(tokenizer);
        y2 = getInteger(tokenizer);
        image.drawRect(x1, y1, x2, y2);
    }
    
    private void fillRect(String args) throws ParseException
    {
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        x1 = getInteger(tokenizer);
        y1 = getInteger(tokenizer);
        x2 = getInteger(tokenizer);
        y2 = getInteger(tokenizer);
        image.fillRect(x1, y1, x2, y2);
    }
    
    private void drawArc(String args) throws ParseException
    {
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;
        int startAngle = 0;
        int arcAngle = 0;
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        x = getInteger(tokenizer);
        y = getInteger(tokenizer);
        width = getInteger(tokenizer);
        height = getInteger(tokenizer);
        startAngle = getInteger(tokenizer);
        arcAngle = getInteger(tokenizer);
        image.drawArc(x, y, width, height, startAngle, arcAngle);
    }
    
    private void drawOval(String args) throws ParseException
    {
        
        int x1 = 0;
        int y1 = 0;
        int width = 0;
        int height = 0;
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        x1 = getInteger(tokenizer);
        y1 = getInteger(tokenizer);
        width = getInteger(tokenizer);
        height = getInteger(tokenizer);
        image.drawOval(x1, y1, width, height);
        
    }
    
    private void drawString(String args) throws ParseException
    {
        int x = 0;
        int y = 0;
        String s = "";
        StringTokenizer tokenizer = new StringTokenizer(args);
        x = getInteger(tokenizer);
        y = getInteger(tokenizer);
        int position = args.indexOf("@");
        if (position == -1)
        {
            throw new ParseException("DrawString string is missing");
        }
        s = args.substring(position + 1, args.length());
        image.drawString(x, y, s);
    }
    
    private void setColour(String colourName) throws ParseException
    {
        image.setColour(checkColour(colourName));
    }
    
    private void colourGradient(String s) throws ParseException
    {
        Color colour1, colour2;
        String c1 = "";
        String c2 = "";
        StringTokenizer tokenizer = new StringTokenizer(s);
        c1 = tokenizer.nextToken();
        c2 = tokenizer.nextToken();
        
        colour1 = checkColour(c1);
        colour2 = checkColour(c2);
        
        image.colourGradient(colour1, colour2);
        
    }
    
    public void loadImage(String s) throws ParseException
    {
         int x = 0;
        int y = 0;
        int w = 0;
        int h = 0;
        String fileName = "";
        
        StringTokenizer tokenizer = new StringTokenizer(s);
        x = getInteger(tokenizer);
        y = getInteger(tokenizer);
        h = getInteger(tokenizer);
        w = getInteger(tokenizer);
        fileName = tokenizer.nextToken();
        
        image.loadImage(x, y, h, w, fileName);
    }
    
    private void setDimension(String args) throws ParseException
    {
        
        int x = 0;
        int y = 0;   
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        x= getInteger(tokenizer);
        y = getInteger(tokenizer);
        image.setImageSize(x, y);        
    }   
    
    private void startAt(String args) throws ParseException
    {
        int x;
        int y;
        int angle;
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        x = getInteger(tokenizer);
        y = getInteger(tokenizer);
        angle = getInteger(tokenizer);
        image.turtleImage.startAt(x, y, angle);
    }
    
    private void forward(String args) throws ParseException
    {
        int distance;
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        distance = getInteger(tokenizer);
        image.turtleImage.forward(distance);
    }
    
    private void direction(String args) throws ParseException
    {
        int direction;
        int angle;
        boolean left;
                
        StringTokenizer tokenizer = new StringTokenizer(args);
        direction = getInteger(tokenizer);
        angle =  getInteger(tokenizer);
        
        if (direction == 0)
        {
            left = false;
        }else
        {
            left = true;
        }       
        
        image.turtleImage.direction(left, angle);
    }
    
    private void pen(String args) throws ParseException
    {
        int pen;
        boolean up;
                
        StringTokenizer tokenizer = new StringTokenizer(args);
        pen = getInteger(tokenizer);
        
        if (pen == 0)
        {
            up = false;
        }else
        {
            up = true;
        }       
        
        image.turtleImage.pen(up);
    }
    
    
    
    private Color checkColour(String colourName) throws ParseException
    {
        
        if (colourName.equals("black"))
        {
            return (Color.BLACK);
        }
        if (colourName.equals("blue"))
        {
            return (Color.BLUE);
            
        }
        if (colourName.equals("cyan"))
        {
            return (Color.CYAN);
        }
        if (colourName.equals("darkgray"))
        {
            return (Color.DARKGREY);
        }
        if (colourName.equals("gray"))
        {
            return (Color.GREY);
        }
        if (colourName.equals("green"))
        {
            return (Color.GREEN);
        }
        if (colourName.equals("lightgray"))
        {
            return (Color.LIGHTGRAY);
        }
        if (colourName.equals("magenta"))
        {
            return (Color.MAGENTA);
        }
        if (colourName.equals("orange"))
        {
            return (Color.ORANGE);
        }
        if (colourName.equals("pink"))
        {
            return (Color.PINK);
        }
        if (colourName.equals("red"))
        {
            return (Color.RED);
        }
        if (colourName.equals("white"))
        {
            return (Color.WHITE);
        }
        if (colourName.equals("yellow"))
        {
            return (Color.YELLOW);
        }
        throw new ParseException("Invalid colour name");        
    }
    
    private int getInteger(StringTokenizer tokenizer) throws ParseException
    {
        if (tokenizer.hasMoreTokens())
        {
            return Integer.parseInt(tokenizer.nextToken());
        } else
        {
            throw new ParseException("Missing Integer value");
        }
    }
}
