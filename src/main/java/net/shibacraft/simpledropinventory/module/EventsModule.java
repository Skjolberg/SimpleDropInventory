package net.shibacraft.simpledropinventory.module;

import de.leonhard.storage.Yaml;
import net.shibacraft.simpledropinventory.SimpleDropInventory;
import net.shibacraft.simpledropinventory.api.loader.Loader;
import net.shibacraft.simpledropinventory.files.FileManager;
import net.shibacraft.simpledropinventory.listeners.*;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

public class EventsModule implements Loader {

    private final SimpleDropInventory plugin;
    private final PluginManager pluginManager;
    private final int version;
    private final BlockBreakListener blockBreakListener;
    private final Yaml config;

    public EventsModule(SimpleDropInventory plugin){
        this.plugin = plugin;
        this.pluginManager = SimpleDropInventory.getPlugin().getServer().getPluginManager();
        this.version = SimpleDropInventory.getVERSION();
        this.blockBreakListener = new BlockBreakListener();
        this.config = FileManager.getFilesYaml().get("Config");
    }

    @Override
    public void load() {

        if (version >= 13) {
            BlockDropItemListener blockDropItemListener = new BlockDropItemListener();
            pluginManager.registerEvents(blockDropItemListener, plugin);
        } else {
            LegacyBlockBreakListener legacyBlockBreakListener = new LegacyBlockBreakListener();
            pluginManager.registerEvents(legacyBlockBreakListener, plugin);
        }

        if (config.getBoolean("Player-Join-Drop")) {
            PlayerJoinListener playerJoinListener = new PlayerJoinListener();
            pluginManager.registerEvents(playerJoinListener, plugin);
        }
        pluginManager.registerEvents(blockBreakListener, plugin);

    }

    @Override
    public void unload() {
        HandlerList.unregisterAll(plugin);
    }

    @Override
    public void reload() {

    }


}
