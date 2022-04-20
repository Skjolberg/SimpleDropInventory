package net.shibacraft.simpledropinventory.module;

import net.shibacraft.simpledropinventory.SimpleDropInventory;
import net.shibacraft.simpledropinventory.api.loader.Loader;
import net.shibacraft.simpledropinventory.listeners.BlockDropItemListener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

public class EventsModule implements Loader {

    private final SimpleDropInventory plugin;
    private final PluginManager pluginManager;
    private final BlockDropItemListener blockDropItemListener = new BlockDropItemListener();

    public EventsModule(SimpleDropInventory plugin) {
        this.plugin = plugin;
        this.pluginManager = plugin.getServer().getPluginManager();
    }

    @Override
    public void load() {
        pluginManager.registerEvents(blockDropItemListener, plugin);
    }

    @Override
    public void unload() {
        HandlerList.unregisterAll(plugin);
    }

    @Override
    public void reload() {

    }



}
