package org.api.game.questing;

import org.rspeer.runetek.api.Varps;

public enum QuestType {

    //Free
    BLACK_KNIGHTS_FORTRESS(130, 0, 4, false),
    COOKS_ASSISTANT(29, 0, 2, false),
    DEMON_SLAYER(222, 0, 22220899, false),
    DORICS_QUEST(31, 0, 100, false),
    DRAGON_SLAYER(176, 0, 10, false),
    ERNEST_THE_CHICKEN(32, 0, 3, false),
    GOBLIN_DIPLOMACY(62, 0, 6, false),
    IMP_CATCHER(160, 0, 2, false),
    THE_KNIGHTS_SWORD(122, 0, 7, false),
    PIRATES_TREASURE(71, 0, 4, false),
    PRINCE_ALI_RESCUE(273, 0, 110, false),
    THE_RESTLESS_GHOST(107, 0, 5, false),
    ROMEO_AND_JULIET(144, 0, 100, false),
    RUNE_MYSTERIES(63, 0, 6, false),
    SHEEP_SHEARER(179, 0, 21, false),
    SHIELD_OF_ARRAV(146, 0, 4, false),
    VAMPIRE_SLAYER(178, 0, 3, false),
    WITCHS_POTION(67, 0, 3, false),

    //Members
    ANIMAL_MAGNETISM(0, 0, 0, true),
    ANOTHER_SLICE_OF_HAM(0, 0, 0, true),
    BETWEEN_A_ROCK(0, 0, 0, true),
    BIG_CHOMPY_BIRD_HUNTING(0, 0, 0, true),
    BIOHAZARD(0, 0, 0, true),
    CABIN_FEVER(0, 0, 0, true),
    CLOCK_TOWER(0, 0, 0, true),
    COLD_WAR(0, 0, 0, true),
    CONTACT(0, 0, 0, true),
    CREATURE_OF_FENKENSTRAIN(0, 0, 0, true),
    DARKNESS_OF_HALLOWVALE(0, 0, 0, true),
    DEATH_PLATEAU(314, 0, 80, true),
    DEATH_TO_THE_DORGESHUUN(0, 0, 0, true),
    DESERT_TREASURE(0, 0, 0, true),
    DEVIOUS_MINDS(0, 0, 0, true),
    THE_DIG_SITE(0, 0, 0, true),
    DREAM_MENTOR(0, 0, 0, true),
    DRUIDIC_RITUAL(80, 0, 4, true),
    DWARF_CANNON(0, 0, 0, true),
    EADGARS_RUSE(0, 0, 0, true),
    EAGLES_PEAK(0, 0, 0, true),
    ELEMENTAL_WORKSHOP_I(0, 0, 0, true),
    ELEMENTAL_WORKSHOP_II(0, 0, 0, true),
    ENAKHRAS_LAMENT(0, 0, 0, true),
    ENLIGHTENED_JOURNEY(0, 0, 0, true),
    THE_EYES_OF_GLOUPHRIE(0, 0, 0, true),
    FAIRYTALE_I_GROWING_PAINTS(671, 0, 0, true),
    FAIRYTALE_II_CURE_A_QUEEN(0, 0, 0, true),
    FAMILY_CREST(0, 0, 0, true),
    THE_FEUD(0, 0, 0, true),
    FIGHT_AREA(17, 0, 15, true),
    FISHING_CONTEST(0, 0, 0, true),
    FORGETTABLE_TALE(0, 0, 0, true),
    THE_FREMENNIK_ISLES(0, 0, 0, true),
    THE_FREMENNIK_TRIALS(347, 0, 0, true),
    GARDEN_OF_TRANQUILITY(0, 0, 0, true),
    GERTRUDES_CAT(180, 0, 6, true),
    GHOSTS_AHOY(0, 0, 0, true),
    THE_GIANT_DWARF(0, 0, 0, true),
    THE_GOLEM(0, 0, 0, true),
    THE_GRAND_TREE(150, 0, 160, true),
    THE_GREAT_BRAIN_ROBBERY(0, 0, 0, true),
    GRIM_TALES(0, 0, 0, true),
    THE_HAND_IN_THE_SAND(0, 0, 0, true),
    HAUNTED_MINE(0, 0, 0, true),
    HAZEEL_CULT(0, 0, 0, true),
    HEROES_QUEST(0, 0, 0, true),
    HOLY_GRAIL(0, 0, 0, true),
    HORROR_FROM_THE_DEEP(0, 0, 0, true),
    ICTHLARINS_LITTLE_HELPER(0, 0, 0, true),
    IN_AID_OF_THE_MYREQUE(705, 0, 0, true),
    IN_SEARCH_OF_THE_MYREQUE(387, 0, 110, true),
    JUNGLE_POTION(175, 0, 7, true),
    KINGS_RANSOM(0, 0, 0, true),
    LEGENDS_QUEST(0, 0, 0, true),
    LOST_CITY(0, 0, 0, true),
    THE_LOST_TRIBE(0, 0, 0, true),
    LUNAR_DIPLOMACY(0, 0, 0, true),
    MAKING_HISTORY(0, 0, 0, true),
    MERLINS_CRYSTAL(0, 0, 0, true),
    MONKS_FRIEND(30, 0, 80, true),
    MONKEY_MADNESS_I(0, 0, 0, true),
    MONKEY_MADNESS_II(0, 0, 0, true),
    MOUNTAIN_DAUGHTER(0, 0, 0, true),
    MOURNINGS_END_PART_I(0, 0, 0, true),
    MOURNINGS_END_PART_II(0, 0, 0, true),
    MURDER_MYSTERY(0, 0, 0, true),
    MY_ARMS_BIG_ADVENTURE(0, 0, 0, true),
    NATURE_SPIRIT(307, 0, 110, true),
    OBSERVATORY_QUEST(0, 0, 0, true),
    OLAFS_QUEST(0, 0, 0, true),
    ONE_SMALL_FAVOUR(416, 0, 280, true),
    PLAGUE_CITY(0, 0, 0, true),
    PRIEST_IN_PERIL(0, 0, 0, true),
    RAG_AND_BONE_MAN(0, 0, 0, true),
    RATCATCHERS(0, 0, 0, true),
    RECIPE_FOR_DISASTER(0, 0, 0, true),
    RECRUITMENT_DRIVE(0, 0, 0, true),
    REGICIDE(0, 0, 0, true),
    ROVING_ELVES(0, 0, 0, true),
    ROYAL_TROUBLE(0, 0, 0, true),
    RUM_DEAL(0, 0, 0, true),
    SCORPION_CATCHER(0, 0, 0, true),
    SEA_SLUG(0, 0, 0, true),
    SHADES_OF_MORTTON(0, 0, 0, true),
    SHADOW_OF_THE_STORM(0, 0, 0, true),
    SHEEP_HERDER(0, 0, 0, true),
    SHILO_VILLAGE(116, 0, 15, true),
    THE_SLUG_MENACE(0, 0, 0, true),
    A_SOULS_BANE(0, 0, 0, true),
    SPIRITS_OF_THE_ELID(0, 0, 0, true),
    SWAN_SONG(723, 0, 1071511752, true),
    TAI_BWO_WANNAI_TRIO(0, 0, 0, true),
    A_TAIL_OF_TWO_CATS(0, 0, 0, true),
    TEARS_OF_GUTHIX(0, 0, 0, true),
    TEMPLE_OF_IKOV(0, 0, 0, true),
    THRONE_OF_MISCELLANIA(0, 0, 0, true),
    THE_TOURIST_TRAP(0, 0, 0, true),
    TOWER_OF_LIFE(0, 0, 0, true),
    TREE_GNOME_VILLAGE(111, 0, 9, true),
    TRIBAL_TOTEM(0, 0, 0, true),
    TROLL_ROMANCE(0, 0, 0, true),
    TROLL_STRONGHOLD(0, 0, 0, true),
    UNDERGROUND_PASS(0, 0, 0, true),
    WANTED(0, 0, 0, true),
    WATCHTOWER(0, 0, 0, true),
    WATERFALL_QUEST(65, 0, 10, true),
    WHAT_LIES_BELOW(0, 0, 0, true),
    WITCHES_HOUSE(226, 0, 7, true),
    ZORGE_FLESH_EATERS(0, 0, 0, true);

    private int setting;
    private int not_started;
    private int complete;
    private boolean members;

    QuestType(int setting, int not_started, int is_complete, boolean is_members) {
        this.setting = setting;
        this.not_started = not_started;
        this.complete = is_complete;
        this.members = is_members;
    }

    public boolean isComplete() {
        if (this == SHIELD_OF_ARRAV) return isShieldOfArravComplete();
        return Varps.get(setting) == complete;
    }

    public boolean hasStarted() {
        if (this == SHIELD_OF_ARRAV) return isShieldOfArravStarted();
        return Varps.get(setting) != not_started && !isComplete();
    }

    public boolean isMembers() {
        return members;
    }

    private boolean isShieldOfArravComplete() {
        int black = Varps.get(146);
        int phoenix = Varps.get(145);
        return black == 4 || phoenix == 7;
    }

    private boolean isShieldOfArravStarted() {
        int black = Varps.get(146);
        int phoenix = Varps.get(145);
        return black != 0 && phoenix != 0 && !isShieldOfArravComplete();
    }

    private boolean isShieldOfArravNotStarted() {
        int black = Varps.get(146);
        int phoenix = Varps.get(145);
        return black == 0 && phoenix == 0;
    }
}
