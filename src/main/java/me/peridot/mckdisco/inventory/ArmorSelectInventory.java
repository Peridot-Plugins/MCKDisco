package me.peridot.mckdisco.inventory;

import api.peridot.periapi.inventories.InventoryContent;
import api.peridot.periapi.inventories.items.InventoryItem;
import api.peridot.periapi.inventories.providers.InventoryProvider;
import api.peridot.periapi.utils.replacements.Replacement;
import me.peridot.mckdisco.MCKDisco;
import me.peridot.mckdisco.data.Config;
import me.peridot.mckdisco.enums.DiscoType;
import me.peridot.mckdisco.user.User;
import org.bukkit.entity.Player;

public class ArmorSelectInventory implements InventoryProvider {

    private final MCKDisco plugin;

    public ArmorSelectInventory(MCKDisco plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init(Player player, InventoryContent content) {
        Config config = plugin.getConfiguration();
        User user = plugin.getUserCache().createUser(player);

        content.clear();
        if (player.hasPermission("mckdisco.random")) {
            content.addItem(InventoryItem.builder()
                    .item(config.getItemBuilder("inventory.buttons.random"))
                    .consumer(event -> {
                        user.setDiscoType(DiscoType.RANDOM);
                        plugin.getConfiguration().getLangAPI().sendMessage(player, "messages.selecteffect", new Replacement("{EFFECT}", config.getColoredString("messages.effects.random")));
                    })
                    .build());
        }
        if (player.hasPermission("mckdisco.smooth")) {
            content.addItem(InventoryItem.builder()
                    .item(config.getItemBuilder("inventory.buttons.smooth"))
                    .consumer(event -> {
                        user.setDiscoType(DiscoType.SMOOTH);
                        plugin.getConfiguration().getLangAPI().sendMessage(player, "messages.selecteffect", new Replacement("{EFFECT}", config.getColoredString("messages.effects.smooth")));
                    })
                    .build());
        }
        if (player.hasPermission("mckdisco.police")) {
            content.addItem(InventoryItem.builder()
                    .item(config.getItemBuilder("inventory.buttons.police"))
                    .consumer(event -> {
                        user.setDiscoType(DiscoType.POLICE);
                        plugin.getConfiguration().getLangAPI().sendMessage(player, "messages.selecteffect", new Replacement("{EFFECT}", config.getColoredString("messages.effects.police")));
                    })
                    .build());
        }
        if (player.hasPermission("mckdisco.gray")) {
            content.addItem(InventoryItem.builder()
                    .item(config.getItemBuilder("inventory.buttons.gray"))
                    .consumer(event -> {
                        user.setDiscoType(DiscoType.GRAY);
                        plugin.getConfiguration().getLangAPI().sendMessage(player, "messages.selecteffect", new Replacement("{EFFECT}", config.getColoredString("messages.effects.gray")));
                    })
                    .build());
        }
        content.addItem(InventoryItem.builder()
                .item(config.getItemBuilder("inventory.buttons.disable"))
                .consumer(event -> {
                    user.setDiscoType(null);
                    plugin.getConfiguration().getLangAPI().sendMessage(player, "messages.disableeffect");
                })
                .build());
    }

    @Override
    public void update(Player player, InventoryContent content) {
    }
}
