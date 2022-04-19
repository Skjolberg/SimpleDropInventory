package net.shibacraft.simpledropinventory.api.chat;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public class TextColor {

    public static @NotNull String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
