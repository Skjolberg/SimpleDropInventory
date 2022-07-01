package net.shibacraft.simpledropinventory.listeners;

import de.leonhard.storage.Yaml;
import net.shibacraft.simpledropinventory.files.FileManager;

import java.util.List;

public class UtilsListener {

    private static Yaml config = FileManager.getFilesYaml().get("Config");

    public static boolean isWorldDisabled(String world) {
        List<String> worlds = FileManager.getFilesYaml().get("Config").getStringList("Disabled-Worlds");
        return worlds.contains(world);
    }

    public static boolean isCollectExperience() {
        return config.getBoolean("Collect-Experience");
    }

    public static boolean isCollectDrops() {
        return config.getBoolean("Collect-Drops");
    }

    public static void reload() {
        config = FileManager.getFilesYaml().get("Config");
    }


}
