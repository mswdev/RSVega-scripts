package sphiinx.api;

import org.rspeer.networking.api.RsPeerApi;
import org.rspeer.script.Script;
import org.rspeer.ui.Log;
import sphiinx.api.framework.mission.Mission;
import sphiinx.api.framework.mission.MissionHandler;
import sphiinx.api.framework.ui.javafx.FXGUIBuilder;
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
    private MissionHandler MISSION_HANDLER = new MissionHandler(createMissionQueue());
    private final FXGUIBuilder FXML_HANDLER = new FXGUIBuilder(getFXGUI());

    @Override
    public void onStart() {
        Log.log(Level.WARNING, "Info", "Starting " + getMeta().name() + "!");
        createDirectoryFolders();

        FXML_HANDLER.invokeGUI();
    }

    @Override
    public int loop() {
        if (FXML_HANDLER.isInvoked())
            return 150;

        if (MISSION_HANDLER.isStopped())
            setStopping(true);

        return MISSION_HANDLER.execute();
    }

    @Override
    public void onStop() {
        if (FXML_HANDLER.getGUI() != null)
            FXML_HANDLER.close();

        Log.log(Level.WARNING, "Info", "Thanks for using " + getMeta().name() + ", " + RsPeerApi.getCurrentUserName() + "!");
    }

    /**
     * Creates the mission queue.
     *
     * @return The queue of missions.
     */
    public abstract Queue<Mission> createMissionQueue();

    /**
     * Sets the FXGUI for the script.
     *
     * @return The FXGUI for the script.
     */
    public abstract FXGUI getFXGUI();

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

