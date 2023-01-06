package com.celeste.civilizationwarsplugins;

import java.io.File;
import java.util.Set;
import java.util.logging.Level;

public interface PluginInterface {

    /**
     * Jarファイル自身を示すFileクラスを返す。
     * @return Jarファイル
     */
    public File getPluginJarFile();

    /**
     * CivilizationWarsConfigを取得する
     * @return CivilizationWarsConfig
     */
    public CivilizationWarsConfig getCivilizationWarsConfig();

    /**
     * プラグインのデータ格納フォルダを取得する
     * @return データ格納フォルダ
     */
    public File getDataFolder();

    /**
     * オンラインのプレイヤー名一覧を取得する
     * @return オンラインのプレイヤー名一覧
     */
    public Set<String> getOnlinePlayerNames();

    /**
     * このプラグインのログを記録する
     * @param level ログレベル
     * @param msg ログメッセージ
     */
    public void log(Level level, String msg);

    /**
     * UUIDキャッシュデータを取得する
     * @return UUIDキャッシュデータ
     */
    public UUIDCache getUUIDCacheData();

    /**
     * 非同期タスクを実行する
     * @param task タスク
     */
    public void runAsyncTask(Runnable task);
}
