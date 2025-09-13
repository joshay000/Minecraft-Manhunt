package me.manhunt.gui;

import me.manhunt.gui.enums.GuiItemType;
import me.manhunt.gui.factories.GuiFactory;
import me.manhunt.singletons.Messages;
import me.manhunt.singletons.SoundPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public abstract class GuiInventory implements GuiInventoryBase, GuiInventoryPaginationBase {
    protected static final int MAX_INV_X = 9;
    protected static final int MAX_INV_Y = 4;

    protected final Player owner;
    protected final List<GuiItemBase> items;

    protected int currentPage;

    public GuiInventory(Player owner) {
        this(owner, 0);
    }

    public GuiInventory(Player owner, int currentPage) {
        this.owner = owner;
        this.currentPage = currentPage;
        this.items = new ArrayList<>();
    }

    protected void createPagination() {
        items.add(GuiFactory.makeItem(GuiItemType.PAGINATION_CURRENT_PAGE, this, currentPage + 1, getTotalPages()));

        if (hasPreviousPage()) {
            items.add(GuiFactory.makeItem(GuiItemType.PAGINATION_FIRST_PAGE, this));
            items.add(GuiFactory.makeItem(GuiItemType.PAGINATION_PREVIOUS_PAGE, this));
        }

        if (hasNextPage()) {
            items.add(GuiFactory.makeItem(GuiItemType.PAGINATION_NEXT_PAGE, this));
            items.add(GuiFactory.makeItem(GuiItemType.PAGINATION_LAST_PAGE, this));
        }
    }

    public boolean hasNextPage() {
        return currentPage < getTotalPages() - 1;
    }

    public boolean hasPreviousPage() {
        return currentPage > 0;
    }

    @Override
    public void refresh(boolean hasPermission) {
        if (!hasPermission) {
            sendNotification(Messages.INSUFFICIENT_PERMISSIONS);

            return;
        }

        owner.openInventory(getInventory());
    }

    @Override
    public void firstPage(boolean hasPermission) {
        currentPage = 0;

        refresh(hasPermission);
    }

    @Override
    public void lastPage(boolean hasPermission) {
        currentPage = getTotalPages() - 1;

        refresh(hasPermission);
    }

    @Override
    public void nextPage(boolean hasPermission) {
        currentPage++;

        if (currentPage >= getTotalPages()) {
            currentPage = getTotalPages() - 1;

            return;
        }

        refresh(hasPermission);
    }

    @Override
    public void previousPage(boolean hasPermission) {
        currentPage--;

        if (currentPage < 0) {
            currentPage = 0;

            return;
        }

        refresh(hasPermission);
    }

    @Override
    public Inventory getInventory() {
        items.clear();

        List<GuiItemBase> preMadeItems = this.getItems();

        if (preMadeItems != null && !preMadeItems.isEmpty())
            items.addAll(preMadeItems);

        final int rows = getRows();
        final int current = currentPage * MAX_INV_Y;

        if (getTotalPages() > 1)
            createPagination();

        Inventory inventory = Bukkit.createInventory(owner, rows * MAX_INV_X, getTitle());

        for (GuiItemBase item : items) {
            Point slot = item.getInventoryLocation();

            if (slot.x >= MAX_INV_X || slot.x < 0)
                continue;

            if (item instanceof GuiPageable) {
                if (slot.y >= current + MAX_INV_Y || slot.y < current)
                    continue;

                int index = slot.x + (slot.y % MAX_INV_Y) * MAX_INV_X;

                inventory.setItem(index, item.getItemStack());

                continue;
            }

            if (slot.y > rows || slot.y < 0)
                continue;

            int index = slot.x + slot.y * MAX_INV_X;

            inventory.setItem(index, item.getItemStack());
        }

        return inventory;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public int getTotalPages() {
        Optional<Integer> max = items.stream().filter(x -> x instanceof GuiPageable).map(x -> x.getInventoryLocation().y).max(Comparator.comparingInt(a -> a));

        return max.map(x -> x / MAX_INV_Y).orElse(0) + 1;
    }

    public List<GuiItemBase> getStoredItems() {
        return items;
    }

    public Player getOwner() {
        return owner;
    }

    protected void sendNotification(String message) {
        sendNotification(owner, message);
    }

    protected void sendNotification(Player player, String message) {
        player.sendMessage(ChatColor.RED + message);

        SoundPlayer.getInstance().playNotification(player);
    }

    protected void sendSettingsUpdate(String message) {
        sendSettingsUpdate(owner, message);
    }

    protected void sendSettingsUpdate(Player player, String message) {
        player.sendMessage(ChatColor.RED + message);

        SoundPlayer.getInstance().playSettingsUpdate(player);
    }
}
