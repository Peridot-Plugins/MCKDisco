package me.peridot.mckdisco.listener;

import me.peridot.mckdisco.MCKDisco;
import me.peridot.mckdisco.user.User;
import me.peridot.mckdisco.util.EquipmentUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class PlayerToggleSneakListener implements Listener {

    private final MCKDisco plugin;
    private final EquipmentUtil equipment;

    public PlayerToggleSneakListener(MCKDisco plugin) {
        this.plugin = plugin;
        this.equipment = new EquipmentUtil();
    }

    @EventHandler(ignoreCancelled = true)
    public void onSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        User user = plugin.getUserCache().createUser(player);

        if (event.isSneaking()) {
            user.setArmor(player.getInventory().getArmorContents());
        } else {
            user.setRealArmorPacket(true);
        }
    }

}
