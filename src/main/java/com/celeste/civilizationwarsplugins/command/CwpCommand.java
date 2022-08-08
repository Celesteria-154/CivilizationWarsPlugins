package com.celeste.civilizationwarsplugins.command;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CwpCommand {
    private ArrayList<CwpSubCommand> commands;
    private ArrayList<CwpSubCommand> commonCommands;
    private HelpCommand helpCommand;

    public CwpCommand() {
        commands = new ArrayList<CwpSubCommand>();
        commands.add(new TestCommand());

        commonCommands = new ArrayList<CwpSubCommand>();

        helpCommand = new HelpCommand(commands);
        commands.add(helpCommand);
    }

    public boolean execute(Player sender, String label, String[] args) {
        if (args.length >= 1) {
            for (CwpSubCommand c : commonCommands) {
                if (c.getCommandName().equalsIgnoreCase(args[0])) {
                    return c.runCommand(sender, label, args);
                }
            }
        }

        if (args.length == 0) {
            helpCommand.runCommand(sender, label, args);
            return true;
        }

        //第一引数に指定されたコマンド実行
        for (CwpSubCommand c : commands) {
            if (c.getCommandName().equalsIgnoreCase(args[0])) {
                // 実行
                return c.runCommand(sender, label, args);
            }
        }
        return false;
    }
}
