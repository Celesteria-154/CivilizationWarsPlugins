package com.celeste.civilizationwarsplugins;

import com.celeste.civilizationwarsplugins.util.YamlConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * civilizationwarsのコンフィグクラス
 */
public class CivilizationWarsConfig {

    private String test;
    private List<String> mapName;
    /**
     * コンストラクタ
     * @param dataFolder コンフィグ格納ファイル
     * @param jarFile プラグインjar File
     */
    public CivilizationWarsConfig(File dataFolder, File jarFile) {
        reloadConfig(dataFolder, jarFile);
    }

    /**
     *再読み込み
     */
    public void reloadConfig(File dataFolder, File jarFile){
        File configFile = new File(dataFolder,"config.yml");
        YamlConfig config = YamlConfig.load(configFile);
        test = config.getString("oppai","true");
        if(config.contains("mapName")){
            mapName = config.getStringList("map");
        }else{
            mapName = new ArrayList<String>();
            mapName.add("custom_world");
        }
    }

    /**
     * test
     * @return true
     */
    public String getTest(){
        return test;
    }

    public List<String> getMapName(){
        return mapName;
    }


}
