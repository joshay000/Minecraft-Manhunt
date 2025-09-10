package me.manhunt.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class GenericManhuntListener implements Listener {
    @EventHandler
    public void onPunch(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        event.getPlayer().sendMessage("Boop");
    }
}
