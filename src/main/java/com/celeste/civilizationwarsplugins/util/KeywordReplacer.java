package com.celeste.civilizationwarsplugins.util;

import java.util.regex.Pattern;

public class KeywordReplacer {
    private StringBuilder str;
    public KeywordReplacer(String src) {
        str = new StringBuilder(src);
    }

    public void replace(String keyword, String value) {
        int index = 0;
        while ((index = str.indexOf(keyword, index)) > -1) {
            str.replace(index, index + keyword.length(), value);
            index += value.length();
        }
    }

    //正規表現regexに一致する箇所を、文字列valueに置き換える

    public void replaceRegex(String regex, String value) {
        str = new StringBuilder(Pattern.compile(regex).matcher(str).replaceAll(value));
    }

    //文字列内のカラーコード候補（&a）を、カラーコード（§a）に置き換えする

    public void translateColorCode() {
        str = new StringBuilder(Utility.replaceColorCode(str.toString()));
    }

    //指定された文字列が含まれているかどうかを判定する

    public boolean contains(String keyword) {
        return str.indexOf(keyword) > -1;
    }

    //文字列をStringで取得する
    @Override
    public String toString() {
        return str.toString();
    }

    //文字列をStringBuilderのまま取得する

    public StringBuilder getStringBuilder() {
        return str;
    }

    public String substring(int start) {
        return str.substring(start);
    }

    public String substring(int start, int end) {
        return str.substring(start, end);
    }

    public int length() {
        return str.length();
    }
}
