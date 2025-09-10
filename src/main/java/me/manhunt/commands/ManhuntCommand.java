package me.manhunt.commands;

import me.manhunt.singletons.Messages;
import me.manhunt.singletons.TextMaker;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ManhuntCommand extends CustomCommand {
    public ManhuntCommand() {
        super("manhunt", "mh");
    }

    @Override
    protected boolean runPlayerCommand(Player player, String[] args) {
        player.sendMessage("Works");

        return false;
    }

    @Override
    protected boolean runConsoleCommand(ConsoleCommandSender console, String[] args) {
        console.sendMessage(Messages.CONSOLE_RUN_COMMAND_PLAYER_ONLY);

        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
