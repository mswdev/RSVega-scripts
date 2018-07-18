package antiban.api;

import org.tribot.api.General;
import org.tribot.api2007.util.ThreadSettings;
import org.tribot.script.Script;
import org.tribot.script.interfaces.*;
import org.tribot.util.Util;
import antiban.api.gui.GUI;
import antiban.priority_framework.PriorityEvent;
import antiban.priority_framework.PriorityEventHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AntibanScript extends Script implements Ending {

    private final String SERVER_IP = "206.189.210.158";

    private final int SERVER_PORT = 1024;

    protected final ClientSocketManager CLIENT_SOCKET_MANAGER = new ClientSocketManager(SERVER_IP, SERVER_PORT);

    protected final PriorityEventHandler PRIORITY_EVENT_HANDLER = new PriorityEventHandler();

    protected GUI gui = getGUI();

    protected abstract GUI getGUI();

    @Override
    public void run() {
        ThreadSettings.get().setClickingAPIUseDynamic(true);
        createDirectoryFolders();

        General.sleep(150);
        if (this.gui != null) {
            this.gui.show();
            PRIORITY_EVENT_HANDLER.setEventStatus("Initializing");

            while (this.gui.isShown())
                sleep(200);
        }

        // [TODO - 7/3/18]: Possibly remove this
        //addEvents();
        PRIORITY_EVENT_HANDLER.iterate(150);
    }

    @Override
    public void onEnd() {
        if (this.gui != null)
            this.gui.close();

        // [TODO - 6/20/18]: Change this log status.
        //Logging.status("Thanks for using " + Client.getManifest(this.getClass()).name() + ", " + General.getTRiBotUsername() + "!");
        CLIENT_SOCKET_MANAGER.close();
    }

    /**
     * Creates the Antiban and bot directories if they do not exist.
     */
    private void createDirectoryFolders() {
        final Path BOT_DIRECTORY_PATH = Paths.get(Util.getWorkingDirectory().getAbsolutePath() + File.separator + "Antiban" + File.separator + Client.getManifest(this.getClass()).name());
        if (!Files.exists(BOT_DIRECTORY_PATH)) {
            try {
                Files.createDirectories(BOT_DIRECTORY_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds all of the given events to the list of events.
     *
     * @param events The tasks to be added to the task list.
     */
    public void addEvents(PriorityEvent... events) {
        PRIORITY_EVENT_HANDLER.addEvent(events);
    }

}
