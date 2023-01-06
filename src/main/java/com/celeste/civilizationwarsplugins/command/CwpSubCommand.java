package com.celeste.civilizationwarsplugins.command;

import com.celeste.civilizationwarsplugins.CivilizationWars;
import com.celeste.civilizationwarsplugins.CivilizationWarsConfig;
import com.celeste.civilizationwarsplugins.CivilizationWarsPlugins;
import com.celeste.civilizationwarsplugins.member.Member;

/**
 * サブコマンド抽象クラス
 */
public abstract class CwpSubCommand {

    protected enum CommandType{
        USER,
        MODERATOR,
        ADMIN
    }

    protected CivilizationWarsConfig config;
    public CwpSubCommand(){
        config = CivilizationWars.getConfig();
    }

    /**
     * コマンドを取得します。
     * @return コマンド
     */
    public abstract String getCommandName();

    /**
     * パーミッションノードを取得します。
     * @return パーミッションノード
     */
    public  abstract String getPermissionNode();

    /**
     * コマンドの種別を取得します。
     * @return コマンド種別
     */
    public abstract CommandType getCommandType();

    /**
     * 使用方法に関するメッセージをsenderに送信します。
     * @param sender コマンド実行者
     * @param label 実行ラベル
     */
    public abstract void sendUsageMessage(Member sender, String label);

    /**
     * コマンドを実行します。
     * @param sender コマンド実行者
     * @param label 実行ラベル
     * @param args 実行時の引数
     * @return コマンドが実行されたかどうか
     */
    public abstract boolean runCommand(Member sender, String label, String[] args);

}
