package sphiinx.api.framework.ui.javafx;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.ui.Log;

import javax.swing.*;
import java.io.IOException;

public class FXHandler {

    private final FXGUI FX_GUI;
    private Scene scene;
    private boolean is_invoked;

    public FXHandler(FXGUI fx_gui) {
        this.FX_GUI = fx_gui;
    }

    public void invokeGUI() {
        SwingUtilities.invokeLater(() -> {
            new JFXPanel();
            Platform.runLater(() -> {
                try {
                    Log.fine("Here");
                    FX_GUI.setStage(new Stage());
                    initialize(FX_GUI.getStage());
                    is_invoked = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
    }

    private void initialize(Stage stage) {
        if (FX_GUI.getFXMLURL() == null && FX_GUI.getFXMLString() == null)
            return;

        Log.fine("Aye");
        stage.setAlwaysOnTop(true);
        Platform.setImplicitExit(false);
        Log.fine("No");

        FXMLLoader loader = new FXMLLoader();
        if (FX_GUI.getFXMLURL() != null) {
            loader = new FXMLLoader(FX_GUI.getFXMLURL());
        } else {
            //todo Load by string
        }

        Log.fine("Okkkk");
        loader.setClassLoader(this.getClass().getClassLoader());
        try {
            final Parent ROOT = loader.load();
            scene = new Scene(ROOT, FX_GUI.getWidth(), FX_GUI.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.fine("Set");
        stage.setScene(scene);

        if (FX_GUI.showOnInvoke())
            stage.showAndWait();
    }


    public FXGUI getGUI() {
        return FX_GUI;
    }

    public void show() {
        if (FX_GUI.getStage() == null)
            return;

        Platform.runLater(() -> FX_GUI.getStage().show());
    }

    public void hide() {
        if (FX_GUI.getStage() == null)
            return;

        Platform.runLater(() -> FX_GUI.getStage().hide());
    }

    public void close() {
        if (FX_GUI.getStage() == null)
            return;

        Platform.runLater(() -> FX_GUI.getStage().close());
    }

    public void waitUntilFinished() {
        while (isFinished() || FX_GUI.getStage().isShowing())
            Time.sleep(500);
    }

    public boolean isFinished() {
        return is_invoked;
    }

}

