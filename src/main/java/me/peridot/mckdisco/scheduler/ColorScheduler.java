package me.peridot.mckdisco.scheduler;

import me.peridot.mckdisco.MCKDisco;
import org.bukkit.Bukkit;

public class ColorScheduler {

    public static int redSmooth = 255;
    public static int greenSmooth = 0;
    public static int blueSmooth = 0;
    public static int grayShade = 0;
    public static int grayShadeCycle = 0;
    public static int policeCycle = 0;
    public static int sharpPoliceCycle = 0;
    public static int redPolice = 255;
    public static int bluePolice = 0;
    private final MCKDisco plugin;

    public ColorScheduler(MCKDisco plugin) {
        this.plugin = plugin;
    }

    public void start() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            //SMOOTH
            if (redSmooth > 0 && blueSmooth == 0) {
                redSmooth -= 3;
                greenSmooth += 3;
            }
            if (greenSmooth > 0 && redSmooth == 0) {
                greenSmooth -= 3;
                blueSmooth += 3;
            }
            if (blueSmooth > 0 && greenSmooth == 0) {
                redSmooth += 3;
                blueSmooth -= 3;
            }

            //GRAY
            if (grayShadeCycle == 0) {
                grayShade += 3;
            }
            if (grayShade >= 255) {
                grayShadeCycle = 1;
            }

            if (grayShadeCycle == 1) {
                grayShade -= 3;
            }
            if (grayShade == 0) {
                grayShadeCycle = 0;
            }

            //POLICE
            if (plugin.getConfiguration().getBoolean("police-smooth")) {
                if (redPolice >= 255) {
                    policeCycle = 1;
                }
                if (policeCycle == 1) {
                    redPolice -= 10;
                    bluePolice += 10;
                }

                if (bluePolice >= 255) {
                    policeCycle = 0;
                }
                if (policeCycle == 0) {
                    bluePolice -= 10;
                    redPolice += 10;
                }
                if (redPolice > 255) redPolice = 255;
                if (bluePolice > 255) bluePolice = 255;
            } else {
                if (policeCycle >= 1) {
                    sharpPoliceCycle -= 1;
                } else {
                    sharpPoliceCycle += 1;
                }
                if (sharpPoliceCycle >= 5) {
                    policeCycle = 1;
                    bluePolice = 255;
                    redPolice = 0;
                } else if (sharpPoliceCycle <= 0) {
                    policeCycle = 0;
                    redPolice = 255;
                    bluePolice = 0;
                }
            }
        }, 0, 1);
    }

}
