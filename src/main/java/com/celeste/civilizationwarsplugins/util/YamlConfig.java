package com.celeste.civilizationwarsplugins.util;

import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

public class YamlConfig extends YamlSection {
    //InputStreamからYamlConfigをロードする
    public static YamlConfig load(InputStream stream) throws IOException {

        YamlConfig config = new YamlConfig();
        if (stream == null) return config;

        Yaml yaml = new Yaml();

        @SuppressWarnings("unchecked")
        Map<String, Object> map = yaml.loadAs(stream, Map.class);
        if (map == null) {
            throw new IOException("Cannot load stream as yaml.");
        }

        config.map = map;
        return config;
    }

    //ReaderからYamlConfigをロードする
    public static YamlConfig load(Reader reader) throws IOException {

        YamlConfig config = new YamlConfig();
        if (reader == null) return config;

        Yaml yaml = new Yaml();

        @SuppressWarnings("unchecked")
        Map<String, Object> map = yaml.loadAs(reader, Map.class);
        if (map == null) {
            throw new IOException("Cannot load reader as yaml.");
        }

        config.map = map;
        return config;
    }

    //FileからYamlConfigをロードする
    public static @NotNull YamlConfig load(File file) {

        // 対象ファイルが存在しない場合やからっぽの場合は、からっぽのYamlConfigを返す
        if (!file.exists() || !file.isFile() || file.length() == 0) return new YamlConfig();

        // 読み込む
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "UTF-8")) {
            return load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 読み込みに失敗した場合は、からっぽのYamlConfigを返す
        return new YamlConfig();
    }

    //保存する
    public void save(File file) throws IOException {

        Yaml yaml = new Yaml();
        String data = yaml.dumpAsMap(map);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(data);
        }
    }
}
