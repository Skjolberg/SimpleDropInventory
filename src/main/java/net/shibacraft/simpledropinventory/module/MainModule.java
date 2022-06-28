package net.shibacraft.simpledropinventory.module;

import net.shibacraft.simpledropinventory.api.analytics.MetricsProvider;
import net.shibacraft.simpledropinventory.api.analytics.Updater;
import net.shibacraft.simpledropinventory.api.banner.Banner;
import net.shibacraft.simpledropinventory.commands.Internal.CommandLoader;
import net.shibacraft.simpledropinventory.files.FileManager;
import net.shibacraft.simpledropinventory.api.loader.Loader;

public class MainModule implements Loader {

    @Override
    public void load() {

        final Banner banner = new Banner();
        banner.load();

        final FileManager fileManager = new FileManager();
        fileManager.load();

        final CommandLoader commandLoader = new CommandLoader();
        commandLoader.load();

        final EventsModule eventsModule = new EventsModule();
        eventsModule.load();

        final Updater updater = new Updater();
        updater.load();

        final MetricsProvider metrics = new MetricsProvider();
        metrics.load();

    }

    @Override
    public void unload() {

    }

    @Override
    public void reload() {

    }

}
