package com.celeste.civilizationwarsplugins.util;

import com.google.common.io.Files;

import java.io.*;
import java.util.Locale;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class Utility {
    public static void copyFileFromJar(File jarFile, File targetFile, String sourceFilePath, boolean isBinary) {
        File parent = targetFile.getParentFile();
        if (!parent.exists()) {
            parent.mkdir();
        }

        if (jarFile.isDirectory()) {
            File file = new File(jarFile, sourceFilePath);
            try {
                Files.copy(file, targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            try (JarFile jar = new JarFile(jarFile)) {
                ZipEntry zipEntry = jar.getEntry(sourceFilePath);
                InputStream is = jar.getInputStream(zipEntry);

                try (FileOutputStream fos = new FileOutputStream(targetFile)) {

                    if (isBinary) {
                        byte[] buf = new byte[8192];
                        int len;
                        while ((len = is.read(buf)) != -1) {
                            fos.write(buf, 0, len);
                        }

                    } else {

                        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"))) {

                            String line;
                            while ((line = reader.readLine()) != null) {
                                writer.write(line);
                                writer.newLine();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //文字列内のカラーコード候補（&a）を、カラーコード（§a）に置き換えする
    public static String replaceColorCode(String source) {
        if (source == null) return null;
        return replaceWebColorCode(source)
                .replaceAll("&([0-9a-fk-orA-FK-OR])", "\u00A7$1");
    }

    //Webカラーコード（#99AABBなど）を、カラーコードに置き換えする
    private static String replaceWebColorCode(String source) {
        return source
                .replaceAll(
                        "#([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])",
                        "\u00A7x\u00A7$1\u00A7$2\u00A7$3\u00A7$4\u00A7$5\u00A7$6")
                .replaceAll(
                        "#([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])",
                        "\u00A7x\u00A7$1\u00A7$1\u00A7$2\u00A7$2\u00A7$3\u00A7$3");
    }

    //文字列に含まれているカラーコード（§a）やカラーコード候補（&aや#99AABB）を除去して返す
    public static String stripColorCode(String source) {
        if (source == null) return null;
        return stripAltColorCode(source).replaceAll("\u00A7([0-9a-fk-orxA-FK-ORX])", "");
    }

    //文字列に含まれているカラーコード候補（&aや#99AABB）を除去して返す
    public static String stripAltColorCode(String source) {
        if (source == null) return null;
        source = source.replaceAll("#[0-9a-fA-F]{6}", "").replaceAll("#[0-9a-fA-F]{3}", "");
        return source.replaceAll("&([0-9a-fk-orxA-FK-ORX])", "");
    }

    //カラーコード（§a）かどうかを判断する

    public static boolean isColorCode(String code) {
        if (code == null) return false;
        return code.matches("\u00A7[0-9a-fk-orxA-FK-ORX]");
    }

    //カラーコード候補（&aや#99AABB）かどうかを判断する

    public static boolean isAltColorCode(String code) {
        if (code == null) return false;
        return code.matches("(&[0-9a-fk-orA-FK-OR]|#[0-9a-fA-F]{3}|#[0-9a-fA-F]{6})");
    }

    //指定された文字数のアスタリスクの文字列を返す

    public static String getAstariskString(int length) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < length; i++) {
            buf.append("*");
        }
        return buf.toString();
    }
}