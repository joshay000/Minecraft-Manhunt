package me.manhunt.commands;

public abstract class CustomSubCommand extends CustomCommand {
    protected CustomCommand parent;

    public CustomSubCommand(String name, String alias) {
        super(name, alias);
    }

    public CustomCommand getParent() {
        return parent;
    }
}
