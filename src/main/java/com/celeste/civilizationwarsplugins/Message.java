package com.celeste.civilizationwarsplugins;

import com.celeste.civilizationwarsplugins.util.KeywordReplacer;
import com.celeste.civilizationwarsplugins.util.Utility;
import com.celeste.civilizationwarsplugins.util.YamlConfig;
import com.celeste.civilizationwarsplugins.util.YamlSection;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class Message {
    private static YamlConfig resources;
    private static File _messageFolder;
    private static File _jar;

    public static void initialize(File messagesFolder, File jar, String lang) {
        _jar = jar;
        _messageFolder = messagesFolder;
        if (!_messageFolder.exists()) {
            _messageFolder.mkdir();

            for (String filename : new String[]{"message.yml"}) {
                File file = new File(_messageFolder, filename);
                if (!file.exists()) {
                    Utility.copyFileFromJar(_jar, file, filename, true);
                }
            }
            // デフォルトメッセージを、jarファイル内からロードする
            YamlConfig defaultMessages = null;
            if (_jar != null) {
                try (JarFile jarFile = new JarFile(_jar)) {

                    ZipEntry zipEntry = jarFile.getEntry(String.format("messages.yml", lang));
                    if (zipEntry == null) {
                        zipEntry = jarFile.getEntry("messages.yml");
                    }

                    defaultMessages = YamlConfig.load(jarFile.getInputStream(zipEntry));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // 対応する言語のメッセージをロードする
            File file = new File(_messageFolder, String.format("messages.yml", lang));
            if (!file.exists()) {
                file = new File(_messageFolder, "messages.yml");
            }

            resources = YamlConfig.load(file);
            resources.addDefaults(defaultMessages);
        }
    }

    public static String usageTest(Object label) {
        String msg = resources.getString("usageTest");
        if (msg == null) return "";
        KeywordReplacer kr = new KeywordReplacer(msg);
        kr.replace("%label%", label.toString());
        return Utility.replaceColorCode(kr.toString());
    }

    /**
     * &e----- &6LunaChat %type% command (&c%num%&6/&c%max%&6) &e-----
     */
    public static String usageTop(Object num, Object max) {
        String msg = resources.getString("usageTop");
        if ( msg == null ) return "";
        KeywordReplacer kr = new KeywordReplacer(msg);
        kr.replace("%num%", num.toString());
        kr.replace("%max%", max.toString());
        return Utility.replaceColorCode(kr.toString());
    }

    /**
     * &e-----------------------------------------
     */
    public static String usageFoot() {
        String msg = resources.getString("usageFoot");
        if ( msg == null ) return "";
        KeywordReplacer kr = new KeywordReplacer(msg);
        return Utility.replaceColorCode(kr.toString());
    }

    /**
     * &6次のページを見るには、&c/%label% help %type% %next%&6 と実行してください。
     */
    public static String usageNoticeNextPage(Object label, Object type, Object next) {
        String msg = resources.getString("usageNoticeNextPage");
        if ( msg == null ) return "";
        KeywordReplacer kr = new KeywordReplacer(msg);
        kr.replace("%label%", label.toString());
        kr.replace("%next%", next.toString());
        return Utility.replaceColorCode(kr.toString());
    }



    /**
     * &6/%label% help [user|mod|admin] [page] &7- ヘルプを表示します。
     */
    public static String usageHelp(Object label) {
        String msg = resources.getString("usageHelp");
        if ( msg == null ) return "";
        KeywordReplacer kr = new KeywordReplacer(msg);
        kr.replace("%label%", label.toString());
        return Utility.replaceColorCode(kr.toString());
    }
}
