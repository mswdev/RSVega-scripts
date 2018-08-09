package sphiinx.api;

import org.rspeer.script.Script;
import org.rspeer.ui.Log;
import sphiinx.api.framework.mission.Mission;
import sphiinx.api.framework.mission.MissionHandler;
import sphiinx.api.framework.ui.SPXScriptGUI;
import sphiinx.api.framework.ui.SPXScriptGUIHandler;

import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Queue;
import java.util.logging.Level;

public abstract class SPXScript extends Script {

    protected String script_data_path = getDataDirectory() + File.separator + "SPX" + File.separator + getMeta().name();
    private SPXScriptGUIHandler spx_gui_handler;
    private MissionHandler mission_handler;

    @Override
    public void onStart() {
        Log.log(Level.WARNING, "Info", "Starting " + getMeta().name() + "!");
        createDirectoryFolders();

        spx_gui_handler = new SPXScriptGUIHandler(setGUI(this));
        if (spx_gui_handler.getGUI() != null) {
            setPaused(true);
            spx_gui_handler.initialize();
        }

        mission_handler = new MissionHandler(createMissionQueue());
    }

    @Override
    public int loop() {
        if (mission_handler.isStopped())
            setStopping(true);

        return mission_handler.execute();
    }

    @Override
    public void onStop() {
        spx_gui_handler.getGUI().getFrame().dispatchEvent(new WindowEvent(spx_gui_handler.getGUI().getFrame(), WindowEvent.WINDOW_CLOSING));
        Log.log(Level.WARNING, "Info", "Thanks for using " + getMeta().name() + "!");
    }

    /**
     * Creates the mission queue.
     *
     * @return The queue of missions.
     */
    public abstract Queue<Mission> createMissionQueue();

    /**
     * Sets the GUI to the specified GUI.
     *
     * @return The GUI to set.
     * */
    public abstract SPXScriptGUI setGUI(SPXScript script);

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

