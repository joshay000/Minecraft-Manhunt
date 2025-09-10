package me.manhunt.events;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItemBase;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ManhuntGuiItemClickedEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final GuiInventory gui;
    private final GuiItemBase item;
    private final Player player;

    public ManhuntGuiItemClickedEvent(GuiInventory gui, GuiItemBase item, Player player) {
        this.gui = gui;
        this.item = item;
        this.player = player;
    }

    public GuiItemBase getClicked() {
        return item;
    }

    public GuiInventory getInventory() {
        return gui;
    }

    public Player getWhoClicked() {
        return player;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
