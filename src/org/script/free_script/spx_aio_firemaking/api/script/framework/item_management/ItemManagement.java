package org.script.free_script.spx_aio_firemaking.api.script.framework.item_management;

public interface ItemManagement {

    ItemManagementEntry[] itemsToBuy();

    int[] itemsToSell();

    double sellPriceModifier();

    double buyPriceModifier();

}
