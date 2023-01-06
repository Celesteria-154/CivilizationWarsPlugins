package com.celeste.civilizationwarsplugins;

import java.io.File;
import java.util.logging.Level;

public interface PluginsInterface {
    public File getPluginJarFile();
    public void log(Level level,String msg);
    public void runAsyncTask(Runnable task);

    public CivilizationWarsConfig getCivilizationWarsConfig();

    public UUIDCache getUUIDCacheData();
}
