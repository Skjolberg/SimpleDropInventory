package net.shibacraft.simpledropinventory.api.logger;

import net.shibacraft.simpledropinventory.api.chat.TextColor;
import org.bukkit.Bukkit;

public class CoreLogger {

    public static final String LOGGER_NAME = "[SimpleDropInventory]";

    public static void info(String message) {
        Bukkit.getConsoleSender().sendMessage(TextColor.color(LOGGER_NAME + "&7 | &f" + message));
    }

    public static void warn(String message) {
        Bukkit.getConsoleSender().sendMessage(TextColor.color(LOGGER_NAME + "&7 | &e" + message));
    }


    public static void severe(String message) {
        Bukkit.getConsoleSender().sendMessage(TextColor.color(LOGGER_NAME + "&7 | &c" + message));
    }

    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(TextColor.color(LOGGER_NAME + "&7 | &f" + message));
    }

}
