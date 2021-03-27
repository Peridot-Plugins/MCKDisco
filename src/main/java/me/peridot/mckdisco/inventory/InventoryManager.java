package me.peridot.mckdisco.inventory;

import api.peridot.periapi.inventories.CustomInventory;
import api.peridot.periapi.inventories.PeriInventoryManager;
import me.peridot.mckdisco.MCKDisco;
import org.bukkit.event.inventory.InventoryType;

public class InventoryManager {

    private final MCKDisco plugin;
    private final PeriInventoryManager manager;

    private final CustomInventory armorSelectInventory;

    public InventoryManager(MCKDisco plugin) {
        this.plugin = plugin;
        this.manager = plugin.getPeriAPI().getInventoryManager();

        armorSelectInventory = CustomInventory.builder()
                .plugin(plugin)
                .manager(manager)
                .provider(new ArmorSelectInventory(plugin))
                .inventoryType(InventoryType.HOPPER)
                .title(plugin.getConfiguration().getColoredString("inventory.title"))
                .updateDelay(-1)
                .build();

    }

    public CustomInventory getArmorSelectInventory() {
        return armorSelectInventory;
    }
}
