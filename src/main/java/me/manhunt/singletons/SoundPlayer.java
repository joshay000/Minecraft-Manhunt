package me.manhunt.singletons;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class SoundPlayer {
    private static final SoundPlayer SINGLETON = new SoundPlayer();

    public static SoundPlayer getInstance() {
        return SINGLETON;
    }

    public void playNotification(List<Player> players) {
        playNotification(listToArray(players));
    }

    public void playNotification(Player...players) {
        play(Sound.ENTITY_ARROW_HIT_PLAYER, 1.0f, 1.1f, players);
    }

    public void playGUIClick(List<Player> players) {
        playGUIClick(listToArray(players));
    }

    public void playGUIClick(Player...players) {
        play(Sound.UI_BUTTON_CLICK, 1.0f, 1.0f, players);
    }

    public void playSettingsUpdates(List<Player> players) {
        playSettingsUpdates(listToArray(players));
    }

    public void playSettingsUpdates(Player...players) {
        play(Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f, players);
    }

    private void play(Sound sound, float volume, float pitch, Player...players) {
        for (Player player : players)
            player.playSound(player.getLocation(), sound, volume, pitch);
    }

    private Player[] listToArray(List<Player> players) {
        return players.toArray(Player[]::new);
    }
}
