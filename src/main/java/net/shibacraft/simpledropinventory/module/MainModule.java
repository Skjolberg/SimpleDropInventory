package net.shibacraft.simpledropinventory.module;

import lombok.Getter;
import net.shibacraft.simpledropinventory.SimpleDropInventory;
import net.shibacraft.simpledropinventory.api.analytics.MetricsProvider;
import net.shibacraft.simpledropinventory.api.analytics.Updater;
import net.shibacraft.simpledropinventory.api.banner.Banner;
import net.shibacraft.simpledropinventory.commands.Internal.CommandLoader;
import net.shibacraft.simpledropinventory.commands.Internal.CommandTranslatorProvider;
import net.shibacraft.simpledropinventory.files.FileManager;
import net.shibacraft.simpledropinventory.api.loader.Loader;

public class MainModule implements Loader {

    @Getter
    private static MainModule mainModule;
    private StorageModule storageModule;
    private FileManager fileManager;
    private EventsModule eventsModule;
    private final SimpleDropInventory plugin;

    public MainModule(SimpleDropInventory plugin){
        this.plugin = plugin;
    }

    @Override
    public void load() {
        mainModule = this;

        final Banner banner = new Banner(plugin);
        banner.load();

        fileManager = new FileManager(plugin);
        fileManager.load();

        storageModule = new StorageModule();
        storageModule.load();

        final CommandLoader commandLoader = new CommandLoader();
        commandLoader.load();

        eventsModule = new EventsModule(plugin);
        eventsModule.load();

        final Updater updater = new Updater(plugin);
        updater.load();

        final MetricsProvider metrics = new MetricsProvider(plugin);
        metrics.load();

    }

    @Override
    public void unload() {
        fileManager.unload();
        storageModule.unload();
        eventsModule.unload();
    }

    @Override
    public void reload() {
        fileManager.reload();
        eventsModule.reload();
        CommandTranslatorProvider.commandTranslatorProvider.reload();
    }

}
