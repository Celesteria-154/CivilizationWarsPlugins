package com.celeste.civilizationwarsplugins.command;

import com.celeste.civilizationwarsplugins.Message;
import com.celeste.civilizationwarsplugins.member.Member;

import java.util.ArrayList;

/**
 * helpコマンドクラス
 *
 * 未動作
 * Caused by: java.lang.NullPointerException: Cannot invoke "com.celeste.civilizationwarsplugins.util.YamlConfig.getString(String)" because "com.celeste.civilizationwarsplugins.Message.resources" is null
 */
public class HelpCommand extends CwpSubCommand {
    private static final String COMMAND_NAME = "help";

    private static final String PERMISSION_NODE = "cwp." + COMMAND_NAME;
    //1ページに表示するコマンドヘルプの項目数
    private static final int PAGE_ITEM_NUM = 6;
    private ArrayList<CwpSubCommand> commands;

    /**
     * コンストラクタ
     * @param commands
     */
    public HelpCommand(ArrayList<CwpSubCommand> commands) {
        this.commands = commands;
    }

    /**
     * コマンドを習得
     * @return コマンド
     */
    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    /**
     * パーミッションノードを取得
     * @return パーミッションノード
     */
    @Override
    public String getPermissionNode() {
        return PERMISSION_NODE;
    }

    /**
     * コマンドの種別を取得
     * @return コマンド種別
     */
    @Override
    public CommandType getCommandType() {
        return CommandType.USER;
    }

    /**
     * 使用方法に関するメッセージをsenderに送信します。
     * @param sender コマンド実行者
     * @param label 実行ラベル
     */
    @Override
    public void sendUsageMessage(Member sender, String label) {
        sender.sendMessage(Message.usageHelp(label));
    }

    /**
     * @param sender コマンド実行者
     * @param label 実行レベル
     * @param args コマンド引数
     * @return コマンドが実行されたか
     */
    @Override
    public boolean runCommand(Member sender, String label, String[] args) {
        CommandType type = CommandType.USER;
        int page = 1;

        if ( args.length >= 2 &&
                (args[1].equalsIgnoreCase("mod")
                        || args[1].equalsIgnoreCase("moderator") ) ) {
            type = CommandType.MODERATOR;
        } else if ( args.length >= 2 && args[1].equalsIgnoreCase("admin") ) {
            type = CommandType.ADMIN;
        } else if ( args.length >= 2 && args[1].matches("[1-9]") ) {
            page = Integer.parseInt(args[1]);
        }
        if ( args.length >= 3 && args[2].matches("[1-9]") ) {
            page = Integer.parseInt(args[2]);
        }
        printUsage(sender, label, type, page);

        return true;
    }
    /**
     *
     * @param sender コマンド実行者
     * @param label 実行ラベル
     * @param type コマンド種別
     * @param page ページ
     */
    public void printUsage(Member sender, String label, CommandType type, int page) {
        String typeDesc;
        switch (type) {
            case MODERATOR:
                typeDesc = "moderator";
                break;
            case ADMIN:
                typeDesc = "admin";
                break;
            case USER:
            default:
                typeDesc = "user";
        }

        // 種別に該当するコマンドを取得
        ArrayList<CwpSubCommand> com = new ArrayList<CwpSubCommand>();
        for ( CwpSubCommand c : commands ) {
            if ( c.getCommandType() == type
                    && sender.hasPermission(c.getPermissionNode()) ) {
                com.add(c);
            }
        }

        int lastPage = ( (com.size() - 1) / PAGE_ITEM_NUM) + 1;
        // 表示処理
        sender.sendMessage(Message.usageTop(typeDesc, page, lastPage));
        for (int index=(page-1)*PAGE_ITEM_NUM; index<page*PAGE_ITEM_NUM; index++) {
            if ( index >= com.size() ) break;
            com.get(index).sendUsageMessage(sender, label);
        }
        sender.sendMessage(Message.usageFoot());
        if ( page < lastPage ) {
            if ( type != CommandType.USER ) {
                sender.sendMessage(Message.usageNoticeNextPage(label, typeDesc, (page + 1)));
            } else {
                sender.sendMessage(Message.usageNoticeNextPage(label, "", (page + 1)));
            }
        }
    }
}
