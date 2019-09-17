package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    public String fileToString(String path){
        try(Stream<String> lines = Files.lines(Paths.get(path))){
            String content = lines.collect(Collectors.joining(System.lineSeparator()));
            return content;
        }catch (IOException e){
            e.printStackTrace();;
        }
        return "yabadabadoo";
    }

    public String encrypt(CaesarCipher caesar, Huffman huffman, String text, int offset){
        String encryption_text = "";
        StringBuffer encrypted_caesar = caesar.encrypt(text, offset);
        String encrypted_caesar_string = encrypted_caesar.toString();
        String encrypted_huffman = huffman.encode(encrypted_caesar_string);

        encryption_text += "The encryption by the Caesar file is: " + encrypted_caesar_string + "\n";
        encryption_text += "The final encryption with Huffman is: " + encrypted_huffman;

        return encryption_text;
    }

    public String get_encrypted_huffman(CaesarCipher caesar, Huffman huffman, String text, int offset){
        String encryption_text = "";
        StringBuffer encrypted_caesar = caesar.encrypt(text, offset);
        String encrypted_caesar_string = encrypted_caesar.toString();
        String encrypted_huffman = huffman.encode(encrypted_caesar_string);

        return encrypted_huffman;
    }

    public String decrypt(CaesarCipher caesar, Huffman huffman, String text, int offset){
        String decryption_text = "";
        String encrypted_huffman = get_encrypted_huffman(caesar,huffman,text,offset);
        String decrypted_huffman = huffman.decode(encrypted_huffman);
        StringBuffer decrypted_caesar = caesar.decrypt(decrypted_huffman, offset);
        String decrypted_caesar_string = decrypted_caesar.toString();

        decryption_text += "The Huffman decryption is: " + decrypted_huffman + "\n";
        decryption_text += "The final text after all the decryptions is: " + decrypted_caesar_string + "\n";

        return decryption_text;
    }

    public String analyze(CaesarCipher caesar, Huffman huffman, String text, int offset){
        String analytics_text = "";
        String offset_caesar = "The Caesar Cipher was made based on the offset: " + offset;
        String huffman_map = huffman.getFrequencyMap(text);
        String character_prefix = huffman.getCharacterPrefixMap(text);

        analytics_text = offset_caesar + "\n \n"
                + " The frequency map of each character in the Huffman Coding is: \n" + huffman_map + "\n \n"
                + " The mapping from characters to binary used in the Huffman Coding is: \n" + character_prefix;

        return analytics_text;
    }

    public void solve(String path, int offset){
        CaesarCipher caesar = new CaesarCipher();
        Huffman huffman = new Huffman();

        String text = fileToString(path);

        String encryption_filename = "encrypted_file.txt";
        String decryption_filename = "decrypted_file.txt";
        String analytics_filename = "cripto_analysis_file.txt";

        String encryption_file_content = "";
        String decryption_file_content = "";
        String analytics_file_content = "";

        encryption_file_content = encrypt(caesar,huffman,text,offset);
        decryption_file_content = decrypt(caesar, huffman, text, offset);
        analytics_file_content = analyze(caesar, huffman, text, offset);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(encryption_filename, true));
            writer.write(encryption_file_content);
            System.out.println("Encryption File written succesfully! \n");
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(decryption_filename, true));
            writer.write(decryption_file_content);
            System.out.println("Decryption File written succesfully! \n");
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(analytics_filename, true));
            writer.write(analytics_file_content);
            System.out.println("Cripto Analysis File written succesfully! \n");
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
