/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifcommunity.util;

/**
 * @author mmara_000
 */

import java.util.Random;

public class EncryptPassword {

    private String password;
    private String encryptedPassword;
    public Random randomKey = new Random();


    public EncryptPassword(String password) {
        this.password = password;
    }

    public String encrypt() {
        char[] passwordCharArray = password.toCharArray();
        char[] encryptedPasswordCharArray = new char[(passwordCharArray.length * 3) + randomize(0, 30) + 1];

        int limite = (encryptedPasswordCharArray.length) - (passwordCharArray.length * 3);
        int indice = randomKey.nextInt(limite);

        encryptedPasswordCharArray = montaCharArray(indice, encryptedPasswordCharArray.length, passwordCharArray, false, encryptedPasswordCharArray);
        encryptedPasswordCharArray = montaCharArray(indice, passwordCharArray.length, passwordCharArray, true, encryptedPasswordCharArray);

        encryptedPassword = String.valueOf(encryptedPasswordCharArray);
        return encryptedPassword;
    }

    private char[] montaCharArray(int index, int length, char[] passwordCharArray, boolean flag, char[] encriptKey) {
        char charAvulse = ' ';
        char[] aleatoryCharArray = new char[3];
        int temporaryCount = 0;

        for (int j = 0; j < length; j++) {
            if (!flag) {
                // monta o array todo aleatório
                if (j == 0) {
                    charAvulse = (char) (randomize(33, 126));
                    encriptKey[j] = charAvulse;
                } else {
                    // Segurança para que não tenha como achar chave onde não pode.
                    do {
                        charAvulse = (char) (randomize(33, 126));
                        encriptKey[j] = charAvulse;
                    } while ((Math.abs(encriptKey[j - 1] - encriptKey[j]) < 5));
                }
            } else if (flag) {
                if (passwordCharArray[j] % 2 == 0) {
                    // joga a password dentro do array aleatório
                    for (int k = 0; k < aleatoryCharArray.length; k++) {
                        do {
                            switch (k) {
                                case 0:
                                    charAvulse = (char) (passwordCharArray[j] + randomize(0, 3));
                                    break;
                                case 1:
                                    charAvulse = (char) (passwordCharArray[j] - randomize(0, 5));
                                    break;
                                case 2:
                                    charAvulse = (char) (passwordCharArray[j] + randomize(3, 5));
                                    break;
                                default:
                                    break;
                            }

                            aleatoryCharArray[k] = charAvulse;
                        } while ((charAvulse == ' ') && (charAvulse > 33 && charAvulse < 126));
                    }
                } else if (passwordCharArray[j] % 2 != 0) {
                    // joga a password dentro do array aleatório
                    for (int k = 0; k < aleatoryCharArray.length; k++) {
                        do {
                            switch (k) {
                                case 0:
                                    charAvulse = (char) (passwordCharArray[j] + randomize(3, 5));
                                    break;
                                case 1:
                                    charAvulse = (char) (passwordCharArray[j] - randomize(0, 5));
                                    break;
                                case 2:
                                    charAvulse = (char) (passwordCharArray[j] + randomize(0, 3));
                                    break;
                                default:
                                    break;
                            }

                            aleatoryCharArray[k] = charAvulse;
                        } while ((charAvulse == ' ' || charAvulse == ' ') && (charAvulse > 33 && charAvulse < 126));
                    }
                }

                encriptKey[index + temporaryCount] = aleatoryCharArray[0];
                temporaryCount++;
                encriptKey[index + temporaryCount] = aleatoryCharArray[1];
                temporaryCount++;
                encriptKey[index + temporaryCount] = aleatoryCharArray[2];
                temporaryCount++;

                // tratamento do char anterior e do char posterior após a inserção do código
                if (((passwordCharArray[0]) + 10 > 126) && index > 0) {
                    encriptKey[index - 1] = (char) ((passwordCharArray[0]) - 6);
                } else if ((passwordCharArray[0]) - 10 < 33 && index > 0) {
                    encriptKey[index - 1] = (char) ((passwordCharArray[0]) + 6);
                } else if (index > 0) {
                    encriptKey[index - 1] = (char) ((passwordCharArray[0]) + 10);
                } else if ((encriptKey.length - passwordCharArray.length * 3 - index - 1) != 0) {
                    encriptKey[index + passwordCharArray.length * 3 + 1] = (char) ((passwordCharArray[0]) + 6);
                }
            }
        }
        return encriptKey;
    }

    private int randomize(int i, int j) {
        int x;
        do {
            x = randomKey.nextInt(j + 10);
        } while (x <= i || x >= j);
        return x;
    }
}

