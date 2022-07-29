package net.shibacraft.simpledropinventory.listeners;

import lombok.Getter;
import net.shibacraft.simpledropinventory.SimpleDropInventory;
import net.shibacraft.simpledropinventory.commands.MainCommand;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class LegacyBlockBreakListener implements Listener {

    private final Set<UUID> drop = MainCommand.getDrop();

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void blockDropItem(BlockBreakEvent event) {
        String world = event.getPlayer().getWorld().getName();
        if(UtilsListener.isWorldDisabled(world)) return;

        if(!UtilsListener.isCollectDrops()) return;

        Player p = event.getPlayer();

        if (drop.contains(p.getUniqueId())) {
            Block b = event.getBlock();
            Location blockLocation = b.getLocation();
            Collection<ItemStack> items = b.getDrops();

            for (ItemStack item : items) {
                if (p.getInventory().firstEmpty() != -1) {
                    p.getInventory().addItem(item);
                } else {
                    HashMap<Integer, ItemStack> res = p.getInventory().addItem(item);
                    if (!res.isEmpty()) p.getWorld().dropItemNaturally(blockLocation, res.get(0));
                }

            }

            if (SimpleDropInventory.getVERSION() > 8) {
                event.setDropItems(false);
            } else {
                b.setType(Material.AIR);
            }

        }

    }

}
