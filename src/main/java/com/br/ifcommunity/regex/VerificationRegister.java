package com.br.ifcommunity.regex;

import java.util.regex.Pattern;

public class VerificationRegister {
    public static boolean enrolledNumberValidate(String enrroledNumber) {
        Pattern pattern = Pattern.compile("^[0-9]{11}-[0-9]$");
        if (pattern.matcher(enrroledNumber).find()) {
            return true;
        }

        return false;
    }
}

//class main {
//    public static void main(String[] args) {
//        System.out.println(VerificationRegister.enrolledNumberValidate("0185861857-1"));
//    }
//}