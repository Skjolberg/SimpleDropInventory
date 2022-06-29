package net.shibacraft.simpledropinventory.api.analytics;

import de.leonhard.storage.Yaml;
import net.shibacraft.simpledropinventory.SimpleDropInventory;
import net.shibacraft.simpledropinventory.api.loader.Loader;
import net.shibacraft.simpledropinventory.files.FileManager;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;

public class MetricsProvider implements Loader {


    private final Metrics metrics = new Metrics(SimpleDropInventory.getPlugin(), 15607);
    private final Yaml config = FileManager.getFilesYaml().get("Config");

    @Override
    public void load() {
        metrics.addCustomChart(new SimplePie("PlayerJoinDrop", () -> String.valueOf(config.getBoolean("PlayerJoinDrop"))));
    }

    @Override
    public void unload() {

    }

    @Override
    public void reload() {

    }
}
