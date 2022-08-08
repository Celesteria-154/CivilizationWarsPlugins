package com.celeste.civilizationwarsplugins.command;

import com.celeste.civilizationwarsplugins.Message;
import org.bukkit.entity.Player;

public class TestCommand extends CwpSubCommand {
    private static final String COMMAND_NAME = "test";

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void sendUsageMessage(Player sender, String label) {
        sender.sendMessage(Message.usageTest(label));
    }

    @Override
    public boolean runCommand(Player sender, String label, String[] args) {
        Player Sender = sender;
        Sender.sendMessage("testCommand");
        return false;
    }

}
