package net.shibacraft.simpledropinventory.api.analytics;

import de.leonhard.storage.Yaml;
import net.shibacraft.simpledropinventory.SimpleDropInventory;
import net.shibacraft.simpledropinventory.api.loader.Loader;
import net.shibacraft.simpledropinventory.files.FileManager;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;

public class MetricsProvider implements Loader {

    private final Metrics metrics;

    public MetricsProvider(SimpleDropInventory plugin){
        metrics = new Metrics(plugin, 15607);
    }

    @Override
    public void load() {
        final Yaml config = FileManager.getFilesYaml().get("Config");
        metrics.addCustomChart(new SimplePie("player-join-drop", () -> String.valueOf(config.getBoolean("Player-Join-Drop"))));
        metrics.addCustomChart(new SimplePie("save-playerdata", () -> String.valueOf(config.getBoolean("Save-PlayerData"))));
        metrics.addCustomChart(new SimplePie("collect-drops", () -> String.valueOf(config.getBoolean("Collect-Drops"))));
        metrics.addCustomChart(new SimplePie("collect-experience", () -> String.valueOf(config.getBoolean("Collect-Experience"))));
    }

    @Override
    public void unload() {

    }

    @Override
    public void reload() {

    }
}
