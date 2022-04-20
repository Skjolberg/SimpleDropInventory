package net.shibacraft.simpledropinventory.listeners;

import de.leonhard.storage.Yaml;
import lombok.Getter;
import net.shibacraft.simpledropinventory.files.FileManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class BlockDropItemListener implements Listener {

    @Getter
    public static final Set<UUID> drop = new HashSet<>();

    @EventHandler(priority = EventPriority.LOWEST)
    public void blockDropItem(BlockDropItemEvent event) {

        Player p = event.getPlayer();
        Location blockLocation = event.getBlock().getLocation();
        List<Item> items = event.getItems();

        if(drop.contains(p.getUniqueId())){
            for (Item item : items) {
                dropToInventory(item.getItemStack(), p, blockLocation);
            }
            event.setCancelled(true);
        }

    }

    void dropToInventory(ItemStack item, Player player, Location blockLocation) {
        ItemStack[] items = new ItemStack[1];
        items[0] = item;

        for (ItemStack drop : items) {
            if (item != null && drop.getType() != Material.AIR) {
                HashMap<Integer, ItemStack> res = player.getInventory().addItem(item);
                for (ItemStack r : res.values()) {
                    if (r.getType() == Material.AIR) continue;
                    player.getWorld().dropItemNaturally(blockLocation, r);
                }
            }
        }

    }
}
