package me.peridot.mckdisco.util;

import api.peridot.periapi.packets.Reflection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EquipmentUtil {

    private final Class<?> packetPlayOutEntityEquipmentClass = Reflection.getMinecraftClass("PacketPlayOutEntityEquipment");
    private final Reflection.ConstructorInvoker packetPlayOutEntityEquipmentConstructor;

    private final Class<?> craftItemStackClass = Reflection.getCraftBukkitClass("inventory.CraftItemStack");
    private final Class<?> itemStackClass = Reflection.getMinecraftClass("ItemStack");
    private final Reflection.MethodInvoker itemStackMethod = Reflection.getMethod(craftItemStackClass, "asNMSCopy", ItemStack.class);

    private Class<?> enumItemSlotClass = null;
    private Reflection.MethodInvoker enumItemSlotMethod = null;


    public EquipmentUtil() {
        if (Reflection.serverVersionNumber >= 9) {
            enumItemSlotClass = Reflection.getMinecraftClass("EnumItemSlot");
            enumItemSlotMethod = Reflection.getMethod(enumItemSlotClass, "valueOf", String.class);
            packetPlayOutEntityEquipmentConstructor = Reflection.getConstructor(packetPlayOutEntityEquipmentClass, int.class, enumItemSlotClass, itemStackClass);
        } else {
            packetPlayOutEntityEquipmentConstructor = Reflection.getConstructor(packetPlayOutEntityEquipmentClass, int.class, int.class, itemStackClass);
        }
    }

    public Object createEquipmentPacket(Player player, int slot, ItemStack item, boolean own) {
        Object packet = null;
        Object itemStack = itemStackMethod.invoke(craftItemStackClass, item);
        if (enumItemSlotClass == null) {
            if (Reflection.serverVersionNumber <= 8) {
                if (slot == 1) {
                    slot = 0;
                } else if (slot >= 2 && slot <= 5) {
                    slot -= 2;
                } else if (slot > 5) {
                    slot = 0;
                }

                if (!own) slot += 1;
            }
            packet = packetPlayOutEntityEquipmentConstructor.invoke(player.getEntityId(), slot, itemStack);
        } else {
            Object enumItemSlot = null;
            if (slot == 0) enumItemSlot = enumItemSlotMethod.invoke(enumItemSlotClass, "MAINHAND");
            else if (slot == 1) enumItemSlot = enumItemSlotMethod.invoke(enumItemSlotClass, "OFFHAND");
            else if (slot == 2) enumItemSlot = enumItemSlotMethod.invoke(enumItemSlotClass, "FEET");
            else if (slot == 3) enumItemSlot = enumItemSlotMethod.invoke(enumItemSlotClass, "LEGS");
            else if (slot == 4) enumItemSlot = enumItemSlotMethod.invoke(enumItemSlotClass, "CHEST");
            else if (slot == 5) enumItemSlot = enumItemSlotMethod.invoke(enumItemSlotClass, "HEAD");
            packet = packetPlayOutEntityEquipmentConstructor.invoke(player.getEntityId(), enumItemSlot, itemStack);
        }
        return packet;
    }

}
