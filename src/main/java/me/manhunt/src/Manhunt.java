package me.manhunt.src;

import me.manhunt.commands.CommandCollection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class Manhunt extends JavaPlugin {
    public static Manhunt PLUGIN;

    @Override
    public void onEnable() {
        PLUGIN = this;

        CommandCollection.clearCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        return CommandCollection.runCommand(sender, label, args);
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        return CommandCollection.getTabCompletion(sender, label, args);
    }
}
