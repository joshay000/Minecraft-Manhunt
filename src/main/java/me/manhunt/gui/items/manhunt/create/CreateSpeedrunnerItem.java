package me.manhunt.gui.items.manhunt.create;

import me.manhunt.collections.GuiCollection;
import me.manhunt.collections.ManhuntPreGameCollection;
import me.manhunt.game.ManhuntPreGame;
import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItem;
import me.manhunt.gui.enums.GuiInventoryType;
import me.manhunt.gui.factories.GuiFactory;
import me.manhunt.singletons.Messages;
import me.manhunt.src.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;

public class CreateSpeedrunnerItem extends GuiItem {
    public CreateSpeedrunnerItem(GuiInventory inventory) {
        super(inventory);
    }

    @Override
    public boolean hasClickPermissions(Player player) {
        return Permissions.MANHUNT_CREATE.hasPermission(player);
    }

    @Override
    public Material getMaterial() {
        return Material.IRON_HELMET;
    }

    @Override
    public String getName() {
        return ChatColor.GREEN + "Speedrunner";
    }

    @Override
    public List<String> getLore() {
        return List.of(
                ChatColor.GOLD + "Sets you as a speedrunner",
                ChatColor.GOLD + "for this Manhunt.",
                ChatColor.RED + "This choice cannot be undone",
                ChatColor.RED + "unless you disband the Manhunt."
        );
    }

    @Override
    public Point getInventoryLocation() {
        return new Point(3, 2);
    }

    @Override
    public void click(Player player) {
        playClick();

        if (!hasClickPermissions(player)) {
            sendNotification(Messages.INSUFFICIENT_PERMISSIONS);

            player.closeInventory();

            return;
        }

        if (ManhuntPreGameCollection.getInstance().doesPlayerHaveInvite(player)) {
            sendNotification(Messages.UNABLE_TO_CREATE_MANHUNT);

            player.closeInventory();

            return;
        }

        ManhuntPreGame game = ManhuntPreGameCollection.getInstance().start(player);

        game.addSpeedrunner(player);

        sendSettingsUpdate(Messages.CREATED_MANHUNT_AS_SPEEDRUNNER);

        GuiInventory gui = GuiCollection.getInstance().add(GuiFactory.makeGui(GuiInventoryType.MANHUNT_MAIN_INVENTORY, player));

        player.openInventory(gui.getInventory());
    }
}
