package net.shibacraft.simpledropinventory.module;

import net.shibacraft.simpledropinventory.SimpleDropInventory;
import net.shibacraft.simpledropinventory.api.loader.Loader;
import net.shibacraft.simpledropinventory.listeners.BlockBreakListener;
import net.shibacraft.simpledropinventory.listeners.LegacyBlockBreakListener;
import net.shibacraft.simpledropinventory.listeners.BlockDropItemListener;
import net.shibacraft.simpledropinventory.listeners.PlayerJoinListener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

public class EventsModule implements Loader {

    private final SimpleDropInventory plugin = SimpleDropInventory.getPlugin();
    private final PluginManager pluginManager = SimpleDropInventory.getPlugin().getServer().getPluginManager();
    private final int version = SimpleDropInventory.getVERSION();
    private final BlockBreakListener blockBreakListener = new BlockBreakListener();
    private final PlayerJoinListener playerJoinListener = new PlayerJoinListener();


    @Override
    public void load() {

        if (version >= 13) {
            pluginManager.registerEvents(new BlockDropItemListener(), plugin);
        }
        if (version < 13) {
            pluginManager.registerEvents(new LegacyBlockBreakListener(), plugin);
        }
        pluginManager.registerEvents(playerJoinListener, plugin);
        pluginManager.registerEvents(blockBreakListener, plugin);

    }

    @Override
    public void unload() {
        HandlerList.unregisterAll(plugin);
    }

    @Override
    public void reload() {
        playerJoinListener.reload();
        blockBreakListener.reload();
    }


}
