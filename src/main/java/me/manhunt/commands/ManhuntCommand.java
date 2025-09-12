package me.manhunt.commands;

import me.manhunt.collections.GuiCollection;
import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.enums.GuiInventoryType;
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
        GuiInventory gui = GuiCollection.getInstance().add(GuiInventoryType.MANHUNT_INVENTORY, player);

        player.openInventory(gui.getInventory());

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
