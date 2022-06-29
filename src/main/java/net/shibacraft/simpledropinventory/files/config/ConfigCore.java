package net.shibacraft.simpledropinventory.files.config;

import de.leonhard.storage.Yaml;
import de.leonhard.storage.internal.settings.ConfigSettings;
import de.leonhard.storage.internal.settings.DataType;
import de.leonhard.storage.internal.settings.ReloadSettings;
import net.shibacraft.simpledropinventory.SimpleDropInventory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ConfigCore extends Yaml {

    public ConfigCore(File yaml) {
        super(yaml.getName(), SimpleDropInventory.getPlugin().getDataFolder().getPath(), null,
                ReloadSettings.INTELLIGENT, ConfigSettings.PRESERVE_COMMENTS, DataType.SORTED);

        this.setDefault("Disabled-Worlds", new ArrayList<>(Arrays.asList("mine", "lobby")));
        this.setDefault("Player-Join-Drop", true);
        this.setDefault("Collect-Experience", true);

    }

}