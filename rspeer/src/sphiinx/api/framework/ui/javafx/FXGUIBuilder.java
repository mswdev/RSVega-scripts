package sphiinx.api.framework.ui.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FXGUIBuilder extends Application {

    private final FXGUI FX_GUI;
    private Stage stage;
    private Scene scene;
    private boolean is_invoking = true;

    public FXGUIBuilder(FXGUI fx_gui) {
        this.FX_GUI = fx_gui;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        FXMLLoader LOADER = new FXMLLoader();
        LOADER.setClassLoader(getClass().getClassLoader());
        byte[] fxml_bytes = null;
        switch (FX_GUI.getFXMLType()) {
            case URL:
                final URLConnection CONNECTION = new URL(FX_GUI.getFXML()).openConnection();
                final BufferedReader READER = new BufferedReader(new InputStreamReader(CONNECTION.getInputStream()));
                fxml_bytes = READER.lines().collect(Collectors.joining("\n")).getBytes();
                break;
            case FILE:
                final Path PATH = Paths.get(FX_GUI.getFXML());
                fxml_bytes = Files.readAllBytes(PATH);
                break;
            case STRING:
                fxml_bytes = FX_GUI.getFXML().getBytes();
                break;
        }

        final Parent ROOT = LOADER.load(new ByteArrayInputStream(fxml_bytes));
        scene = new Scene(ROOT, FX_GUI.getWidth(), FX_GUI.getHeight());

        stage.setTitle(FX_GUI.getTitle());
        stage.setResizable(FX_GUI.isResizable());
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);

        if (FX_GUI.showOnInvoke())
            stage.show();

        Platform.setImplicitExit(false);
        is_invoking = false;
    }

    public void invokeGUI() {
        if (FX_GUI == null)
            return;

        new JFXPanel();
        Platform.runLater(() -> {
            try {
                start(new Stage());
                is_invoking = false;
            } catch (Exception e) {
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
        if (FX_GUI == null)
            return false;

        return is_invoking || stage.isShowing();
    }
}
