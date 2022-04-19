package net.shibacraft.simpledropinventory.module;

import net.shibacraft.simpledropinventory.SimpleDropInventory;
import net.shibacraft.simpledropinventory.commands.Internal.CommandLoader;
import net.shibacraft.simpledropinventory.files.FileManager;
import net.shibacraft.simpledropinventory.api.loader.Loader;

public class MainModule implements Loader {

    private final SimpleDropInventory plugin;

    public MainModule(SimpleDropInventory plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        Loader loader = new FileManager(plugin);
        loader.load();

        loader = new CommandLoader(plugin);
        loader.load();
    }

    @Override
    public void unload() {

    }

    @Override
    public void reload() {

    }
}
