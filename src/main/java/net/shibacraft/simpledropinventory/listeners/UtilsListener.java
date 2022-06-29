package net.shibacraft.simpledropinventory.listeners;

import net.shibacraft.simpledropinventory.files.FileManager;

import java.util.List;

public class UtilsListener {

    public static boolean isWorldDisabled(String world) {
        List<String> worlds = FileManager.getFilesYaml().get("Config").getStringList("DisabledWorlds");
        return worlds.contains(world);
    }

}
