package org.api.ui.fxui;

public class FXGUIBuilder /*extends Application */ {

    /*private final FXGUI fx_gui;
    private Stage stage;
    private Scene scene;
    private boolean is_invoking = true;*/

    public FXGUIBuilder(FXGUI fx_gui) {
        /*this.fx_gui = fx_gui;*/
    }

    /*@Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        FXMLLoader loader = new FXMLLoader();
        loader.setClassLoader(getClass().getClassLoader());
        byte[] fxml_bytes = null;
        switch (fx_gui.getFXMLType()) {
            case URL:
                final URLConnection connection = new URL(fx_gui.getFXML()).openConnection();
                final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                fxml_bytes = reader.lines().collect(Collectors.joining("\n")).getBytes();
                break;
            case FILE:
                final Path path = Paths.get(fx_gui.getFXML());
                fxml_bytes = Files.readAllBytes(path);
                break;
            case STRING:
                fxml_bytes = fx_gui.getFXML().getBytes();
                break;
        }

        final Parent root = loader.load(new ByteArrayInputStream(fxml_bytes));
        scene = new Scene(root, fx_gui.getWidth(), fx_gui.getHeight());

        stage.setTitle(fx_gui.getTitle());
        stage.setResizable(fx_gui.isResizable());
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);

        if (fx_gui.showOnInvoke())
            stage.show();

        Platform.setImplicitExit(false);
        is_invoking = false;
    }*/

    public void invokeGUI() {
        /*new JFXPanel();
        Platform.runLater(() -> {
            try {
                start(new Stage());
                is_invoking = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        });*/
    }

    public FXGUI getGUI() {
        /*return fx_gui;*/
        return null;
    }

    public void show() {
        /*if (stage == null)
            return;

        Platform.runLater(() -> stage.show());*/
    }

    public void hide() {
        /*if (stage == null)
            return;

        Platform.runLater(() -> stage.hide());*/
    }

    public void close() {
        /*if (stage == null)
            return;

        Platform.runLater(() -> stage.close());*/
    }

    public boolean isInvoked() {
       /* if (fx_gui == null)
            return false;

        return is_invoking || stage.isShowing();*/

        return false;
    }
}
