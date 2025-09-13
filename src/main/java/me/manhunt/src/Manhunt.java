package me.manhunt.src;

import me.manhunt.collections.GuiCollection;
import me.manhunt.collections.ManhuntPreGameCollection;
import me.manhunt.commands.CommandCollection;
import me.manhunt.commands.ManhuntCommand;
import me.manhunt.listeners.GenericManhuntListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class Manhunt extends JavaPlugin {
    // TODO:
    // Add a GUI to navigate through game creation. (Template done!)
    // Add pagination for all available players. (Template done!)
    // Add block feature (add ability to unblock).
    // Add invite feature to send to selected players where they can accept or reject.
    // Add assignability to hunter/speedrunner.
    // Allow multiple speedrunners and hunters.
    // Require minimum of 1 speedrunner.
    // Allow game to begin if there are only speedrunners; just a chill day.
    // Allow only 1 game per player to be the owner of.
    // Spawn at 0, 0 (highest Y) OR at their bed spawn point.
    // If all speedrunners die, end game (hunter[s] win).
    // If goal is acheived, end game (speedrunner[s] win).
    // Create 3 separate worlds: USERID_overworld, USERID_nether, USERID_the_end
    // Allow for world, hunter, speedrunner, game, and goal settings to be very flexible.

    public static Manhunt PLUGIN;

    @Override
    public void onEnable() {
        PLUGIN = this;

        this.getServer().getPluginManager().registerEvents(new GenericManhuntListener(), this);

        CommandCollection.clearCommands();
        CommandCollection.addCommand(new ManhuntCommand());

        this.getLogger().info("Enabled");
    }

    @Override
    public void onDisable() {
        GuiCollection.getInstance().clear();
        ManhuntPreGameCollection.getInstance().clear();

        this.getLogger().info("Disabled");
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
