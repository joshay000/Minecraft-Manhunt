package me.manhunt.listeners;

import me.manhunt.collections.GuiCollection;
import me.manhunt.events.ManhuntGuiItemClickedEvent;
import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItem;
import me.manhunt.gui.GuiItemBase;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class GenericManhuntListener implements Listener {
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
