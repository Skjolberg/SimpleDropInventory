package net.shibacraft.simpledropinventory.api.chat;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class TextColor {

    public @NotNull String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
