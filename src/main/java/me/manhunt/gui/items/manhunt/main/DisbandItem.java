package me.manhunt.gui.items.manhunt.main;

import me.manhunt.collections.ManhuntPreGameCollection;
import me.manhunt.game.ManhuntPreGame;
import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItem;
import me.manhunt.singletons.Messages;
import me.manhunt.src.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;

public class DisbandItem extends GuiItem {
    public DisbandItem(GuiInventory inventory) {
        super(inventory);
    }

    @Override
    public boolean hasClickPermissions(Player player) {
        return Permissions.MANHUNT.hasPermission(player);
    }

    @Override
    public Material getMaterial() {
        return Material.DARK_OAK_DOOR;
    }

    @Override
    public String getName() {
        return ChatColor.DARK_RED + "Disband";
    }

    @Override
    public List<String> getLore() {
        return List.of(
                ChatColor.GOLD + "Disband the current Manhunt."
        );
    }

    @Override
    public Point getInventoryLocation() {
        return new Point(7, 2);
    }

    @Override
    public void click(Player player) {
        playClick();

        if (!hasClickPermissions(player)) {
            sendNotification(Messages.INSUFFICIENT_PERMISSIONS);

            return;
        }

        ManhuntPreGame game = ManhuntPreGameCollection.getInstance().getPlayerGameLeading(player);

        if (game == null) {
            sendNotification(Messages.PLAYER_NOT_LEADER);

            return;
        }

        sendSettingsUpdate(ChatColor.RED + "You disbanded the Manhunt.");

        for (Player p : game.getPlayers())
            if (!p.getUniqueId().equals(player.getUniqueId()))
                p.sendMessage(ChatColor.AQUA + player.getName() + Messages.LEADER_DISBAND_PRE_GAME);

        ManhuntPreGameCollection.getInstance().stop(game);

        player.closeInventory();
    }
}
