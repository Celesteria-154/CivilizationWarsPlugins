package com.celeste.civilizationwarsplugins.command;

import com.celeste.civilizationwarsplugins.CivilizationWars;
import com.celeste.civilizationwarsplugins.Message;
import com.celeste.civilizationwarsplugins.CivilizationWarsConfig;
import com.celeste.civilizationwarsplugins.member.Member;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * CivilizationWarsPluginsコマンドの処理クラス
 *
 * @author Celeste
 */

public class CwpCommand {
    private ArrayList<CwpSubCommand> commands;
    private ArrayList<CwpSubCommand> commonCommands;
    private HelpCommand helpCommand;

    private CivilizationWarsConfig config;


    /**
     * コンストラクタ
     */
    public CwpCommand() {
        commands = new ArrayList<CwpSubCommand>();
        commands.add(new TestCommand());
        commands.add(new MapCommand());
        commands.add(new OppaiCommand());

        commonCommands = new ArrayList<CwpSubCommand>();

        helpCommand = new HelpCommand(commands);
        commands.add(helpCommand);
    }

    /**
     * コマンドを実行したときに呼び出されるメソッド
     *
     * @param sender 実行者
     * @param label  実行されたコマンドのラベル
     * @param args   実行されたコマンドの引数
     * @return 実行したかどうか（falseを返した場合、サーバーがUsageを表示する）
     */
    public boolean execute(Member sender, String label, String[] args) {
        if (args.length >= 1) {
            for (CwpSubCommand c : commands) {
                if (c.getCommandName().equalsIgnoreCase(args[0])) {

                    // パーミッションの確認
                    String node = c.getPermissionNode();
                    if (!sender.hasPermission(node)) {
                        sender.sendMessage(Message.errmsgPermission(node));
                        return true;
                    }

                    // 実行
                    return c.runCommand(sender, label, args);
                }
            }
        }
        if (args.length == 0) {
            helpCommand.runCommand(sender, label, args);
            return true;
        }
        return false;
    }


    /**
     * TABキー補完が実行されたときに呼び出されるメソッド
     *
     * @param sender TABキー補完の実行者
     * @param label  実行されたコマンドのラベル
     * @param args   実行されたコマンドの引数
     * @return 補完候補
     */
    public List<String> onTabComplete(Member sender, String label, String[] args) {
        if (args.length == 1) {
            // コマンド名で補完する
            String arg = args[0].toLowerCase();
            ArrayList<String> coms = new ArrayList<String>();
            for (CwpSubCommand c : commands) {
                if (c.getCommandName().startsWith(arg) &&
                        sender.hasPermission(c.getPermissionNode())) {
                    coms.add(c.getCommandName());
                }
            }
            for (CwpSubCommand c : commonCommands) {
                if (c.getCommandName().startsWith(arg) &&
                        sender.hasPermission(c.getPermissionNode())) {
                    coms.add(c.getCommandName());
                }
            }
            return coms;

            //mapテスト用
        } else if (args.length == 2 && (
                args[0].equalsIgnoreCase("map"))) {
            //map名で補完
            String arg = args[1].toLowerCase();
            ArrayList<String> items = new ArrayList<String>();
            for (String name : getListMapName()) {
                if (name.toLowerCase().startsWith(arg)) {
                    items.add(name);
                }
            }
            return items;
        }


        return new ArrayList<String>();
    }

    /**
     * Mapテスト用
     * @return マップ一覧
     */
    protected ArrayList<String> getListMapName() {
        ArrayList<String> items = new ArrayList<String>();
        for (String mapname : CivilizationWars.getConfig().getMapName()) {
            items.add(mapname);
        }
        return items;
    }

}
