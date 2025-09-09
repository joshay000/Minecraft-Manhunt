package me.manhunt.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomCommand {
    protected final String name;
    protected final String alias;
    protected final List<CustomSubCommand> subCommands;

    public CustomCommand(String name, String alias) {
        this.name = name;
        this.alias = alias;

        subCommands = new ArrayList<>();
    }

    public boolean runCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player player)
            return runPlayerCommand(player, args);

        if (sender instanceof ConsoleCommandSender console)
            return runConsoleCommand(console, args);

        return false;
    }

    protected abstract boolean runPlayerCommand(Player player, String[] args);
    protected abstract boolean runConsoleCommand(ConsoleCommandSender console, String[] args);

    public abstract List<String> tabComplete(CommandSender sender, String[] args);

    public void addSubCommand(CustomSubCommand command) {
        if (command.parent != null)
            return;

        command.parent = this;

        subCommands.add(command);
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public boolean hasAlias() {
        return alias != null;
    }

    public boolean isNameOrAlias(String label) {
        if (hasAlias())
            return name.equalsIgnoreCase(label) || alias.equalsIgnoreCase(label);

        return name.equalsIgnoreCase(label);
    }

    public CustomSubCommand getSubCommand(String name) {
        for (CustomSubCommand item : subCommands)
            if (item.isNameOrAlias(name))
                return item;

        return null;
    }

    public CustomSubCommand[] getSubCommands() {
        return subCommands.toArray(CustomSubCommand[]::new);
    }

    protected boolean sendMessageWithIssue(Player player, String message) {
        player.sendMessage(ChatColor.RED + message);

        return true;
    }
}
