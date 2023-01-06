package com.celeste.civilizationwarsplugins.member;

import com.celeste.civilizationwarsplugins.CivilizationWars;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

import static org.bukkit.Bukkit.getLogger;

public class MemberPlayer extends MemberBukkit {

    private UUID id;

    /**
     * コンストラクタ
     *
     * @param id プレイヤーID
     */
    public MemberPlayer(String id) {
        this.id = UUID.fromString(id);
    }

    /**
     * コンストラクタ
     *
     * @param id UUID
     */
    public MemberPlayer(UUID id) {
        this.id = id;
    }

    /**
     * プレイヤー名からUUIDを取得してMemberPlayerを作成して返す
     *
     * @param name プレイヤー名
     * @return MemberPlayer
     */
    public static MemberPlayer getMemberPlayerFromName(String name) {
        Player player = Bukkit.getPlayerExact(name);
        if (player != null) {
            return new MemberPlayer(player.getUniqueId());
        }
        @SuppressWarnings("deprecation")
        OfflinePlayer offline = Bukkit.getOfflinePlayer(name);
        if (offline != null && offline.getUniqueId() != null) {
            return new MemberPlayer(offline.getUniqueId());
        }
        return null;
    }

    /**
     * CommandSenderから、Playerを作成して返す
     *
     * @param sender
     * @return Player
     */
    public static MemberPlayer getPlayer(CommandSender sender) {
        if (sender instanceof Player) {
            return new MemberPlayer(((Player) sender).getUniqueId());
        }
        return new MemberPlayer(sender.getName());
    }

    /**
     * オンラインかどうか
     *
     * @return オンラインかどうか
     */
    @Override
    public boolean isOnline() {
        Player player = Bukkit.getPlayer(id);
        return (player != null);
    }

    /**
     * プレイヤー名を返す
     *
     * @return プレイヤー名
     */
    @Override
    public String getName() {
        String cache = CivilizationWars.getUUIDCacheData().get(id.toString());
        if (cache != null) {
            return cache;
        }
        Player player = Bukkit.getPlayer(id);

        getLogger().info(String.valueOf(player));

        if (player != null) {
            return player.getName();
        }
        OfflinePlayer offlineplayer = Bukkit.getOfflinePlayer(id);
        if (offlineplayer != null) {
            String name = offlineplayer.getName();
            return name;
        }

        getLogger().info(id.toString());

        return id.toString();
    }

    /**
     * プレイヤー表示名を返す
     *
     * @return プレイヤー表示名
     */
    @Override
    public String getDisplayName() {
        Player player = getPlayer();
        if (player != null) {
            return player.getDisplayName();
        }
        return getName();
    }

    /**
     * プレフィックスを返す
     *
     * @return プレフィックス
     */
    @Override
    public String getPrefix() {
        return "";
    }

    /**
     * サフィックスを返す
     *
     * @return サフィックス
     */
    @Override
    public String getSuffix() {
        return "";
    }

    /**
     * メッセージを送る
     *
     * @param message 送るメッセージ
     */
    @Override
    public void sendMessage(String message) {
        if (message == null || message.isEmpty()) return;
        Player player = getPlayer();
        if (player != null) {
            player.sendMessage(message);
        }
    }

    /**
     * メッセージを送る
     *
     * @param message 送るメッセージ
     */
    public void sendMessage(BaseComponent[] message) {
        if (message == null || message.length == 0) return;
        Player player = getPlayer();
        if (player != null) {
            player.spigot().sendMessage(message);
        }
    }

    /**
     * BukkitのPlayerを取得する
     *
     * @return Player
     */
    @Override
    public Player getPlayer() {
        return Bukkit.getPlayer(id);
    }

    /**
     * 発言者が今いるワールドを取得する
     *
     * @return 発言者が今いるワールド
     */
    @Override
    public World getWorld() {
        Player player = getPlayer();
        if (player != null) return player.getWorld();
        return null;
    }

    /**
     * 発言者が今いるワールドのワールド名を取得する
     *
     * @return ワールド名
     */
    @Override
    public String getWorldName() {
        World world = getWorld();
        if (world == null) return "";
        return world.getName();
    }

    /**
     * 発言者が今いる位置を取得する
     *
     * @return 発言者の位置
     */
    @Override
    public Location getLocation() {
        Player player = getPlayer();
        if (player != null) {
            return player.getLocation();
        }
        return null;
    }

    /**
     * 指定されたパーミッションノードの権限を持っているかどうかを取得する
     *
     * @param node パーミッションノード
     * @return 権限を持っているかどうか
     */
    @Override
    public boolean hasPermission(String node) {
        Player player = getPlayer();
        if (player == null) {
            return false;
        } else {
            return player.hasPermission(node);
        }
    }

    /**
     * 指定されたパーミッションノードが定義されているかどうかを取得する
     *
     * @param node パーミッションノード
     * @return 定義を持っているかどうか
     */
    @Override
    public boolean isPermissionSet(String node) {
        Player player = getPlayer();
        if (player == null) {
            return false;
        } else {
            return player.isPermissionSet(node);
        }
    }

    /**
     * 指定されたメッセージの内容を発言する
     *
     * @param message メッセージ
     */
    public void chat(String message) {
        Player player = getPlayer();
        if (player != null) {
            player.chat(message);
        }
    }

    /**
     * IDを返す
     *
     * @return "$" + UUID を返す
     */
    @Override
    public String toString() {
        return "$" + id.toString();
    }

    public static MemberPlayer getMember(String nameOrUuid) {
        if (nameOrUuid.startsWith("$")) {
            return new MemberPlayer(nameOrUuid.substring(1));
        } else {
            @SuppressWarnings("deprecation")
            OfflinePlayer op = Bukkit.getOfflinePlayer(nameOrUuid);
            if (op == null) return null;
            return new MemberPlayer(op.getUniqueId());
        }
    }
}