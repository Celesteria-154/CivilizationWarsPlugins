package com.celeste.civilizationwarsplugins.member;

import com.celeste.civilizationwarsplugins.CivilizationWars;
import net.md_5.bungee.api.chat.BaseComponent;

public abstract class Member implements Comparable<Member> {

    /**
     * オンラインかどうか
     * @return オンラインかどうか
     */
    public abstract boolean isOnline();

    /**
     * プレイヤー名を返す
     * @return プレイヤー名
     */
    public abstract String getName();

    /**
     * プレイヤー表示名を返す
     * @return プレイヤー表示名
     */
    public abstract String getDisplayName();

    /**
     * プレフィックスを返す
     * @return プレフィックス
     */
    public abstract String getPrefix();

    /**
     * サフィックスを返す
     * @return サフィックス
     */
    public abstract String getSuffix();

    /**
     * メッセージを送る
     * @param message 送るメッセージ
     */
    public abstract void sendMessage(String message);

    /**
     * メッセージを送る
     * @param message 送るメッセージ
     */
    public abstract void sendMessage(BaseComponent[] message);

    /**
     * 発言者が今いるワールドのワールド名を取得する
     * @return ワールド名
     */
    public abstract String getWorldName();

    /**
     * 発言者が今いるサーバーのサーバー名を取得する
     * @return サーバー名
     */
    public abstract String getServerName();

    /**
     * 指定されたパーミッションノードの権限を持っているかどうかを取得する
     * @param node パーミッションノード
     * @return 権限を持っているかどうか
     */
    public abstract boolean hasPermission(String node);

    /**
     * 文字列表現を返す
     * @return 名前管理なら名前、UUID管理なら "$" + UUID を返す
     */
    @Override
    public abstract String toString();

    /**
     * 指定されたパーミッションノードが定義されているかどうかを取得する
     * @param node パーミッションノード
     * @return 定義を持っているかどうか
     */
    public abstract boolean isPermissionSet(String node);

    /**
     * 指定されたメッセージの内容を発言する
     * @param message メッセージ
     */
    public abstract void chat(String message);

    /**
     * 同一のオブジェクトかどうかを返す
     * @param other 他方のオブジェクト
     * @return 同一かどうか
     */
    @Override
    public boolean equals(Object other) {
        if ( !(other instanceof Member) ) {
            return false;
        }
        return this.toString().equals(other.toString());
    }

    /**
     * インスタンス同士の比較を行う。このメソッドを実装しておくことで、
     * Java8でのHashMapのキー挿入における高速化が期待できる（らしい）。
     * @param other
     * @return
     */
    @Override
    public int compareTo(Member other) {
        return this.toString().compareTo(other.toString());
    }

    /**
     * 名前またはUUIDから、Memberを作成して返す
     * @param nameOrUuid 名前、または、"$" + UUID
     * @return Member
     */
    public static Member getMember(String nameOrUuid) {
        return MemberPlayer.getMember(nameOrUuid);
    }

    /**
     * オブジェクトから、Memberを作成して返す
     * @param obj
     * @return Member
     */
    public static Member getMember(Object obj) {

        if ( obj != null && obj instanceof Member ) return (Member)obj;
        return MemberBukkit.getMemberBukkit(obj);
    }
}