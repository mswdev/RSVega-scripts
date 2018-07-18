package sphiinx.api;

import org.rspeer.script.Script;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SphiinxScript extends TaskScript {

    @Override
    public void onStart() {
        Log.fine("Info", "Starting " + getMeta().name());
        createDirectoryFolders();
        loop();
    }

    /**
     * Creates the Sphiinx script and bot directories if they do not exist.
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

    @Override
    public void onStop() {
        Log.fine("Info", "Thanks for using " + getMeta().name() + "!");
        super.onStop();
    }

}

