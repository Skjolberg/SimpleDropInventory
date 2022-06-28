package net.shibacraft.simpledropinventory.api.banner;

import net.shibacraft.simpledropinventory.SimpleDropInventory;
import net.shibacraft.simpledropinventory.api.loader.Loader;
import net.shibacraft.simpledropinventory.api.logger.CoreLogger;

public class Banner implements Loader {

    private final SimpleDropInventory plugin = SimpleDropInventory.getPlugin();

    @Override
    public void load() {
        CoreLogger.info("&bPlugin: &e" + plugin.getName());
        CoreLogger.info("&fAuthor: &e" + plugin.getDescription().getAuthors().get(0));
        CoreLogger.info("&fVersion: &e" + plugin.getDescription().getVersion());
    }

    @Override
    public void unload() {

    }

    @Override
    public void reload() {

    }

}
