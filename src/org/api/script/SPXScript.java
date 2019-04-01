package org.api.script;

import com.beust.jcommander.JCommander;
import org.api.client.screenshot.Screenshot;
import org.api.game.BankCache;
import org.api.http.RSVegaTracker;
import org.api.http.RSVegaTrackerThread;
import org.api.script.framework.item_management.ItemManagement;
import org.api.script.framework.item_management.ItemManagementEntry;
import org.api.script.framework.item_management.ItemManagementTracker;
import org.api.script.framework.mission.Mission;
import org.api.script.framework.mission.MissionHandler;
import org.api.script.impl.mission.item_management_mission.ItemManagementMission;
import org.api.ui.fxui.FXGUI;
import org.api.ui.fxui.FXGUIBuilder;
import org.api.ui.swingui.GUI;
import org.api.ui.swingui.GUIBuilder;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.Script;
import org.rspeer.ui.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public abstract class SPXScript extends Script implements RenderListener {

    public BankCache bank_cache;
    protected String script_data_path = getDataDirectory() + File.separator + "SPX" + File.separator + getMeta().name();
    private MissionHandler mission_handler;
    private FXGUIBuilder fx_gui_builder;
    private GUIBuilder gui_builder;
    private ItemManagementTracker item_management_tracker;
    private MissionHandler item_management_mission_handler;
    private ScheduledThreadPoolExecutor thread_pool_executor;

    @Override
    public void onStart() {
        Log.log(Level.FINE, "Info", "Starting " + getMeta().name() + "!");

        RSVegaTracker.insertAccount();
        RSVegaTracker.insertBot();
        RSVegaTracker.insertSession(getMeta().name(), new Date());
        scheduleThreadPool();

        createDirectoryFolders();
        if (getArguments() != null && getArgs() != null && getArgs().length() > 0) {
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

        final ItemManagementEntry item_management_entry = getReadyItemManagementEntry();
        if (item_management_entry != null || item_management_mission_handler != null) {
            if (item_management_mission_handler == null)
                item_management_mission_handler = new MissionHandler(new LinkedList<>(Collections.singleton(new ItemManagementMission(this, item_management_entry, item_management_tracker, item_management_tracker.getItemManagement().itemsToSell()))));

            if (!item_management_mission_handler.isStopped())
                return item_management_mission_handler.execute();
            else
                item_management_mission_handler = null;
        }

        return mission_handler.execute();
    }

    @Override
    public void onStop() {
        RSVegaTracker.updateSession(new Date());
        thread_pool_executor.shutdown();

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

    @Override
    public void notify(RenderEvent renderEvent) {
        Screenshot.renderQueue(renderEvent);
    }

    /**
     * Schedules the thread pool executor.
     */
    private void scheduleThreadPool() {
        thread_pool_executor = new ScheduledThreadPoolExecutor(1);
        thread_pool_executor.scheduleAtFixedRate(new RSVegaTrackerThread(), 30, 30, TimeUnit.SECONDS);
    }

    /**
     * Gets a ready item management entry that has an item that can be bought.
     *
     * @return A ready item management entry.
     */
    private ItemManagementEntry getReadyItemManagementEntry() {
        final Mission current_mission = mission_handler.getCurrent();
        if (!(current_mission instanceof ItemManagement))
            return null;

        if (item_management_tracker == null || item_management_tracker.getItemManagement() != current_mission)
            item_management_tracker = new ItemManagementTracker(this, (ItemManagement) current_mission);

        item_management_tracker.update();
        return item_management_tracker.shouldBuy();
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
}

