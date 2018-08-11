package sphiinx.api.framework.ui.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.rspeer.ui.Log;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FXGUIBuilder extends Application {

    private final FXGUI FX_GUI;
    private Stage stage;
    private Scene scene;
    private boolean is_invoking;

    public FXGUIBuilder(FXGUI fx_gui) {
        this.FX_GUI = fx_gui;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        final FXMLLoader LOADER = new FXMLLoader();
        LOADER.setClassLoader(getClass().getClassLoader());
        final Parent ROOT = LOADER.load(getFXML());
        scene = new Scene(ROOT, FX_GUI.getWidth(), FX_GUI.getHeight());

        stage.setAlwaysOnTop(true);
        stage.setScene(scene);

        if (FX_GUI.showOnInvoke())
            stage.show();

        Platform.setImplicitExit(false);
    }

    private InputStream getFXML() throws IOException {
        switch (FX_GUI.getFXMLType()) {
            case URL:
                final URLConnection CONNECTION = new URL(FX_GUI.getFXML()).openConnection();
                //final BufferedReader READER = new BufferedReader(new InputStreamReader(CONNECTION.getInputStream()));
                return CONNECTION.getInputStream();
            case FILE:
                final Path PATH = Paths.get(FX_GUI.getFXML());
                return new ByteArrayInputStream(new String(Files.readAllBytes(PATH)).getBytes());
            case STRING:
                return new ByteArrayInputStream(FX_GUI.getFXML().getBytes());
        }

        return null;
    }

    public void invokeGUI() {
        is_invoking = true;
        Platform.runLater(() -> {
            try {
                start(new Stage());
                is_invoking = false;
            } catch (Exception e) {
                Log.severe("EXCEPTION");
                Log.severe(e.getCause());
                Log.severe(e.getStackTrace());
                e.printStackTrace();
            }
        });
    }

    public FXGUI getGUI() {
        return FX_GUI;
    }

    public void show() {
        if (stage == null)
            return;

        Platform.runLater(() -> stage.show());
    }

    public void hide() {
        if (stage == null)
            return;

        Platform.runLater(() -> stage.hide());
    }

    public void close() {
        if (stage == null)
            return;

        Platform.runLater(() -> stage.close());
    }

    public boolean isInvoked() {
        return is_invoking || stage.isShowing();
    }
}
