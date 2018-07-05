package com.br.ifcommunity.util;

import java.util.ArrayList;

public class WordFilter {
    public static String verifyFWord(String string) {
        ArrayList<String> FWords = new ArrayList<>();
        // Para inserir valores no ArrayList, utilizamos o método add
        FWords.add("caralho");
        FWords.add("disgraca");
        FWords.add("disgraça");
        FWords.add("disgrassa");
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
        FWords.add("satanas");
        FWords.add("satanás");
        FWords.add("satan");
        FWords.add("merda");
        FWords.add("lixo");
        FWords.add("aborto");
        FWords.add("puta");
        FWords.add("kct");
        FWords.add("kacete");
        FWords.add("k7");
        FWords.add("kassete");
        FWords.add("cassete");
        FWords.add("caralhudo");
        FWords.add("caralhao");
        FWords.add("viado");
        FWords.add("viadinho");
        FWords.add("viadão");
        FWords.add("karalho");
        FWords.add("kralho");
        FWords.add("cacete");
        FWords.add("prioca");
        FWords.add("pirocão");
        FWords.add("idiota");
        FWords.add("imbecil");
        FWords.add("otario");
        FWords.add("otário");
        FWords.add("bunda");
        FWords.add("incompetente");
        FWords.add("estupido");
        FWords.add("estupida");
        FWords.add("estúpida");
        FWords.add("estúpido");
        FWords.add("corno");
        FWords.add("gosar");
        FWords.add("gozar");
        FWords.add("putaria");
        FWords.add("lésbica");
        FWords.add("lesbica");
        FWords.add("pirocudo");
        FWords.add("caceta");
        FWords.add("arrombado");
        FWords.add("porra");
        FWords.add("xana");
        FWords.add("xoxota");
        FWords.add("xota");
        FWords.add("maconheiro");
        FWords.add("chupador");
        FWords.add("drogado");
        FWords.add("drogadinho");
        FWords.add("nóia");
        FWords.add("noia");
        FWords.add("crackudo");
        FWords.add("cracudo");
        FWords.add("seu doente");
        FWords.add("seu duente");
        
        
        
        for (String FWord : FWords) {
            string = string.toLowerCase().replace(FWord.toLowerCase(), "*****");
        }

       return string;
    }
}
