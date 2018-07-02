package com.br.ifcommunity.regex;

import java.util.regex.Pattern;

public class VerificationRegister {
    public static boolean enrolledNumberValidate(String enrroledNumber) {
        Pattern pattern = Pattern.compile("^[0-9]{11}-[0-9]$");
        return pattern.matcher(enrroledNumber).find();
    }

    public static boolean mail(String verifyString) {
        Pattern pattern = Pattern.compile("(?:[a-z0-9!#$%&\"*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&\"*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])/");
        return pattern.matcher(verifyString).find();
    }
}

//class main {
//    public static void main(String[] args) {
//        System.out.println(VerificationRegister.enrolledNumberValidate("0185861857-1"));
//    }
//}