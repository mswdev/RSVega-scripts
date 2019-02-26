package org.api.script.framework.item_management;

public interface ItemManagement {

    ItemManagementEntry[] itemsToBuy();

    int[] itemsToSell();

    double sellPriceModifier();

    double buyPriceModifier();

}
