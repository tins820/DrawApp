package drawapp;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) {

        final MainWindow main = new MainWindow();

        Platform.runLater(
                new Runnable() {
                    public void run() {
                        ImagePanel imagePanel = main.getImagePanel();
                        //Reader reader = new InputStreamReader(System.in);
                        String input = "DL 0 0 500 300\nSC orange\nDR 100 150 50 50";

                        Parser parser = new Parser(new StringReader(input), imagePanel, main);
                        parser.parse();
                    }
                });



    }

    public static void main(String[] args) {
        launch(args);
    }
}