package com.celeste.civilizationwarsplugins.command;

import com.celeste.civilizationwarsplugins.Message;
import com.celeste.civilizationwarsplugins.member.Member;
import org.bukkit.entity.Player;

/**
 * まだ動かない、ぬるぽ
 */
public class MapCommand extends CwpSubCommand{
    private static final String COMMAND_NAME = "map";
    private static final String PERMISSION_NODE = "cwp." + COMMAND_NAME;

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
    public CwpSubCommand.CommandType getCommandType() {
        return CwpSubCommand.CommandType.USER;
    }

    /**
     * @param sender コマンド実行者
     * @param label 実行ラベル
     */

    @Override
    public void sendUsageMessage(Member sender, String label) {
        sender.sendMessage(Message.usageTest(label));
    }

    /**
     * テスト用
     * @param sender コマンド実行者
     * @param label 実行レベル
     * @param args コマンド引数
     * @return コマンドが実行されたか
     */
    @Override
    public boolean runCommand(Member sender, String label, String[] args) {
        Member Sender = sender;
        Sender.sendMessage("Successful command execution!");
        return true;
    }
}
