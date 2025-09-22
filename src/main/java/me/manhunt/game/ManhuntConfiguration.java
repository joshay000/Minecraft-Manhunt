package me.manhunt.game;

import org.bukkit.Difficulty;
import org.bukkit.WorldType;

import java.util.Random;

public final class ManhuntConfiguration {
    private final WorldConfiguration worldSettings;

    public ManhuntConfiguration() {
        worldSettings = new WorldConfiguration();
    }

    public WorldConfiguration getWorldSettings() {
        return worldSettings;
    }

    public static class WorldConfiguration {
        private WorldType type;
        private boolean generateStructures;
        private long seed;
        private Difficulty difficulty;
        private boolean doWeatherCycle;
        private boolean announceAdvancements;
        private boolean doDaylightCycle;
        private boolean doMobSpawning;
        private boolean keepInventory;
        private boolean fallDamage;
        private boolean fireDamage;
        private boolean doMobGriefing;
        private long randomTickSpeed;
        private boolean locatorBar;

        private WorldConfiguration() {
            Random random = new Random();

            type = WorldType.NORMAL;
            generateStructures = true;
            seed = random.nextLong();
            difficulty = Difficulty.NORMAL;
            doWeatherCycle = true;
            announceAdvancements = true;
            doDaylightCycle = true;
            doMobSpawning = true;
            keepInventory = false;
            fallDamage = true;
            fireDamage = true;
            doMobGriefing = true;
            randomTickSpeed = 3L;
            locatorBar = false;
        }

        public void setAnnounceAdvancements(boolean announceAdvancements) {
            this.announceAdvancements = announceAdvancements;
        }

        public void setDifficulty(Difficulty difficulty) {
            this.difficulty = difficulty;
        }

        public void setDoDaylightCycle(boolean doDaylightCycle) {
            this.doDaylightCycle = doDaylightCycle;
        }

        public void setDoMobGriefing(boolean doMobGriefing) {
            this.doMobGriefing = doMobGriefing;
        }

        public void setDoMobSpawning(boolean doMobSpawning) {
            this.doMobSpawning = doMobSpawning;
        }

        public void setDoWeatherCycle(boolean doWeatherCycle) {
            this.doWeatherCycle = doWeatherCycle;
        }

        public void setFallDamage(boolean fallDamage) {
            this.fallDamage = fallDamage;
        }

        public void setFireDamage(boolean fireDamage) {
            this.fireDamage = fireDamage;
        }

        public void setGenerateStructures(boolean generateStructures) {
            this.generateStructures = generateStructures;
        }

        public void setKeepInventory(boolean keepInventory) {
            this.keepInventory = keepInventory;
        }

        public void setLocatorBar(boolean locatorBar) {
            this.locatorBar = locatorBar;
        }

        public void setRandomTickSpeed(long randomTickSpeed) {
            this.randomTickSpeed = randomTickSpeed;
        }

        public void setSeed(long seed) {
            this.seed = seed;
        }

        public void setWorldType(WorldType type) {
            this.type = type;
        }

        public boolean isAnnounceAdvancements() {
            return announceAdvancements;
        }

        public boolean isDoDaylightCycle() {
            return doDaylightCycle;
        }

        public boolean isDoMobGriefing() {
            return doMobGriefing;
        }

        public boolean isDoMobSpawning() {
            return doMobSpawning;
        }

        public boolean isDoWeatherCycle() {
            return doWeatherCycle;
        }

        public boolean isFallDamage() {
            return fallDamage;
        }

        public boolean isFireDamage() {
            return fireDamage;
        }

        public boolean hasGenerateStructures() {
            return generateStructures;
        }

        public boolean hasKeepInventory() {
            return keepInventory;
        }

        public boolean hasLocatorBar() {
            return locatorBar;
        }

        public Difficulty getDifficulty() {
            return difficulty;
        }

        public long getRandomTickSpeed() {
            return randomTickSpeed;
        }

        public long getSeed() {
            return seed;
        }

        public WorldType getWorldType() {
            return type;
        }
    }
}
