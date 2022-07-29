package net.shibacraft.simpledropinventory.listeners;

import de.leonhard.storage.Yaml;
import lombok.experimental.UtilityClass;
import net.shibacraft.simpledropinventory.files.FileManager;

import java.util.List;

@UtilityClass
public class UtilsListener {

    private final Yaml config = FileManager.getFilesYaml().get("Config");

    public boolean isWorldDisabled(String world) {
        List<String> worlds = config.getStringList("Disabled-Worlds");
        return worlds.contains(world);
    }

    public boolean isCollectExperience() {
        return config.getBoolean("Collect-Experience");
    }

    public boolean isCollectDrops() {
        return config.getBoolean("Collect-Drops");
    }


}
