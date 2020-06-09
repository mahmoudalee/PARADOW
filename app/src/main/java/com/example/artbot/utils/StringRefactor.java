package com.example.artbot.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringRefactor {
    public static String getENstring(String word){

        Pattern p = Pattern.compile("[a-zA-Z']{2,}");

        Matcher m = p.matcher(word);

        String res ="";
        while (m.find()){
            res += m.group() +" ";
        }
        // to remove _ar_ from last
        res = res.substring(2, res.length() - 4);
        return res;
    }
}
