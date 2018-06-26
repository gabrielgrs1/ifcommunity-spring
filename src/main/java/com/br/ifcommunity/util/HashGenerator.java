package com.br.ifcommunity.util;

public class HashGenerator {
    public static String gerenerateHashUser() {
        String[] characterArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        StringBuilder userHash = new StringBuilder();

        for (int x = 0; x < 100; x++) {
            int j = (int) (Math.random() * characterArray.length);
            userHash.append(characterArray[j]);
        }

        return userHash.toString();
    }

}