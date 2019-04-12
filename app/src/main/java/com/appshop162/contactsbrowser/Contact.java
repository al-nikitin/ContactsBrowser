package com.appshop162.contactsbrowser;

import android.graphics.Bitmap;
import android.text.SpannableString;

public class Contact {
    String name, nameAsNumbers, number;
    SpannableString nameSS, numberSS;
    Bitmap photo;

    public Contact(String name, String number) {
        super();
        this.name = name;
        this.nameAsNumbers = getNameAsNumbers(name);
        this.number = number;
    }

    private String getNameAsNumbers(String name) {
        name = name.toLowerCase();
        String nameAsNumbers = "";
        for (int i = 0; i < name.length(); i++) {
            String s = name.substring(i, i + 1);
            nameAsNumbers += convertLetterToNumber(s);
        }
        return nameAsNumbers;
    }

    private String convertLetterToNumber(String s) {
        String s2 = "абвгabc";
        String s3 = "дежзdef";
        String s4 = "ийклghi";
        String s5 = "мнопjkl";
        String s6 = "рстуmno";
        String s7 = "фхцчpqrs";
        String s8 = "шщъыtuv";
        String s9 = "ьэюяwxyz";

        String result = s;

        if (s2.contains(s)) {
            result = "2";
        } else if (s3.contains(s)) {
            result = "3";
        } else if (s4.contains(s)) {
            result = "4";
        } else if (s5.contains(s)) {
            result = "5";
        } else if (s6.contains(s)) {
            result = "6";
        } else if (s7.contains(s)) {
            result = "7";
        } else if (s8.contains(s)) {
            result = "8";
        } else if (s9.contains(s)) {
            result = "9";
        }

        return result;
    }
}
