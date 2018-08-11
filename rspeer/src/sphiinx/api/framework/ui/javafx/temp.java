package sphiinx.api.framework.ui.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.rspeer.runetek.api.commons.Time;

import javax.swing.*;
import java.net.URL;

public class temp extends Application {

    private final URL fxml;
    private final URL stylesheet;

    private Stage stage;
    private Scene scene;
    private boolean decorated = true;

    private boolean isOpen = false;

    public temp(URL fxml) {
        this(fxml, null);
    }

    public temp(URL fxml, boolean decorated) {
        this(fxml, null, decorated);
    }

    public temp(URL fxml, URL stylesheet) {
        this(fxml, stylesheet, true);
    }

    public temp(URL fxml, URL stylesheet, boolean decorated) {

        this.fxml = fxml;
        this.stylesheet = stylesheet;
        this.decorated = decorated;

        SwingUtilities.invokeLater(() -> {
            new JFXPanel();
            Platform.runLater(() -> {
                try {
                    final Stage stage = new Stage();

                    if (!this.decorated)//Add This
                        stage.initStyle(StageStyle.TRANSPARENT);

                    start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
        //waitForInit();
    }

    public Scene getScene() {
        return this.scene;
    }

    public Stage getStage() {
        return this.stage;
    }

    public void start(Stage stage) throws Exception {
        System.out.println("Startingg");
        if (fxml == null) {
            System.out.println("Null");
            return;
        }

        this.stage = stage;

        stage.setAlwaysOnTop(true);

        Platform.setImplicitExit(false);

        FXMLLoader loader = new FXMLLoader(fxml);

        loader.setClassLoader(this.getClass().getClassLoader());

        Parent box = loader.load();

        FXGUIBuilder controller = loader.getController();

        System.out.println("Aye");
        if (controller == null)// Add this
            return;

        //controller.setFXML(this); //Add this

        scene = new Scene(box);

        if (!this.decorated) {//Add this
            scene.setFill(Color.TRANSPARENT);
        }

        if (this.stylesheet != null) //Add this
            scene.getStylesheets().add(this.stylesheet.toExternalForm());

        stage.setScene(scene);

        System.out.println("Here");
        stage.setResizable(false);//Add this

    }

    public void show() {
        if (stage == null)
            return;

        isOpen = true;
        System.out.println("Showing");
        Platform.runLater(() -> stage.show());
    }

    public void close() {
        if (stage == null)
            return;

        isOpen = false;
        Platform.runLater(() -> stage.close());
    }

    public boolean isOpen() {
        return isOpen;
    }

    private void waitForInit() {
        Time.sleepUntil(() -> stage != null, 5000);
    }
}

