package org.script.free_script.spx_tutorial_island_lite.api.script.framework.item_management;

public interface ItemManagement {

    ItemManagementEntry[] itemsToBuy();

    int[] itemsToSell();

    double sellPriceModifier();

    double buyPriceModifier();

}
