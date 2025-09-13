package me.manhunt.game;

public class ManhuntConfiguration {
    private int worldBorderDiameter;

    public ManhuntConfiguration() {
        worldBorderDiameter = 10000;
    }

    public ManhuntConfiguration setWorldBorderDiameter(int worldBorderDiameter) {
        this.worldBorderDiameter = worldBorderDiameter;

        return this;
    }
}
