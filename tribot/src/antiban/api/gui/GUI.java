package antiban.api.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import antiban.api.game.timing.Waiting;

import javax.swing.*;
import java.net.URL;

public class GUI extends Application {

    private final String FXML;

    private Stage stage;

    private boolean is_shown = false;

    public GUI(String fxml) {
        this.FXML = fxml;

        SwingUtilities.invokeLater(() -> {
            new JFXPanel();
            Platform.runLater(() -> {
                try {
                    final Stage STAGE = new Stage();
                    start(STAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });

        waitForStage();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        final FXMLLoader LOADER = new FXMLLoader(new URL(FXML));
        LOADER.setClassLoader(getClass().getClassLoader());

        final Parent ROOT = LOADER.load();
        final GUIController CONTROLLER = LOADER.getController();

        CONTROLLER.setGUI(this);
        stage.setScene(new Scene(ROOT));
    }

    /**
     * Returns true if the GUI is shown; false otherwise.
     *
     * @return True if the GUI is shown; false otherwise.
     */
    public boolean isShown() {
        return is_shown;
    }

    /**
     * Shows the GUI.
     */
    public void show() {
        if (stage == null)
            return;

        is_shown = true;
        Platform.runLater(() -> stage.show());
    }

    /**
     * Closes the GUI.
     */
    public void close() {
        if (stage == null)
            return;

        is_shown = false;
        Platform.runLater(() -> stage.close());
    }

    /**
     * Waits for the initialization of the stage.
     */
    private void waitForStage() {
        Waiting.waitForCondition(() -> stage != null, 5000);
    }

}
