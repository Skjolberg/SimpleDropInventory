package net.shibacraft.simpledropinventory.files;

import de.leonhard.storage.Yaml;
import de.leonhard.storage.internal.settings.ConfigSettings;
import de.leonhard.storage.internal.settings.DataType;
import de.leonhard.storage.internal.settings.ReloadSettings;
import lombok.Getter;
import net.shibacraft.simpledropinventory.SimpleDropInventory;
import net.shibacraft.simpledropinventory.api.loader.Loader;
import net.shibacraft.simpledropinventory.files.config.ConfigCore;
import net.shibacraft.simpledropinventory.files.messages.Messages;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FileManager implements Loader {

    @Getter
    public static FileManager fileManager;
    private final SimpleDropInventory plugin;

    @Getter
    public static final Map<String, Yaml> filesYaml = new HashMap<>();
    private final Set<String> filesCheck = new HashSet<>();

    public FileManager(SimpleDropInventory plugin){
        this.plugin = plugin;
    }

    @Override
    public void load() {
        fileManager = this;
        filesCheck.add("Messages.yml");
        filesCheck.add("Config.yml");
        FileUtils.checkFiles(filesCheck);
        filesCheck.clear();

        filesYaml.put("Messages", new Yaml("Messages.yml", plugin.getDataFolder().getPath(), null,
                ReloadSettings.INTELLIGENT, ConfigSettings.PRESERVE_COMMENTS, DataType.SORTED));
        Messages.load();
        filesYaml.put("Config", new ConfigCore(new File(plugin.getDataFolder().getPath(), "Config.yml")));

    }

    @Override
    public void unload() {
        filesYaml.clear();
    }

    @Override
    public void reload() {
        filesYaml.get("Config").forceReload();
        filesYaml.get("Messages").forceReload();
    }

}
