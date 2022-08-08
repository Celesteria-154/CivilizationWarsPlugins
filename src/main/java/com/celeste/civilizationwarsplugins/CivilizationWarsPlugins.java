package com.celeste.civilizationwarsplugins;

import java.io.File;

import com.celeste.civilizationwarsplugins.command.CwpCommand;
import com.celeste.civilizationwarsplugins.command.CwpSubCommand;
import com.celeste.civilizationwarsplugins.command.TestCommand;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class CivilizationWarsPlugins extends JavaPlugin {

    private BukkitTask Bukkittask;
    public static PluginsInterface instance;

    private BukkitTask expireCheckerTask;
    private CwpCommand cwpCommand;
    public static PluginsInterface getPlugin() {
        return instance;
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        //コマンドの登録
        cwpCommand = new CwpCommand();
        // config.ymlが存在しない場合ファイルに出力。
        this.saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (Bukkittask != null) {
            Bukkittask.cancel();
        }
    }
}
