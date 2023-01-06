package com.celeste.civilizationwarsplugins.member;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class MemberBukkitConsole extends MemberBukkit {

    ConsoleCommandSender sender;

    /**
     * コンストラクタ
     * @param sender コンソール
     */
    public MemberBukkitConsole(ConsoleCommandSender sender) {
        this.sender = sender;
    }

    /**
     * オンラインかどうか
     * @return 常にtrue
     */
    @Override
    public boolean isOnline() {
        return true;
    }

    /**
     * プレイヤー名を返す
     * @return プレイヤー名
     */
    @Override
    public String getName() {
        return sender.getName();
    }

    /**
     * プレイヤー表示名を返す
     * @return プレイヤー表示名
     */
    @Override
    public String getDisplayName() {
        return sender.getName();
    }

    /**
     * プレフィックスを返す
     * @return 常に空文字列
     */
    @Override
    public String getPrefix() {
        return "";
    }

    /**
     * サフィックスを返す
     * @return 常に空文字列
     */
    @Override
    public String getSuffix() {
        return "";
    }

    /**
     * メッセージを送る
     * @param message 送信するメッセージ
     */
    @Override
    public void sendMessage(String message) {
        if ( message == null || message.isEmpty() ) return;
        sender.sendMessage(message);
    }

    /**
     * メッセージを送る
     * @param message 送るメッセージ
     */
    public void sendMessage(BaseComponent[] message) {
        if ( message == null || message.length == 0 ) return;
        sender.spigot().sendMessage(message);
    }

    /**
     * BukkitのPlayerを取得する
     * @return 常にnullが返される
     */
    @Override
    public Player getPlayer() {
        return null;
    }

    /**
     * 発言者が今いるワールドのワールド名を取得する
     * @return 常に "-" が返される。
     */
    @Override
    public String getWorldName() {
        return "-";
    }

    /**
     * 発言者が今いる位置を取得する
     * @return 常にnullが返される
     */
    @Override
    public Location getLocation() {
        return null;
    }

    /**
     * 指定されたパーミッションノードの権限を持っているかどうかを取得する
     * @param node パーミッションノード
     * @return 権限を持っているかどうか
     */
    @Override
    public boolean hasPermission(String node) {
        return sender.hasPermission(node);
    }

    /**
     * 指定されたパーミッションノードが定義されているかどうかを取得する
     * @param node パーミッションノード
     * @return 定義を持っているかどうか
     */
    @Override
    public boolean isPermissionSet(String node) {
        return sender.isPermissionSet(node);
    }

    /**
     * 指定されたメッセージの内容を発言する
     * @param message メッセージ
     */
    public void chat(String message) {
        Bukkit.broadcastMessage("<" + getName() + ">" + message);
    }

    /**
     * IDを返す
     * @return 名前をそのまま返す
     */
    @Override
    public String toString() {
        return getName();
    }

    @Override
    public World getWorld() {
        return null;
    }
}
