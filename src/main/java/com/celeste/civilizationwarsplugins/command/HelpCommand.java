package com.celeste.civilizationwarsplugins.command;

import com.celeste.civilizationwarsplugins.Message;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class HelpCommand extends CwpSubCommand {
    private static final String COMMAND_NAME = "help";
    //1ページに表示するコマンドヘルプの項目数
    private static final int PAGE_ITEM_NUM = 6;
    private ArrayList<CwpSubCommand> commands;

    public HelpCommand(ArrayList<CwpSubCommand> commands) {
        this.commands = commands;
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void sendUsageMessage(Player sender, String label) {
        sender.sendMessage(Message.usageHelp(label));
    }

    @Override
    public boolean runCommand(Player sender, String label, String[] args) {
        int page = 1;

        if (args.length >= 2 && args[1].matches("[1-9]")) {
            page = Integer.parseInt(args[1]);
        }

        if (args.length >= 3 && args[2].matches("[1-9]")) {
            page = Integer.parseInt(args[2]);
        }

        printUsage(sender, label, page);
        return true;
    }

    public void printUsage(Player sender, String label, int page) {
        String typeDesc;
        ArrayList<CwpSubCommand> com = new ArrayList<CwpSubCommand>();

        //表示処理
        int lastPage = ((commands.size() - 1) / PAGE_ITEM_NUM) + 1;
        sender.sendMessage(Message.usageTop(page, lastPage));
        for (int index = (page - 1) * PAGE_ITEM_NUM; index < page * PAGE_ITEM_NUM; index++) {
            if (index >= com.size()) break;
            com.get(index).sendUsageMessage(sender, label);
        }
        sender.sendMessage(Message.usageFoot());
        if (page < lastPage) {
            sender.sendMessage(Message.usageNoticeNextPage(label, "", (page + 1)));
        }
    }
}
