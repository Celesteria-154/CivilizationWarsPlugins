package com.celeste.civilizationwarsplugins;

import com.celeste.civilizationwarsplugins.command.CwpCommand;
import com.celeste.civilizationwarsplugins.member.Member;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;



public class CivilizationWarsPlugins extends JavaPlugin implements PluginInterface{

    private static CivilizationWarsPlugins instance;
    private CivilizationWarsConfig config;
    private BukkitTask Bukkittask;
    private UUIDCache uuidCache;
    private CwpCommand cwpCommand;
    @Override
    public void onEnable() {
        // Plugin startup logic
        CivilizationWars.setPlugin(this);
        //コマンドの登録
        cwpCommand = new CwpCommand();

        // config.ymlが存在しない場合ファイルに出力。
        this.saveDefaultConfig();

        config = new CivilizationWarsConfig(getDataFolder(), getFile());
        uuidCache = new UUIDCache(getDataFolder());
        Message.initialize(new File(getDataFolder(), "messages"), getFile());

    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (Bukkittask != null) {
            Bukkittask.cancel();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        cwpCommand = new CwpCommand();
        if ( command.getName().equals("cwp") ) {
            return cwpCommand.execute(Member.getMember(sender), label, args);
        }
        return false;
    }

    /** TABキー補完が実行されたときに呼び出されるメソッド
    **/
    @Override
     public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        cwpCommand = new CwpCommand();
        List<String> completeList = null;
        if ( command.getName().equals("cwp") ) {
            completeList = cwpCommand.onTabComplete(Member.getMember(sender), label, args);
        }
        if ( completeList != null ) {
            return completeList;
        }
        return super.onTabComplete(sender, command, label, args);
    }

    /**
     * CivilizationChatのインスタンスを返す
     * @return Civilization
     */
    public static CivilizationWarsPlugins getInstance() {
        if ( instance == null ) {
            instance = (CivilizationWarsPlugins) Bukkit.getPluginManager().getPlugin("CivilizationWars");
        }
        return instance;
    }
    @Override
    public File getPluginJarFile() {
        return null;
    }

    @Override
    public CivilizationWarsConfig getCivilizationWarsConfig() {
        return null;
    }

    @Override
    public Set<String> getOnlinePlayerNames() {
        return null;
    }

    @Override
    public void log(Level level, String msg) {

    }

    @Override
    public UUIDCache getUUIDCacheData() {
        return null;
    }

    @Override
    public void runAsyncTask(Runnable task) {

    }
}
