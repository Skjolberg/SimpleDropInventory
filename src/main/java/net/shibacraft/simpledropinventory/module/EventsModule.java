package net.shibacraft.simpledropinventory.module;

import lombok.Getter;
import net.shibacraft.simpledropinventory.SimpleDropInventory;
import net.shibacraft.simpledropinventory.api.loader.Loader;
import net.shibacraft.simpledropinventory.listeners.BlockBreakListener;
import net.shibacraft.simpledropinventory.listeners.BlockDropItemListener;
import net.shibacraft.simpledropinventory.listeners.PlayerJoinListener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class EventsModule implements Loader {

    private final SimpleDropInventory plugin = SimpleDropInventory.getPlugin();
    private final PluginManager pluginManager = SimpleDropInventory.getPlugin().getServer().getPluginManager();
    @Getter
    public static final Set<UUID> drop = new HashSet<>();
    private final int version = SimpleDropInventory.getVERSION();

    @Override
    public void load() {

        if (version >= 13) {
            pluginManager.registerEvents(new BlockDropItemListener(), plugin);
        }
        if (version < 13) {
            pluginManager.registerEvents(new BlockBreakListener(), plugin);
        }
        pluginManager.registerEvents(new PlayerJoinListener(), plugin);

    }

    @Override
    public void unload() {
        HandlerList.unregisterAll(plugin);
    }

    @Override
    public void reload() {

    }


}
