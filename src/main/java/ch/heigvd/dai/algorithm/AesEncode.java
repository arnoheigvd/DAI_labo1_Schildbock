package ch.heigvd.dai.algorithm;

import ch.heigvd.dai.mode.Encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.MessageDigest;

public class AesEncode implements Encrypt {

    private static final String ALGORITHM_KEY = "SHA-256";
    private static final String ALGORITHM_CIPHER = "AES";
    private static final String MODE = "AES/ECB/PKCS5Padding";
    private static final int BUFFER_SIZE = 16; // custom chosen buffer size of 16 bytes

    @Override
    public void encode(String filename_input, String filename_output, String password) {
        SecretKeySpec key;
        Cipher cipher = null;
        try {

            //Generate a 256-bit key using SHA-256 and create the hashing tool
            MessageDigest sha = MessageDigest.getInstance(ALGORITHM_KEY);

            // Hash the password (digest) and create the key
            key = new SecretKeySpec(sha.digest(password.getBytes()), ALGORITHM_CIPHER);

            // Creation et initialisation of the cypher mode AES-ECB
            cipher = Cipher.getInstance(MODE);

            // We want to encrypt
            cipher.init(Cipher.ENCRYPT_MODE, key);

        } catch (Exception e) {
            System.err.println("Error in instancing the cypher AES for encode -> " + e.getMessage());
        }
        // Read ciphertext and write the decrypted plaintext
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filename_input));
             BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filename_output))) {

            byte[] tmp = new byte[BUFFER_SIZE];
            int b;

            while ((b = inputStream.read(tmp)) != -1) {
                // Encrypt (cipher.update) and write the output
                outputStream.write(cipher.update(tmp, 0, b));
            }

            // Final encryption step (adding padding, etc.)
            outputStream.write(cipher.doFinal());

        } catch (Exception e) {
            System.err.println("Error in reading/writing files for AES encode -> " + e.getMessage());
        }
    }
}
