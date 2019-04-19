package org.script.paid_script.spx_tutorial_island.api.script.framework.item_management;

public interface ItemManagement {

    ItemManagementEntry[] itemsToBuy();

    int[] itemsToSell();

    double sellPriceModifier();

    double buyPriceModifier();

}
