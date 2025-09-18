package me.manhunt.singletons;

import org.bukkit.ChatColor;

public final class Messages {
    public static final String INSUFFICIENT_PERMISSIONS = ChatColor.RED + "You do not have sufficient permissions.";
    public static final String CONSOLE_RUN_COMMAND_PLAYER_ONLY = ChatColor.RED + "You must be an in-game player to run this command.";
    public static final String UNABLE_TO_CREATE_MANHUNT = ChatColor.RED + "You currently have an invitation to a different Manhunt and cannot proceed until you accept or reject the invitation.";
    public static final String CREATED_MANHUNT_AS_HUNTER = ChatColor.GOLD + "You created a new Manhunt and are set as a " + ChatColor.RED + "HUNTER" + ChatColor.GOLD + ".";
    public static final String CREATED_MANHUNT_AS_SPEEDRUNNER = ChatColor.GOLD + "You created a new Manhunt and are set as a " + ChatColor.GREEN + "SPEEDRUNNER" + ChatColor.GOLD + ".";
    public static final String INVITE_REJECTED = ChatColor.GOLD + " has rejected your Manhunt invitation.";
    public static final String INVITE_ACCEPTED = ChatColor.GOLD + " has accepted your Manhunt invitation.";
    public static final String LEADER_DISBAND_PRE_GAME = ChatColor.RED + " has disbanded the Manhunt.";
    public static final String PLAYER_LEFT_PRE_GAME = ChatColor.RED + " has left the Manhunt.";
    public static final String PLAYER_NOT_LEADER = ChatColor.RED + "You cannot perform this action as you are not the Manhunt leader.";
}
