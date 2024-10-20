package ch.heigvd.dai.algorithm;

import ch.heigvd.dai.mode.Decrypt;

import java.io.*;
import java.lang.System;
import java.nio.charset.StandardCharsets;

public class CodeCesarDecode implements Decrypt {

    @Override
    public void decode(String filename_input, String filename_output, String key){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename_input, StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new FileWriter(filename_output, StandardCharsets.UTF_8))){

            StringBuilder content = new StringBuilder();
            String line;

            // read data form the file line by line
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }

            String encryptText = content.toString();
            encryptText = encryptText.trim();

            int shiftKey = Integer.parseInt(key);

            StringBuilder decryptText = new StringBuilder();

            // loop to decrypt the text char by char
            for (int i = 0; i < encryptText.length(); i++) {
                char currentChar = encryptText.charAt(i);

                if (Character.isLetter(currentChar)) {
                    char base = Character.isUpperCase(currentChar) ? 'A' : 'a'; // Determine base (A or a)

                    char decryptedChar = (char) ((currentChar - base - shiftKey + 26) % 26 + base);
                    decryptText.append(decryptedChar);
                } else {
                    // do nothing for specials characters and numbers
                    decryptText.append(currentChar);
                }
            }

            // write the decrypt text into the file
            writer.write(decryptText.toString());

        } catch (IOException e) {
            System.err.println("Error in decoding CodeCesar -> " + e.getMessage());
        }
    }
}
