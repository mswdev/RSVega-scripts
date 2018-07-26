package sphiinx.script.public_script.spx_aio_woodcutting.api;

import org.rspeer.script.Script;
import org.rspeer.ui.Log;
import sphiinx.script.public_script.spx_aio_woodcutting.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_aio_woodcutting.api.framework.mission.MissionHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Queue;
import java.util.logging.Level;

public abstract class SPXScript extends Script {

    private MissionHandler mission_handler;

    @Override
    public void onStart() {
        Log.log(Level.WARNING, "Info", "Starting " + getMeta().name() + "!");
        createDirectoryFolders();
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
        Log.log(Level.WARNING, "Info", "Thanks for using " + getMeta().name() + "!");
    }

    /**
     * Creates the mission queue.
     *
     * @return The queue of missions.
     */
    public abstract Queue<Mission> createMissionQueue();

    /**
     * Creates the script directories if they do not exist.
     */
    private void createDirectoryFolders() {
        final Path BOT_DIRECTORY_PATH = Paths.get(Script.getDataDirectory() + File.separator + "SPX" + File.separator + getMeta().name());
        if (!Files.exists(BOT_DIRECTORY_PATH)) {
            try {
                Files.createDirectories(BOT_DIRECTORY_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

