package net.shibacraft.simpledropinventory.listeners;

import lombok.Getter;
import net.shibacraft.simpledropinventory.module.EventsModule;
import org.bukkit.Location;
import org.bukkit.block.Block;
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
    public static final Set<UUID> drop = EventsModule.getDrop();

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void blockDropItem(BlockDropItemEvent event) {
        Player p = event.getPlayer();

        if (drop.contains(p.getUniqueId())) {
            Location blockLocation = event.getBlock().getLocation();
            List<Item> items = event.getItems();
            for (Item a : items) {
                if (p.getInventory().firstEmpty() != -1) {
                    event.getPlayer().getInventory().addItem(a.getItemStack());
                } else {
                    HashMap<Integer, ItemStack> res = p.getInventory().addItem(a.getItemStack());
                    if (!res.isEmpty()) p.getWorld().dropItemNaturally(blockLocation, res.get(0));
                }
            }
            event.setCancelled(true);
        }
    }

}
