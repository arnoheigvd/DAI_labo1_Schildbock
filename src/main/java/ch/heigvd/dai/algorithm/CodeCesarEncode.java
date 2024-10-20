package ch.heigvd.dai.algorithm;

import ch.heigvd.dai.mode.Encrypt;

import java.io.*;
import java.lang.System;
import java.nio.charset.StandardCharsets;

public class CodeCesarEncode implements Encrypt {

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
            plainText = plainText.trim();

            int shiftKey = Integer.parseInt(key);

            StringBuilder encryptText = new StringBuilder();

            // loop to encrypt the text char by char
            for (int i = 0; i < plainText.length(); i++) {
                char currentChar = plainText.charAt(i);

                if (Character.isLetter(currentChar)) {
                    char base = Character.isUpperCase(currentChar) ? 'A' : 'a'; // Determine base (A or a)

                    char encryptedChar = (char) ((currentChar - base + shiftKey) % 26 + base);
                    encryptText.append(encryptedChar);
                } else {
                    // do nothing for specials characters and numbers
                    encryptText.append(currentChar);
                }
            }

            // write the encrypt text into the file
            writer.write(encryptText.toString());

        } catch (IOException e) {
            System.err.println("Error in encoding CodeCesar -> " + e.getMessage());
        }
    }
}
