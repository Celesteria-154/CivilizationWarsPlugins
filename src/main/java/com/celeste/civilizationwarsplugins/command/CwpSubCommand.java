package com.celeste.civilizationwarsplugins.command;

import org.bukkit.entity.Player;

public abstract class CwpSubCommand {

    public abstract String getCommandName();

    public abstract void sendUsageMessage(Player sender, String label);

    public abstract boolean runCommand(Player sender, String label, String[] args);
}
