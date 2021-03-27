package me.peridot.mckdisco.user;

import me.peridot.mckdisco.enums.DiscoType;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class User {

    private final UUID uuid;

    private DiscoType discoType;
    private ItemStack[] armor;
    private boolean realArmorPacket;

    public User(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public DiscoType getDiscoType() {
        return discoType;
    }

    public void setDiscoType(DiscoType discoType) {
        this.discoType = discoType;
    }

    public ItemStack[] getArmor() {
        return armor;
    }

    public void setArmor(ItemStack[] armor) {
        this.armor = armor;
    }

    public boolean isRealArmorPacket() {
        return realArmorPacket;
    }

    public void setRealArmorPacket(boolean realArmorPacket) {
        this.realArmorPacket = realArmorPacket;
    }
}
