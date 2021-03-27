package me.peridot.mckdisco.commands;

import me.peridot.mckdisco.MCKDisco;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscoCommand implements CommandExecutor {

    private final MCKDisco plugin;

    public DiscoCommand(MCKDisco plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("[MCKDisco] Tylko gracz na serwerze moze uzyc tej komendy!");
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("mckdisco.cmd")) {
            plugin.getConfiguration().getLangAPI().sendMessage(player, "messages.noperm");
            return true;
        }
        plugin.getInventoryManager().getArmorSelectInventory().open(player);
        return true;
    }
}
