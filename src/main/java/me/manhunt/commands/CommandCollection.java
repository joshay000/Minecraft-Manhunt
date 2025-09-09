package me.manhunt.commands;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandCollection {
    private static final List<CustomCommand> INSTANCES = new ArrayList<>();

    public static void addCommand(CustomCommand command) {
        INSTANCES.add(command);
    }

    public static void clearCommands() {
        INSTANCES.clear();
    }

    public static CustomCommand getCommand(String name) {
        for (CustomCommand item : INSTANCES)
            if (item.isNameOrAlias(name))
                return item;

        return null;
    }

    public static boolean runCommand(CommandSender sender, String label, String[] args) {
        CustomCommand command = getCommand(label);

        if (command == null)
            return false;

        if (args.length == 0)
            return command.runCommand(sender, args);

        CustomSubCommand subCommand = command.getSubCommand(args[0]);

        if (subCommand == null)
            return command.runCommand(sender, args);

        for (int i = 1; i < args.length; i++) {
            CustomSubCommand temp = Objects.requireNonNull(subCommand).getSubCommand(args[i]);

            if (temp == null)
                return subCommand.runCommand(sender, args);

            subCommand = temp;
        }

        return subCommand.runCommand(sender, args);
    }

    public static List<String> getTabCompletion(CommandSender sender, String label, String[] args) {
        CustomCommand command = getCommand(label);

        if (command == null)
            return null;

        if (args.length == 0)
            return command.tabComplete(sender, args);

        CustomSubCommand subCommand = command.getSubCommand(args[0]);

        if (subCommand == null)
            return command.tabComplete(sender, args);

        for (int i = 1; i < args.length; i++) {
            CustomSubCommand temp = Objects.requireNonNull(subCommand).getSubCommand(args[i]);

            if (temp == null)
                return subCommand.tabComplete(sender, args);

            subCommand = temp;
        }

        return subCommand.tabComplete(sender, args);
    }
}
