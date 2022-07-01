package net.shibacraft.simpledropinventory.module;

import de.leonhard.storage.Yaml;
import lombok.Getter;
import net.shibacraft.simpledropinventory.SimpleDropInventory;
import net.shibacraft.simpledropinventory.api.loader.Loader;
import net.shibacraft.simpledropinventory.files.FileManager;
import net.shibacraft.simpledropinventory.listeners.*;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

public class EventsModule implements Loader {

    private final SimpleDropInventory plugin = SimpleDropInventory.getPlugin();
    private final PluginManager pluginManager = SimpleDropInventory.getPlugin().getServer().getPluginManager();
    private final int version = SimpleDropInventory.getVERSION();
    private final BlockBreakListener blockBreakListener = new BlockBreakListener();
    private final Yaml config = FileManager.getFilesYaml().get("Config");

    @Override
    public void load() {

        if (version >= 13) {
            BlockDropItemListener blockDropItemListener = new BlockDropItemListener();
            pluginManager.registerEvents(blockDropItemListener, plugin);
        }
        if (version < 13) {
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
        UtilsListener.reload();
    }


}
