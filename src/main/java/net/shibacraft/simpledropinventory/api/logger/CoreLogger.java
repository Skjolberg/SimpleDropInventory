package net.shibacraft.simpledropinventory.api.logger;

import lombok.experimental.UtilityClass;
import net.shibacraft.simpledropinventory.api.chat.TextColor;
import org.bukkit.Bukkit;

@UtilityClass
public class CoreLogger {

    public final String LOGGER_NAME = "[SimpleDropInventory]";

    public void info(String message) {
        Bukkit.getConsoleSender().sendMessage(TextColor.color(LOGGER_NAME + "&7 | &f" + message));
    }

    public void warn(String message) {
        Bukkit.getConsoleSender().sendMessage(TextColor.color(LOGGER_NAME + "&7 | &e" + message));
    }

    public void severe(String message) {
        Bukkit.getConsoleSender().sendMessage(TextColor.color(LOGGER_NAME + "&7 | &c" + message));
    }

}
