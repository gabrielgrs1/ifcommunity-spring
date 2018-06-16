/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifcommunity.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mmara_000
 */
public class DecryptPassword {

    private String password;
    private String describedPassword;

    public DecryptPassword(String password) {
        super();
        this.password = password;
    }

    private Map<String, String> key = new HashMap<>();

    private void keyMount() {
        char[] key = new char[3];
        int limInf = 32, limSup = 127;

        do {
            if (limInf % 2 == 0) {
                for (int j = 0; j < 5; j++) {
                    key[1] = (char) (limInf - j);
                    parseKeyMount(key, limInf);
                }
            } else if (limInf % 2 != 0) {
                for (int j = 1; j < 5; j++) {
                    key[1] = (char) (limInf - j);
                    parseKeyMount(key, limInf);
                }
            }
            limInf++;
        } while (limInf != limSup);

    }

    private void parseKeyMount(char[] key, int limInf) {
        for (int i = 3; i < 5; i++) {
            key[0] = (char) (limInf + i);
            for (int k = 0; k < 3; k++) {
                key[2] = (char) (limInf + k);
                this.key.put(String.valueOf(key), String.valueOf((char) (limInf)));
            }
        }
    }

    public String decrypt() {
        char[] passwordCharArray = password.toCharArray();
        char[] temporary = new char[3];
        int count = 0;
        boolean found = false;

        ArrayList<Character> decryptedPassword = new ArrayList<Character>();

        keyMount();

        do {
            for (int i = 0; i < temporary.length; i++) {
                temporary[i] = passwordCharArray[count];
                count++;
            }
            if (key.get(String.valueOf(temporary)) != null) {
                char aux = (key.get(String.valueOf(temporary))).charAt(0);
                decryptedPassword.add(aux);
                found = true;
            }
            if (!found) {
                count = count - 2;
            }
            if ((count > passwordCharArray.length - 2) || (found && key.get(String.valueOf(temporary)) == null)) {
                break;
            }
        } while (count != (passwordCharArray.length - 2));

        char[] decryptedCharArray = new char[decryptedPassword.size()];
        for (int i = 0; i < decryptedCharArray.length; i++) {
            decryptedCharArray[i] = decryptedPassword.get(i);
        }

        describedPassword = String.valueOf(decryptedCharArray);

        return describedPassword;
    }

}

