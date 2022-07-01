package net.shibacraft.simpledropinventory.listeners;

import net.shibacraft.simpledropinventory.commands.MainCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Set;
import java.util.UUID;

public class BlockBreakListener implements Listener {

    private final Set<UUID> drop = MainCommand.getDrop();

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void blockDropItem(BlockBreakEvent event) {
        String world = event.getPlayer().getWorld().getName();
        if (UtilsListener.isWorldDisabled(world)) return;

        if (!UtilsListener.isCollectExperience()) return;

        Player p = event.getPlayer();

        if (drop.contains(p.getUniqueId())) {
            int exp = event.getExpToDrop();
            event.getPlayer().giveExp(exp);
            event.setExpToDrop(0);
        }

    }

}
