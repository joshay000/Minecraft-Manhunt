package me.manhunt.collections;

import me.manhunt.game.ManhuntPreGame;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ManhuntPreGameCollection {
    private static final ManhuntPreGameCollection SINGLETON = new ManhuntPreGameCollection();

    public static ManhuntPreGameCollection getInstance() {
        return SINGLETON;
    }

    private final List<ManhuntPreGame> games;

    private ManhuntPreGameCollection() {
        games = new ArrayList<>();
    }

    public ManhuntPreGame start(Player leader) {
        if (isPlayerLeadingGame(leader))
            return null;

        ManhuntPreGame game = new ManhuntPreGame(leader);

        games.add(game);

        return game;
    }

    public ManhuntPreGame stop(ManhuntPreGame game) {
        int index = indexOf(game);

        if (index == -1)
            return null;

        return games.remove(index);
    }

    public void clear() {
        games.clear();
    }

    public int indexOf(ManhuntPreGame game) {
        for (int i = 0; i < games.size(); i++)
            if (games.get(i).getId().equals(game.getId()))
                return i;

        return -1;
    }

    public boolean isPlayerInGame(Player player) {
        for (ManhuntPreGame game : games)
            if (game.containsPlayer(player))
                return true;

        return false;
    }

    public boolean isPlayerLeadingGame(Player player) {
        for (ManhuntPreGame game : games)
            if (game.isPlayerLeader(player))
                return true;

        return false;
    }

    public boolean doesPlayerHaveInvite(Player player) {
        for (ManhuntPreGame game : games)
            if (game.isPlayerInvited(player))
                return true;

        return false;
    }

    public ManhuntPreGame getPlayerGame(Player player) {
        for (ManhuntPreGame game : games)
            if (game.containsPlayer(player))
                return game;

        return null;
    }

    public ManhuntPreGame getPlayerGameLeading(Player player) {
        for (ManhuntPreGame game : games)
            if (game.isPlayerLeader(player))
                return game;

        return null;
    }

    public ManhuntPreGame getPlayerGameInvited(Player player) {
        for (ManhuntPreGame game : games)
            if (game.isPlayerInvited(player))
                return game;

        return null;
    }
}
