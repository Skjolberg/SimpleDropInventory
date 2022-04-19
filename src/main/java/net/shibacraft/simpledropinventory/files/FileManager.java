package net.shibacraft.simpledropinventory.files;

import de.leonhard.storage.Yaml;
import lombok.Getter;
import net.shibacraft.simpledropinventory.SimpleDropInventory;
import net.shibacraft.simpledropinventory.api.loader.Loader;
import net.shibacraft.simpledropinventory.files.messages.Messages;

import java.util.HashMap;
import java.util.Map;

public class FileManager implements Loader {

    private final SimpleDropInventory plugin;

    @Getter
    private static final Map<String, Yaml> filesYaml = new HashMap<>();


    public FileManager(SimpleDropInventory plugin) {
        this.plugin = plugin;
    }


    @Override
    public void load() {
        Messages.load();
    }

    @Override
    public void unload() {

    }

    @Override
    public void reload() {

    }
}
