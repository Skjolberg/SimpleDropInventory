package net.shibacraft.simpledropinventory.module;

import lombok.Getter;
import net.shibacraft.simpledropinventory.api.analytics.MetricsProvider;
import net.shibacraft.simpledropinventory.api.analytics.Updater;
import net.shibacraft.simpledropinventory.api.banner.Banner;
import net.shibacraft.simpledropinventory.commands.Internal.CommandLoader;
import net.shibacraft.simpledropinventory.files.FileManager;
import net.shibacraft.simpledropinventory.api.loader.Loader;

public class MainModule implements Loader {

    @Getter
    private static MainModule mainModule;
    private StorageModule storageModule;
    private FileManager fileManager;
    private EventsModule eventsModule;

    @Override
    public void load() {
        mainModule = this;

        final Banner banner = new Banner();
        banner.load();

        fileManager = new FileManager();
        fileManager.load();

        storageModule = new StorageModule();
        storageModule.load();

        final CommandLoader commandLoader = new CommandLoader();
        commandLoader.load();

        eventsModule = new EventsModule();
        eventsModule.load();

        final Updater updater = new Updater();
        updater.load();

        final MetricsProvider metrics = new MetricsProvider();
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
    }

}
