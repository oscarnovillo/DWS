/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author oscar
 */
public class Encriptacion {

    public byte[] cifrar(String key, String sinCifrar) throws Exception {
        final byte[] bytes = sinCifrar.getBytes("UTF-8");
        final Cipher aes = obtieneCipher(key,true);
        final byte[] cifrado = aes.doFinal(bytes);
        return cifrado;
    }

    public String descifrar(String key,byte[] cifrado) throws Exception {
        final Cipher aes = obtieneCipher(key,false);
        final byte[] bytes = aes.doFinal(cifrado);
        final String sinCifrar = new String(bytes, "UTF-8");
        return sinCifrar;
    }
    private static Cipher obtieneCipher(String clave,boolean paraCifrar) throws Exception {

        final MessageDigest digest = 
                MessageDigest.getInstance("SHA-1");
        digest.update(clave.getBytes("UTF-8"));
        final SecretKeySpec key = new SecretKeySpec(
                digest.digest(), 0, 16, "AES");

        final Cipher aes = Cipher.getInstance(
                "AES/ECB/PKCS5Padding");
        if (paraCifrar) {
            aes.init(Cipher.ENCRYPT_MODE, key);
        } else {
            aes.init(Cipher.DECRYPT_MODE, key);
        }

        return aes;
    }
}
