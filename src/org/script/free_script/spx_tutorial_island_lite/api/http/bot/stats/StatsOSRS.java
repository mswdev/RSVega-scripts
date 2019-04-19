package org.script.free_script.spx_tutorial_island_lite.api.http.bot.stats;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.scene.Players;
import org.script.free_script.spx_tutorial_island_lite.api.game.player.Player;
import org.script.free_script.spx_tutorial_island_lite.api.game.questing.Questing;
import org.script.free_script.spx_tutorial_island_lite.api.http.RSVegaTracker;
import org.script.free_script.spx_tutorial_island_lite.api.http.wrappers.Request;

import java.io.IOException;

public class StatsOSRS {

    public static boolean updateStatsOSRS(int bot_id, RequestBody request_body) {
        try (final Response response = Request.put(RSVegaTracker.API_URL + "/bot/id/" + bot_id + "/stats/osrs/update", request_body)) {
            return response.isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static RequestBody getStatsOSRSDataRequestBody() {
        final FormBody.Builder form_builder = new FormBody.Builder();
        form_builder.add("is_tutorial", String.valueOf(Player.isTutorial()));
        form_builder.add("ironman_state", String.valueOf(Player.getIronManState().getState()));
        form_builder.add("position_x", String.valueOf(Players.getLocal().getX()));
        form_builder.add("position_y", String.valueOf(Players.getLocal().getY()));
        form_builder.add("position_z", String.valueOf(Players.getLocal().getFloorLevel()));
        form_builder.add("level_total", String.valueOf(Skills.getTotalLevel()));
        form_builder.add("level_combat", String.valueOf(Players.getLocal().getCombatLevel()));
        form_builder.add("level_attack", String.valueOf(Skills.getLevel(Skill.ATTACK)));
        form_builder.add("level_defence", String.valueOf(Skills.getLevel(Skill.DEFENCE)));
        form_builder.add("level_strength", String.valueOf(Skills.getLevel(Skill.STRENGTH)));
        form_builder.add("level_hitpoints", String.valueOf(Skills.getLevel(Skill.HITPOINTS)));
        form_builder.add("level_ranged", String.valueOf(Skills.getLevel(Skill.RANGED)));
        form_builder.add("level_prayer", String.valueOf(Skills.getLevel(Skill.PRAYER)));
        form_builder.add("level_magic", String.valueOf(Skills.getLevel(Skill.MAGIC)));
        form_builder.add("level_cooking", String.valueOf(Skills.getLevel(Skill.COOKING)));
        form_builder.add("level_woodcutting", String.valueOf(Skills.getLevel(Skill.WOODCUTTING)));
        form_builder.add("level_fletching", String.valueOf(Skills.getLevel(Skill.FLETCHING)));
        form_builder.add("level_fishing", String.valueOf(Skills.getLevel(Skill.FISHING)));
        form_builder.add("level_firemaking", String.valueOf(Skills.getLevel(Skill.FIREMAKING)));
        form_builder.add("level_crafting", String.valueOf(Skills.getLevel(Skill.CRAFTING)));
        form_builder.add("level_smithing", String.valueOf(Skills.getLevel(Skill.SMITHING)));
        form_builder.add("level_mining", String.valueOf(Skills.getLevel(Skill.MINING)));
        form_builder.add("level_herblore", String.valueOf(Skills.getLevel(Skill.HERBLORE)));
        form_builder.add("level_agility", String.valueOf(Skills.getLevel(Skill.AGILITY)));
        form_builder.add("level_thieving", String.valueOf(Skills.getLevel(Skill.THIEVING)));
        form_builder.add("level_slayer", String.valueOf(Skills.getLevel(Skill.SLAYER)));
        form_builder.add("level_farming", String.valueOf(Skills.getLevel(Skill.FARMING)));
        form_builder.add("level_runecrafting", String.valueOf(Skills.getLevel(Skill.RUNECRAFTING)));
        form_builder.add("level_hunter", String.valueOf(Skills.getLevel(Skill.HUNTER)));
        form_builder.add("level_construction", String.valueOf(Skills.getLevel(Skill.CONSTRUCTION)));
        form_builder.add("quest_points", String.valueOf(Questing.getPoints()));
        form_builder.add("quests_complete", Questing.getCompletedQuests());
        return form_builder.build();
    }
}
