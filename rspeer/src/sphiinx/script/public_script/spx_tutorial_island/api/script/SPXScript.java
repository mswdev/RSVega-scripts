package sphiinx.script.public_script.spx_tutorial_island.api.script;

import com.beust.jcommander.JCommander;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.Script;
import org.rspeer.ui.Log;
import sphiinx.script.public_script.spx_tutorial_island.api.client.screenshot.Screenshot;
import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.mission.MissionHandler;
import sphiinx.script.public_script.spx_tutorial_island.api.ui.fxui.FXGUI;
import sphiinx.script.public_script.spx_tutorial_island.api.ui.fxui.FXGUIBuilder;
import sphiinx.script.public_script.spx_tutorial_island.api.ui.swingui.GUI;
import sphiinx.script.public_script.spx_tutorial_island.api.ui.swingui.GUIBuilder;
import sphiinx.script.public_script.spx_tutorial_island.api.game.BankCache;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Queue;
import java.util.logging.Level;

public abstract class SPXScript extends Script implements RenderListener {

    protected String script_data_path = getDataDirectory() + File.separator + "SPX" + File.separator + getMeta().name();
    private MissionHandler mission_handler;
    private FXGUIBuilder fx_gui_builder;
    private GUIBuilder gui_builder;
    private BankCache bank_cache;

    @Override
    public void onStart() {
        Log.log(Level.FINE, "Info", "Starting " + getMeta().name() + "!");
        createDirectoryFolders();
        if (getArgs() != null && getArgs().length() > 0) {
            JCommander.newBuilder()
                    .addObject(getArguments())
                    .build()
                    .parse(getArgs().split(" "));
        }

        mission_handler = new MissionHandler(createMissionQueue());

        if (getFXGUI() != null) {
            fx_gui_builder = new FXGUIBuilder(getFXGUI());
            fx_gui_builder.invokeGUI();
        }

        if (getGUI() != null) {
            gui_builder = new GUIBuilder(getGUI());
            gui_builder.invokeGUI();
        }

        bank_cache = new BankCache(this);
        bank_cache.start();
    }

    @Override
    public int loop() {
        if (fx_gui_builder != null)
            if (fx_gui_builder.isInvoked())
                return 100;

        if (gui_builder != null)
            if (gui_builder.isInvoked())
                return 100;

        if (mission_handler.isStopped())
            setStopping(true);

        return mission_handler.execute();
    }

    @Override
    public void onStop() {
        if (fx_gui_builder != null)
            fx_gui_builder.close();

        if (gui_builder != null)
            gui_builder.close();

        Log.log(Level.FINE, "Info", "Thanks for using " + getMeta().name() + "!");
    }

    public abstract Queue<Mission> createMissionQueue();

    public Object getArguments() {
        return null;
    }

    public FXGUI getFXGUI() {
        return null;
    }

    public GUI getGUI() {
        return null;
    }

    public MissionHandler getMissionHandler() {
        return mission_handler;
    }

    public FXGUIBuilder getFXMLHandler() {
        return fx_gui_builder;
    }

    public GUIBuilder getGUIBuilder() {
        return gui_builder;
    }

    /**
     * Creates the script directories if they do not exist.
     */
    private void createDirectoryFolders() {
        final Path bot_directory_path = Paths.get(script_data_path);
        if (!Files.exists(bot_directory_path)) {
            try {
                Files.createDirectories(bot_directory_path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void notify(RenderEvent renderEvent) {
        Screenshot.renderQueue(renderEvent);
    }
}

