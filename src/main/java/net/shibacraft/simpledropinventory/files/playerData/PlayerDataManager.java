package net.shibacraft.simpledropinventory.files.playerData;

import de.leonhard.storage.Json;
import de.leonhard.storage.internal.settings.ReloadSettings;
import net.shibacraft.simpledropinventory.SimpleDropInventory;
import net.shibacraft.simpledropinventory.api.loader.Loader;
import net.shibacraft.simpledropinventory.commands.MainCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.util.Set;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class PlayerDataManager implements Listener, Loader {

    private final SimpleDropInventory plugin;
    private Json json;
    private final Set<UUID> drop;

    public PlayerDataManager(){
        plugin = SimpleDropInventory.getPlugin();
        drop = MainCommand.getDrop();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void load() {
        getServer().getScheduler().runTaskAsynchronously(plugin, this::jsonToCache);
    }

    @Override
    public void unload() {
        cacheToJson();
    }

    @Override
    public void reload() {

    }

    public void createPlayer(Player p) {
        json = new Json(p.getUniqueId() + ".json", plugin.getDataFolder().getPath() + "/playerdata", null,
                ReloadSettings.INTELLIGENT, null);

        json.setDefault("NAME", p.getName());
        json.setDefault("UUID", p.getUniqueId());
        json.setDefault("DROP_TO_INVENTORY", false);

    }

    public boolean isDropToInventory(Player p) {
        json = new Json(p.getUniqueId() + ".json", plugin.getDataFolder().getPath() + "/playerdata");
        return json.getBoolean("DROP_TO_INVENTORY");
    }

    public void cacheToJson(){
        for(Player p : plugin.getServer().getOnlinePlayers()) {
            UUID uuid = p.getUniqueId();
            json = new Json(uuid + ".json", plugin.getDataFolder().getPath() + "/playerdata");
            if(drop.contains(uuid)){
                json.set("DROP_TO_INVENTORY", true);
            } else {
                json.set("DROP_TO_INVENTORY", false);
            }
        }
    }

    public void jsonToCache(){
        File playerData = new File(plugin.getDataFolder().getPath() + "/playerdata");
        File[] files = playerData.listFiles();

        if (files != null) {
            for (File file : files) {
                json = new Json(file.getName(), plugin.getDataFolder().getPath() + "/playerdata");
                boolean isDrop = json.getBoolean("DROP_TO_INVENTORY");
                if(isDrop){
                    String uuid = file.getName().split(".json")[0];
                    drop.add(UUID.fromString(uuid));
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        if (p.hasPermission("sdi.use")) {
            createPlayer(p);
            if(isDropToInventory(p)) drop.add(p.getUniqueId());
        }

    }


}
