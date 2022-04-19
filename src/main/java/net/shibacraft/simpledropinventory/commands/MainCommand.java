package net.shibacraft.simpledropinventory.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.Required;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import net.shibacraft.simpledropinventory.SimpleDropInventory;
import net.shibacraft.simpledropinventory.commands.Internal.CommandTranslatorProvider;
import net.shibacraft.simpledropinventory.files.FileManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(names = {"sbr", "simpleblockregen"}, permission = "sbr.use", desc = "SimpleBlockRegen main command")
@Required
public class MainCommand implements CommandClass {

    private final FileManager fileManager;

    public MainCommand(SimpleDropInventory plugin) {
        fileManager = new FileManager(plugin);
    }

    @Command(names = "")
    public void onMainCommand(@Sender CommandSender sender) {
        sender.sendMessage("It needs arguments.");
    }

    @Command(names = "reload", permission = "sbr.reload")
    public void onReloadCommand(@Sender CommandSender sender) {

        fileManager.reload();
        CommandTranslatorProvider.commandTranslatorProvider.reload();


    }

    @Command(names = "bypass", permission = "sbr.bypass")
    public void onBypassCommand(@Sender Player player) {



    }
}
