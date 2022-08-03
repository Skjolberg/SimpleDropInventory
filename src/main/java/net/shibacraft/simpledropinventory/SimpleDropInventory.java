package net.shibacraft.simpledropinventory;

import lombok.Getter;
import net.shibacraft.simpledropinventory.module.MainModule;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleDropInventory extends JavaPlugin {

    @Getter
    static SimpleDropInventory plugin;
    @Getter
    static int VERSION;
    private MainModule mainModule;


    @Override
    public void onEnable() {
        SimpleDropInventory.plugin = this;

        String pack = this.getServer().getClass().getPackage().getName();
        String[] version = pack.substring(pack.lastIndexOf('.') + 1).split("_");
        VERSION = Integer.parseInt(version[1]);

        mainModule = new MainModule(this);
        mainModule.load();
    }

    @Override
    public void onDisable() {
        mainModule.unload();
    }

}
