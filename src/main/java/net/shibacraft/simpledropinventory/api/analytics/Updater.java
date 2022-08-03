package net.shibacraft.simpledropinventory.api.analytics;

import net.shibacraft.simpledropinventory.SimpleDropInventory;
import net.shibacraft.simpledropinventory.api.chat.TextColor;
import net.shibacraft.simpledropinventory.api.loader.Loader;
import net.shibacraft.simpledropinventory.api.logger.CoreLogger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Updater implements Loader, Listener {

    private final SimpleDropInventory plugin;

    public Updater(SimpleDropInventory plugin){
        this.plugin = plugin;
    }

    @Override
    public void load() {
        checkForUpdates();
    }

    @Override
    public void unload() {

    }

    @Override
    public void reload() {

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (isLatest()) return;

        Player p = e.getPlayer();
        if (p.hasPermission("sbr.admin")) {
            p.sendMessage(" ");
            p.sendMessage(TextColor.color("&cA new version of &dSimpleDropInventory &bis available!"));
            p.sendMessage(TextColor.color("&aDownload it now from:"));
            p.sendMessage(TextColor.color("&ahttps://www.spigotmc.org/resources/101522/"));
            p.sendMessage(" ");
        }
    }

    private void checkForUpdates() {
        String[] version = plugin.getDescription().getVersion().split("-");
        if (isLatest() && version[1].contains("DEV")) {
            CoreLogger.warn(" ");
            CoreLogger.warn("You are using a DEV version. Some functions may not be stable, be careful and use at your own risk.");
            CoreLogger.warn(" ");
        } else if (isLatest()) {
            CoreLogger.info(" ");
            CoreLogger.info("You are using the latest version: &e" + plugin.getDescription().getVersion());
            CoreLogger.info(" ");
        } else {
            CoreLogger.warn(" ");
            CoreLogger.warn("A new version is available at:");
            CoreLogger.warn("https://www.spigotmc.org/resources/101522/");
            CoreLogger.warn(" ");
            CoreLogger.warn("Download it to stay up to date!");
            CoreLogger.warn(" ");
        }
    }

    private String getLatestVersion() {
        URL url;
        URLConnection connection;
        BufferedReader bufferedReader;
        String latest;
        String[] res;

        try {
            url = new URL("https://api.spigotmc.org/legacy/update.php?resource=101522");
            connection = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            latest = bufferedReader.readLine();
            res = latest.split("-");
        } catch (Exception e) {
            CoreLogger.warn(" ");
            CoreLogger.warn("Couldn't check for new updates.");
            CoreLogger.warn("Current version is " + plugin.getDescription().getVersion() + ".");
            CoreLogger.warn("Check https://www.spigotmc.org/resources/101522/ to stay up to date.");
            CoreLogger.warn(" ");
            return null;
        }

        return res[0];
    }

    private boolean isLatest() {
        if (getLatestVersion() == null) return false;

        int latest = Integer.parseInt(getLatestVersion().replace(".", ""));
        String[] version = plugin.getDescription().getVersion().replace(".", "").split("-");
        int pluginVersion = Integer.parseInt(version[0]);

        return pluginVersion >= latest;
    }

}
