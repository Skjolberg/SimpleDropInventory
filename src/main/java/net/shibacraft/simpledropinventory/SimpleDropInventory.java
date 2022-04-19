package net.shibacraft.simpledropinventory;

import lombok.Getter;
import net.shibacraft.simpledropinventory.api.loader.Loader;
import net.shibacraft.simpledropinventory.module.MainModule;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleDropInventory extends JavaPlugin {

    @Getter
    static SimpleDropInventory plugin;
    private Loader loader;

    @Override
    public void onEnable() {
        SimpleDropInventory.plugin = this;
        loader = new MainModule(this);
        loader.load();
    }

    @Override
    public void onDisable() {
        loader.unload();
    }

}
