package com.company;



import java.util.Arrays;
import java.lang.StringBuffer;


public class CaesarCipher {

    public static StringBuffer encrypt(String text, int s){

        StringBuffer result = new StringBuffer();

        s = s%26;

        for(int i = 0; i < text.length(); i++){
            if(Character.isLetter(text.charAt(i))) {
                if (Character.isUpperCase(text.charAt(i))) {
                    char ch = (char) (((int) text.charAt(i) + s - 65 + 26) % 26 + 65);
                    result.append(ch);
                } else {
                    char ch = (char) (((int) text.charAt(i) + s - 97 + 26) % 26 + 97);
                    result.append(ch);
                }
            }else{
                result.append(text.charAt(i));
            }
        }
        return result;
    }

    public static StringBuffer decrypt(String text, int s){

        StringBuffer result = new StringBuffer();

        result = encrypt(text,(26 - s)%26);
        return result;
    }
}
