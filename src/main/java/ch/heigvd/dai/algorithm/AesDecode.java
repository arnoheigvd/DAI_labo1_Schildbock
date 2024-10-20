package ch.heigvd.dai.algorithm;

import ch.heigvd.dai.mode.Decrypt;
import javax.crypto.Cipher; // Cipher class to encrypt
import javax.crypto.spec.SecretKeySpec; // Key to interact with Cipher
import java.io.*;
import java.security.MessageDigest; // SHA-256

public class AesDecode implements Decrypt {
    /* Description de :
     * Algorithme de hashage (cle)
     * Algorithme de chiffrement (fichier)
     * Mode de chiffrement
     * Taille du buffer (pour le chiffrement)
     * */
    private static final String ALGORITHM_KEY = "SHA-256";
    private static final String ALGORITHM_CIPHER = "AES";
    private static final String MODE = "AES/ECB/PKCS5Padding";
    /* Peut valoir ce qu'on veut, j'ai choisi 16 octects pour représenter un bloc de données
     * Probablement un peu plus lent
     * */
    private static final int BUFFER_SIZE = 16;

    @Override
    public void decode(String filename_input, String filename_output, String password) {
        SecretKeySpec key;
        Cipher cipher = null;
        try {

            /* Generation d'une cle 256bits a l'aide de SHA-256 */
            /* Creation de l'outil de hashage */
            MessageDigest sha = MessageDigest.getInstance(ALGORITHM_KEY);
            /* Hachage du mot de passe (digest) et creation de la cle */
            key = new SecretKeySpec(sha.digest(password.getBytes()), ALGORITHM_CIPHER);
            /* Creation et initialisation du chiffreur AES-ECB */
            cipher = Cipher.getInstance(MODE);
            cipher.init(Cipher.DECRYPT_MODE, key);  // Seul vrai changement entre encrypt et decrypt

        } catch (Exception e) { System.err.println("Error in encoding AES -> " + e.getMessage()); }
        /* Lecture clear text et ecriture cipher text*/
        try (BufferedInputStream inputStream =
                     new BufferedInputStream(new FileInputStream(filename_input));
             BufferedOutputStream outputStream =
                     new BufferedOutputStream(new FileOutputStream(filename_output))) {

            byte[] tmp = new byte[BUFFER_SIZE];
            int b;

            while ((b = inputStream.read(tmp)) != -1) {
                /* Chiffrement (cipher.update) et ecriture */
                outputStream.write(cipher.update(tmp, 0, b));
            }

            /* Chiffrement de la fin (ajout du padding, etc)*/
            outputStream.write(cipher.doFinal());

        } catch (Exception e) { System.err.println("Error in encoding AES -> " + e.getMessage()); }

    }
}
