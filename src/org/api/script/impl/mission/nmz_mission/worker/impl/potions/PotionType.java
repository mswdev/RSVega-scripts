package org.api.script.impl.mission.nmz_mission.worker.impl.potions;

import org.rspeer.runetek.api.component.tab.Inventory;

import java.util.Arrays;

public enum PotionType {

    SUPER_RANGING("Super ranging", "Super ranging potion", 0, 3951, -1),
    SUPER_MAGIC("Super magic", "Super magic potion", 3, 3952, -1),
    OVERLOAD("Overload", "Overload potion", 6, 3953, 3955),
    ABSORPTION("Absorption", "Absorption potion", 9, 3954, 3956);

    private final String name;
    private final String barrel_name;
    private final int shop_interface_id;
    private final int amount_owned_varpbit;
    private final int active_varpbit;

    PotionType(String name, String barrel_name, int shop_interface_id, int amount_owned_varpbit, int active_varpbit) {
        this.name = name;
        this.barrel_name = barrel_name;
        this.shop_interface_id = shop_interface_id;
        this.amount_owned_varpbit = amount_owned_varpbit;
        this.active_varpbit = active_varpbit;
    }

    public static int getCount(PotionType potion_type) {
        return Arrays.stream(Inventory.getItems()).filter(a -> a.getName().contains(potion_type.getName())).mapToInt(a -> Integer.parseInt(a.getName().replaceAll("[^0-9]+", ""))).sum();
    }

    public String getName() {
        return name;
    }

    public String getBarrelName() {
        return barrel_name;
    }

    public int getShopInterfaceID() {
        return shop_interface_id;
    }

    public int getAmountOwnedVarpbit() {
        return amount_owned_varpbit;
    }

    public int getActiveVarpbit() {
        return active_varpbit;
    }
}

