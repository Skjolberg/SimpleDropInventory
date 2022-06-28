package net.shibacraft.simpledropinventory.files;

import de.leonhard.storage.Yaml;
import de.leonhard.storage.internal.settings.ConfigSettings;
import de.leonhard.storage.internal.settings.DataType;
import de.leonhard.storage.internal.settings.ReloadSettings;
import lombok.Getter;
import net.shibacraft.simpledropinventory.SimpleDropInventory;
import net.shibacraft.simpledropinventory.api.loader.Loader;
import net.shibacraft.simpledropinventory.api.utils.FileUtils;
import net.shibacraft.simpledropinventory.files.messages.Messages;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FileManager implements Loader {

    @Getter
    public static FileManager fileManager;
    private final SimpleDropInventory plugin = SimpleDropInventory.getPlugin();

    @Getter
    private static final Map<String, Yaml> filesYaml = new HashMap<>();
    private static final Set<String> filesCheck = new HashSet<>();

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
        filesYaml.put("Config", new Yaml("Config.yml", plugin.getDataFolder().getPath(), null,
                ReloadSettings.INTELLIGENT, ConfigSettings.PRESERVE_COMMENTS, DataType.SORTED));

    }

    @Override
    public void unload() {
        filesYaml.clear();
        filesCheck.clear();
    }

    @Override
    public void reload() {
        filesYaml.get("Config").forceReload();
        filesYaml.get("Messages").forceReload();
    }

}