package com.celeste.civilizationwarsplugins;

import com.celeste.civilizationwarsplugins.util.KeywordReplacer;
import com.celeste.civilizationwarsplugins.util.Utility;
import com.celeste.civilizationwarsplugins.util.YamlConfig;
import com.celeste.civilizationwarsplugins.util.YamlSection;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class Message{
    private static YamlConfig resources;
    private static File _messageFolder;
    private static File _jar;

    public static void initialize(File messagesFolder, File jar) {

        _jar = jar;
        _messageFolder = messagesFolder;
        if (!_messageFolder.exists()) {
            _messageFolder.mkdir();
        }

        // コンフィグフォルダにメッセージファイルがまだ無いなら、コピーしておく
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
                 ZipEntry zipEntry = jarFile.getEntry(String.format("messages.yml"));
                if (zipEntry == null) {
                    zipEntry = jarFile.getEntry("messages.yml");
                }
                 defaultMessages = YamlConfig.load(jarFile.getInputStream(zipEntry));
             } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file = new File(_messageFolder, String.format("messages.yml"));
        if (!file.exists()) {
            file = new File(_messageFolder, "messages.yml");
        }
        resources = YamlConfig.load(file);
        resources.addDefaults(defaultMessages);
    }


    public static String usageTest(Object label) {
        String msg = resources.getString("usageTest");
        if (msg == null) return "";
        KeywordReplacer kr = new KeywordReplacer(msg);
        kr.replace("%label%", label.toString());
        return Utility.replaceColorCode(kr.toString());
    }

    /**
     * &e----- &6cwp %type% command (&c%num%&6/&c%max%&6) &e-----
     */
    public static String usageTop(Object type,Object num, Object max) {
        String msg = resources.getString("usageTop");
        if ( msg == null ) return "";
        KeywordReplacer kr = new KeywordReplacer(msg);
        kr.replace("%type%", type.toString());
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

    /**
     * コマンドの指定が正しくありません。
     */
    public static String errmsgCommand() {
        String msg = resources.getString("errmsgCommand");
        if ( msg == null ) return "";
        KeywordReplacer kr = new KeywordReplacer(msg);
        return Utility.replaceColorCode(resources.getString("errorPrefix", "") + kr.toString());
    }

    /**
     * 権限 "%permission%" が無いため、実行できません。
     */
    public static String errmsgPermission(Object permission) {
        String msg = resources.getString("errmsgPermission");
        if ( msg == null ) return "";
        KeywordReplacer kr = new KeywordReplacer(msg);
        kr.replace("%permission%", permission.toString());
        return Utility.replaceColorCode(resources.getString("errorPrefix", "") + kr.toString());
    }

    /**
     * このコマンドはゲーム内からしか実行できません。
     */
    public static String errmsgIngame() {
        String msg = resources.getString("errmsgIngame");
        if ( msg == null ) return "";
        KeywordReplacer kr = new KeywordReplacer(msg);
        return Utility.replaceColorCode(resources.getString("errorPrefix", "") + kr.toString());
    }

}
