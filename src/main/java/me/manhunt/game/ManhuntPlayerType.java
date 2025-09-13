package me.manhunt.game;

public enum ManhuntPlayerType {
    HUNTER("Speedrunner"),
    SPEEDRUNNER("Hunter"),
    NULL("NULL");

    private final String name;

    ManhuntPlayerType(String name) {
        this.name = name;
    }

    public String getOfficialName() {
        return name;
    }
}
