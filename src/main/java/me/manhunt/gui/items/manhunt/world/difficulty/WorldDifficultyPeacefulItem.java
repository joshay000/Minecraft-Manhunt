package me.manhunt.gui.items.manhunt.world.difficulty;

import me.manhunt.collections.GuiCollection;
import me.manhunt.collections.ManhuntPreGameCollection;
import me.manhunt.game.ManhuntConfiguration;
import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItem;
import me.manhunt.gui.enums.GuiInventoryType;
import me.manhunt.singletons.Messages;
import me.manhunt.src.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;

public class WorldDifficultyPeacefulItem extends GuiItem {
    private final ManhuntConfiguration game;

    public WorldDifficultyPeacefulItem(GuiInventory inventory) {
        super(inventory);

        game = ManhuntPreGameCollection.getInstance().getPlayerGameLeading(inventory.getOwner()).getConfiguration();
    }

    @Override
    public boolean hasClickPermissions(Player player) {
        return Permissions.MANHUNT.hasPermission(player);
    }

    @Override
    public Material getMaterial() {
        return Material.WOODEN_SWORD;
    }

    @Override
    public String getName() {
        return ChatColor.GREEN + "Peaceful";
    }

    @Override
    public List<String> getLore() {
        return List.of(
                ChatColor.GOLD + "Presents a peaceful world to",
                ChatColor.GOLD + "play Manhunt in.",
                ChatColor.RED + "WARNING: No blazes nor ender dragon",
                ChatColor.RED + "will be able to spawn.",
                ChatColor.RED + "Update the end goal settings."
        );
    }

    @Override
    public Point getInventoryLocation() {
        return new Point(3, 1);
    }

    @Override
    public void click(Player player) {
        playClick();

        if (!hasClickPermissions(player)) {
            sendNotification(Messages.INSUFFICIENT_PERMISSIONS);

            return;
        }

        sendSettingsUpdate(Messages.WORLD_SETTINGS_UPDATED);

        game.getWorldSettings().setDifficulty(Difficulty.PEACEFUL);

        GuiInventory gui = GuiCollection.getInstance().add(GuiInventoryType.MANHUNT_WORLD_SETTINGS_INVENTORY, player);

        player.openInventory(gui.getInventory());
    }
}
