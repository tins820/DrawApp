package drawapp;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class MainWindow extends Stage
{

    public static final int DEFAULT_WIDTH = 500;
    public static final int DEFAULT_HEIGHT = 300;
    private int width;
    private int height;
    private ImagePanel imagePanel;
    private TextArea messageView;
    public Button quitButton, drawButton, saveButton;

    public MainWindow()
    {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public MainWindow(int width, int height)
    {
        super.setTitle("Draw App");
        this.width = width;
        this.height = height;
        buildGUI();
        /*setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.pack();
         this.setVisible(true);*/
    }

    private void buildGUI()
    {
        Stage backPanel = new Stage();

        VBox vBox = new VBox(5);

        imagePanel = new ImagePanel(width, height);

        messageView = new TextArea();
        messageView.setPrefRowCount(6);
        messageView.setEditable(false);

        quitButton = new Button("Close Window");
        drawButton = new Button("Draw the Next Part");
        saveButton = new Button("Save the Picture");

        EventHandler generateButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                WritableImage wi = imagePanel.snapshot(new SnapshotParameters(), null);
                BufferedImage image;
                image = javafx.embed.swing.SwingFXUtils.fromFXImage(wi, null);
                File file = new File("src/out.png");
                System.out.print("hi");
                try
                {
                    Graphics2D gd = (Graphics2D) image.getGraphics();
                    ImageIO.write(image, "png", file);
                } catch (IOException e)
                {
                };
            }
        };
        saveButton.setOnAction(generateButtonHandler);

        quitButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                MainWindow.this.close();
            }
        });


        vBox.getChildren().addAll(imagePanel, messageView, drawButton, saveButton, quitButton);

        StackPane root = new StackPane();

        root.getChildren()
                .add(vBox);

        Scene scene = new Scene(root, this.width, this.height + 300);

        this.setTitle(
                "Hello World!");
        this.setScene(scene);

        this.show();
    }

    private void setScreenshotButtonListener(final Group canvas)
    {
    }

    public ImagePanel getImagePanel()
    {
        return imagePanel;
    }

    public void postMessage(final String s)
    {
        Platform.runLater(
                new Runnable()
                {
                    public void run()
                    {
                        messageView.appendText(s);
                    }
                });
    }
}