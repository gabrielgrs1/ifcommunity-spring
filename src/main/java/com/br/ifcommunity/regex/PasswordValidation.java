/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifcommunity.regex;

import java.util.regex.Pattern;

/**
 *
 * @author mmara_000
 */
public class PasswordValidation {

    private String errors = "";
    private String password;

    public PasswordValidation(String password) {
        this.password = password;
    }

    public String getErrors() {
        char[] Senha = password.toCharArray();

        // Verifica se a senha tem no máximo 25 caracteres
        if (Senha.length > 25) {
            errors += " A senha deve ter no máximo 25 caracteres!";
        }

        // Verifica se a senha tem os requisitos minimos
        Pattern pattAlphaNum = Pattern.compile("^[a-zA-Z0-9$@$!%*?&+-]+$");
        if (!pattAlphaNum.matcher(password).matches()) {
            errors += " A senha não atende aos requisitos!";
        }

        return errors;
    }

}
