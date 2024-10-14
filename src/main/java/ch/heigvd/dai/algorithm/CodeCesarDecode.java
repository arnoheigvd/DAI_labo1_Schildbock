package ch.heigvd.dai.algorithm;

import ch.heigvd.dai.mode.Decrypt;

import java.io.*;
import java.lang.System;
import java.nio.charset.StandardCharsets;

public class CodeCesarDecode implements Decrypt {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public void decode(String filename_input, String filename_output, String key){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename_input, StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new FileWriter(filename_output, StandardCharsets.UTF_8))){

            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }

            String encryptText = content.toString();
            encryptText = encryptText.toLowerCase().trim();

            int shiftKey = Integer.parseInt(key);

            String decryptStr = "";
            for (int i = 0; i < encryptText.length(); i++) {
                int pos = ALPHABET.indexOf(encryptText.charAt(i));

                int decryptPos = (pos - shiftKey) % 26;

                if (decryptPos < 0){
                    decryptPos = ALPHABET.length() + decryptPos;
                }
                char decryptChar = ALPHABET.charAt(decryptPos);

                decryptStr += decryptChar;
            }
            writer.write(decryptStr);

        } catch (IOException e) {
            System.err.println("Error in decoding CodeCesar -> " + e.getMessage());
        }
    }
}
