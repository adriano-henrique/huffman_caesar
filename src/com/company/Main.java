package com.company;

import java.lang.StringBuffer;
import java.lang.String;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Solution solution = new Solution();
        Scanner input = new Scanner(System.in);
        System.out.println("What is the path to the file you want to encrypt, decrypt and analyze? \n");
        String path = input.nextLine();
        System.out.println("What is the offset you want for the Caesar Cipher? \n");
        String offset_str = input.nextLine();
        int offset = Integer.parseInt(offset_str);
        solution.solve(path, offset);

    }
}
