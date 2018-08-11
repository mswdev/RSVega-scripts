package sphiinx.api;

import org.rspeer.networking.api.RsPeerApi;
import org.rspeer.script.Script;
import org.rspeer.ui.Log;
import sphiinx.api.framework.mission.Mission;
import sphiinx.api.framework.mission.MissionHandler;
import sphiinx.api.framework.ui.javafx.FXHandler;
import sphiinx.api.framework.ui.javafx.FXGUI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Queue;
import java.util.logging.Level;

public abstract class SPXScript extends Script {

    protected String script_data_path = getDataDirectory() + File.separator + "SPX" + File.separator + getMeta().name();
    private MissionHandler mission_handler;
    private FXHandler fxml_handler;

    @Override
    public void onStart() {
        Log.log(Level.WARNING, "Info", "Starting " + getMeta().name() + "!");
        createDirectoryFolders();

        fxml_handler = new FXHandler(getFXML());
        mission_handler = new MissionHandler(createMissionQueue());
    }

    @Override
    public int loop() {
        if (fxml_handler.isFinished())
            fxml_handler.invokeGUI();

        if (mission_handler.isStopped())
            setStopping(true);

        return mission_handler.execute();
    }

    @Override
    public void onStop() {
        if (fxml_handler.getGUI() != null)
            fxml_handler.close();

        Log.log(Level.WARNING, "Info", "Thanks for using " + getMeta().name() + ", " + RsPeerApi.getCurrentUserName() + "!");
    }

    /**
     * Creates the mission queue.
     *
     * @return The queue of missions.
     */
    public abstract Queue<Mission> createMissionQueue();

    /**
     * Sets the FXML for the GUI.
     *
     * @return The FXML for the GUI.
     */
    public abstract FXGUI getFXML();

    /**
     * Creates the script directories if they do not exist.
     */
    private void createDirectoryFolders() {
        final Path BOT_DIRECTORY_PATH = Paths.get(script_data_path);
        if (!Files.exists(BOT_DIRECTORY_PATH)) {
            try {
                Files.createDirectories(BOT_DIRECTORY_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

