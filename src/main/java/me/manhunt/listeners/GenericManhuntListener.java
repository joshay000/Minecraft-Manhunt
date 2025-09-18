package me.manhunt.listeners;

import me.manhunt.collections.GuiCollection;
import me.manhunt.collections.ManhuntPreGameCollection;
import me.manhunt.events.ManhuntGuiItemClickedEvent;
import me.manhunt.game.ManhuntPreGame;
import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItem;
import me.manhunt.gui.GuiItemBase;
import me.manhunt.singletons.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class GenericManhuntListener implements Listener {
    // TODO: Check if player is in Manhunt game when joined.
    // TODO: Return to hub world spawn if game ended.
    // TODO: Continue where left off, if not.

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        // TODO: Check if player is in Manhunt game rather than pre-game

        ManhuntPreGame game = ManhuntPreGameCollection.getInstance().getPlayerGameInvited(player);

        if (game != null) {
            game.denyInvite(player);

            game.getLeader().sendMessage(ChatColor.AQUA + player.getName() + Messages.INVITE_REJECTED);

            return;
        }

        game = ManhuntPreGameCollection.getInstance().getPlayerGame(player);

        if (game == null)
            return;

        if (game.isPlayerLeader(player)) {
            for (Player p : game.getPlayers())
                p.sendMessage(ChatColor.AQUA + player.getName() + Messages.LEADER_DISBAND_PRE_GAME);

            ManhuntPreGameCollection.getInstance().stop(game);

            return;
        }

        boolean speedrunner = game.isPlayerSpeedrunner(player);

        if (speedrunner)
            game.removeSpeedrunner(player);
        else
            game.removeHunter(player);

        game.getLeader().sendMessage(ChatColor.AQUA + player.getName() + Messages.PLAYER_LEFT_PRE_GAME);
    }

    @EventHandler
    public void onNormalInventoryClick(InventoryClickEvent event) {
        GuiCollection guis = GuiCollection.getInstance();

        if (!(event.getWhoClicked() instanceof Player player))
            return;

        if (!guis.contains(event.getView(), player))
            return;

        event.setCancelled(true);

        if (event.getClick() != ClickType.LEFT)
            return;

        GuiInventory gui = guis.get(player);

        ItemStack clicked = event.getCurrentItem();

        if (clicked == null || clicked.getType().isAir())
            return;

        String name = Objects.requireNonNull(clicked.getItemMeta()).getDisplayName();

        GuiItemBase itemClicked = null;

        for (GuiItemBase item : gui.getStoredItems()) {
            if (item.getName().equals(name)) {
                itemClicked = item;

                break;
            }
        }

        if (itemClicked == null)
            return;

        ManhuntGuiItemClickedEvent clickedEvent = new ManhuntGuiItemClickedEvent(gui, itemClicked, player);

        Bukkit.getPluginManager().callEvent(clickedEvent);
    }

    @EventHandler
    public void onManhuntInventoryClicked(ManhuntGuiItemClickedEvent event) {
        event.getClicked().click(event.getWhoClicked());
    }
}
