package com.br.ifcommunity.util;

import java.util.ArrayList;

public class WordFilter {
    public static String verifyFWord(String string) {
        ArrayList<String> FWords = new ArrayList<>();
        // Para inserir valores no ArrayList, utilizamos o método add
        FWords.add("caralho");
        FWords.add("disgraca");
        FWords.add("buceta");
        FWords.add("desgraça");
        FWords.add("inferno");
        FWords.add("vai toma no");
        FWords.add("cu");
        FWords.add("cú");
        FWords.add("vtnc");
        FWords.add("vsf");
        FWords.add("pinto");
        FWords.add("rola");
        FWords.add("fode");
        FWords.add("fude");

        for (String FWord : FWords) {
            string = string.toLowerCase().replace(FWord.toLowerCase(), "[CENSURADO]");
        }

       return string;
    }
}
