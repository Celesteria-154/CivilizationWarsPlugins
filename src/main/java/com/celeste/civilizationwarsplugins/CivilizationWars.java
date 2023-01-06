package com.celeste.civilizationwarsplugins;

public class CivilizationWars {
    private static PluginInterface instance;

    //実行元プラグインクラスを設定する
    static void setPlugin(PluginInterface plugin) {
        instance = plugin;
    }

    public static CivilizationWarsConfig getConfig(){
        return instance.getCivilizationWarsConfig();
    }

    /**
     * @return Cache
     */
    public static UUIDCache getUUIDCacheData() {
        return instance.getUUIDCacheData();
    }

}
