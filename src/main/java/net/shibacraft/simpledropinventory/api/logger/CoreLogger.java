package net.shibacraft.simpledropinventory.api.logger;

import net.shibacraft.simpledropinventory.api.chat.TextColor;
import org.bukkit.Bukkit;
import team.unnamed.error.Errors;

public class CoreLogger {

    public static final String LOGGER_NAME = "&d&lSimpleBlockRegen";

    public static void info(String message) {
        Bukkit.getLogger().info(TextColor.color(LOGGER_NAME + "&8 | &r" + message));
    }

    public static void warn(String message) {
        Bukkit.getLogger().warning(TextColor.color(LOGGER_NAME + "&8 | &e" + message));
    }

    public static void warn(Throwable throwable) {
        warn(Errors.getStackTrace(throwable));
    }

    public static void severe(String message) {
        Bukkit.getLogger().severe(TextColor.color(LOGGER_NAME + "&8 | &c" + message));
    }

    public static void severe(Throwable throwable) {
        severe(Errors.getStackTrace(throwable));
    }
}
