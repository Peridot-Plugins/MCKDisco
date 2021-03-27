package me.peridot.mckdisco.util;

import org.apache.commons.lang.Validate;
import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.concurrent.ThreadLocalRandom;

public class Util {

    public static int getRandom(int min, int max) throws IllegalArgumentException {
        Validate.isTrue(max > min, "Max can't be smaller than min!");
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static ItemStack createColorArmor(ItemStack item, Color color) {
        if (!item.getType().toString().split("_")[0].equalsIgnoreCase("LEATHER")) return item;
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
        itemMeta.setColor(color);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static Color randomColor() {
        int red = getRandom(0, 255);
        int green = getRandom(0, 255);
        int blue = getRandom(0, 255);

        return Color.fromRGB(red, green, blue);
    }
}
