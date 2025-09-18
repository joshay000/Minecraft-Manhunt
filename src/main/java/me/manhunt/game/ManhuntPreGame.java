package me.manhunt.game;

import org.bukkit.entity.Player;

import java.util.*;

public class ManhuntPreGame {
    private final UUID id;
    private final ManhuntConfiguration configuration;
    private final List<Player> hunters;
    private final List<Player> speedrunners;
    private final Map<Player, ManhuntPlayerType> invites;
    private final Player leader;

    public ManhuntPreGame(Player leader) {
        this.leader = leader;

        id = UUID.randomUUID();
        configuration = new ManhuntConfiguration();
        hunters = new ArrayList<>();
        speedrunners = new ArrayList<>();
        invites = new HashMap<>();
    }

    public boolean addSpeedrunner(Player player) {
        if (containsPlayer(player))
            return false;

        speedrunners.add(player);

        return true;
    }

    public boolean addHunter(Player player) {
        if (containsPlayer(player))
            return false;

        hunters.add(player);

        return true;
    }

    public boolean invitePlayer(Player player, ManhuntPlayerType type) {
        if (containsPlayer(player))
            return false;

        invites.put(player, type);

        return true;
    }

    public void acceptInvite(Player player) {
        ManhuntPlayerType type = getInvitedPlayerType(player);

        if (type == ManhuntPlayerType.NULL)
            return;

        switch (type) {
            case SPEEDRUNNER -> addSpeedrunner(player);
            case HUNTER -> addHunter(player);
        }

        invites.remove(player);
    }

    public void denyInvite(Player player) {
        invites.remove(player);
    }

    public boolean removeSpeedrunner(Player player) {
        return speedrunners.remove(player);
    }

    public boolean removeHunter(Player player) {
        return hunters.remove(player);
    }

    public boolean containsSpeedrunner(Player player) {
        if (!hasSpeedrunners() || player == null)
            return false;

        for (Player p : speedrunners)
            if (p.getUniqueId().equals(player.getUniqueId()))
                return true;

        return false;
    }

    public boolean containsHunter(Player player) {
        if (!hasHunters() || player == null)
            return false;

        for (Player p : hunters)
            if (p.getUniqueId().equals(player.getUniqueId()))
                return true;

        return false;
    }

    public int indexOfSpeedrunner(Player player) {
        for (int i = 0; i < speedrunners.size(); i++)
            if (speedrunners.get(i).getUniqueId().equals(player.getUniqueId()))
                return i;

        return -1;
    }

    public int indexOfHunter(Player player) {
        for (int i = 0; i < hunters.size(); i++)
            if (hunters.get(i).getUniqueId().equals(player.getUniqueId()))
                return i;

        return -1;
    }

    public Player getSpeedrunner(int index) {
        return speedrunners.get(index);
    }

    public Player getHunter(int index) {
        return hunters.get(index);
    }

    public Set<Player> getInvitedPlayers() {
        return invites.keySet();
    }

    public ManhuntPlayerType getInvitedPlayerType(Player player) {
        return invites.get(player);
    }

    public boolean isPlayerSpeedrunner(Player player) {
        if (!hasSpeedrunners())
            return false;

        return containsSpeedrunner(player);
    }

    public boolean isPlayerHunter(Player player) {
        if (!hasHunters())
            return false;

        return containsHunter(player);
    }

    public boolean isPlayerInvited(Player player) {
        return invites.getOrDefault(player, ManhuntPlayerType.NULL) != ManhuntPlayerType.NULL;
    }

    public boolean isPlayerLeader(Player player) {
        return player.getUniqueId().equals(leader.getUniqueId());
    }

    public boolean containsPlayer(Player player) {
        return isPlayerSpeedrunner(player) || isPlayerHunter(player) || isPlayerInvited(player);
    }

    public boolean hasSpeedrunners() {
        return !speedrunners.isEmpty();
    }

    public boolean hasHunters() {
        return !hunters.isEmpty();
    }

    public UUID getId() {
        return id;
    }

    public Player getLeader() {
        return leader;
    }

    public ManhuntConfiguration getConfiguration() {
        return configuration;
    }

    public List<Player> getSpeedrunners() {
        return speedrunners;
    }

    public List<Player> getHunters() {
        return hunters;
    }

    public List<Player> getPlayers() {
        List<Player> output = new ArrayList<>();

        output.addAll(speedrunners);
        output.addAll(hunters);

        return output;
    }

    public Map<Player, ManhuntPlayerType> getInvites() {
        return invites;
    }
}
