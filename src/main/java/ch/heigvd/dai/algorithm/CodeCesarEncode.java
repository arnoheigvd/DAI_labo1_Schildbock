package ch.heigvd.dai.algorithm;

import ch.heigvd.dai.mode.Encrypt;

import java.io.*;
import java.lang.System;
import java.nio.charset.StandardCharsets;

public class CodeCesarEncode implements Encrypt {

    // a string that contains the alphabet used for the code cesar
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public void encode(String filename_input, String filename_output, String key){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename_input, StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new FileWriter(filename_output, StandardCharsets.UTF_8))){

            StringBuilder content = new StringBuilder();
            String line;

            // read data form the file line by line
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }

            String plainText = content.toString();
            plainText = plainText.toLowerCase().trim();

            int shiftKey = Integer.parseInt(key);

            // loop to encrypt the text char by char
            String encryptText = "";
            for (int i = 0; i < plainText.length(); i++) {
                int pos = ALPHABET.indexOf(plainText.charAt(i));

                int encryptPos = (shiftKey + pos) % 26;
                char encryptChar = ALPHABET.charAt(encryptPos);

                encryptText += encryptChar;
            }

            // write the string into the file
            writer.write(encryptText);

        } catch (IOException e) {
            System.err.println("Error in encoding CodeCesar -> " + e.getMessage());
        }
    }
}
