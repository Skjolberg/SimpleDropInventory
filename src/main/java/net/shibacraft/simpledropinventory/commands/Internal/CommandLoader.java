package net.shibacraft.simpledropinventory.commands.Internal;

import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.SimplePartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import me.fixeddev.commandflow.translator.DefaultTranslator;
import net.shibacraft.simpledropinventory.SimpleDropInventory;
import net.shibacraft.simpledropinventory.commands.MainCommand;
import net.shibacraft.simpledropinventory.api.loader.Loader;

public class CommandLoader implements Loader {

    private final CommandManager commandManager;

    public CommandLoader() {
        this.commandManager = new BukkitCommandManager(SimpleDropInventory.getPlugin().getName());
        commandManager.setTranslator(new DefaultTranslator(new CommandTranslatorProvider()));
    }

    @Override
    public void load() {
        PartInjector partInjector = new SimplePartInjector();
        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());

        AnnotatedCommandTreeBuilder treeBuilder = new AnnotatedCommandTreeBuilderImpl(partInjector);

        commandManager.registerCommands(treeBuilder.fromClass(new MainCommand()));


    }

    @Override
    public void unload() {
        commandManager.unregisterAll();
    }

    @Override
    public void reload() {

    }

}
