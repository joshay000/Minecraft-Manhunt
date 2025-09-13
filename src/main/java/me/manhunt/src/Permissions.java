package me.manhunt.src;

import me.manhunt.singletons.Messages;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public final class Permissions {
    public static final Permissions MANHUNT = new Permissions("manhunt");
    public static final Permissions MANHUNT_CREATE = new Permissions("manhunt.create", "manhunt.create.*");

    private final List<String> perms;

    private Permissions(String permission, String...bases) {
        perms = new ArrayList<>();

        perms.add(permission);

        for (String base : bases) {
            if (perms.contains(base))
                continue;

            perms.add(base);
        }
    }

    public boolean hasPermission(Player player) {
        return hasPermission(player, true);
    }

    public boolean hasPermission(Player player, boolean opOverride) {
        if (player.hasPermission("*") || player.hasPermission("manhunt.*"))
            return true;

        if (player.isOp() && opOverride)
            return true;

        for (String item : perms)
            if (player.hasPermission(item))
                return true;

        return false;
    }

    public static boolean insufficient(Player player) {
        player.sendMessage(Messages.INSUFFICIENT_PERMISSIONS);

        return true;
    }
}
