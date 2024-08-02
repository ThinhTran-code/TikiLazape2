/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;


import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author hbtth
 */
public class Hashing {

    private SecretKey key;
    private int KEY_SIZE = 128;
    private int T_LEN = 128;
    private byte[] IV;
    
    public Hashing(){
        initFromStrings("soU2n2NGPaljPb8WtdbuIw==", "Xz1EkfajMufPf09V");
    }
    public void init() {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(KEY_SIZE);
            key = generator.generateKey();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String encrypt_old(String message) throws Exception {
        byte[] messageByte = message.getBytes();
        Cipher encryptCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
        IV = encryptCipher.getIV();
        byte[] encryptByte = encryptCipher.doFinal(messageByte);

        return encode(encryptByte);
    }

    public String encrypt(String message) {
        try {
            byte[] messageByte = message.getBytes();
            Cipher encryptCipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec spec = new GCMParameterSpec(T_LEN, IV);
            encryptCipher.init(Cipher.ENCRYPT_MODE, key,spec);
            byte[] encryptByte = encryptCipher.doFinal(messageByte);
            
            return encode(encryptByte);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String decrypt(String message) {
        try {
            byte[] messageByte = decode(message);
            Cipher decryptCipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec spec = new GCMParameterSpec(T_LEN, IV);
            decryptCipher.init(Cipher.DECRYPT_MODE, key, spec);
            byte[] decryptBytes = decryptCipher.doFinal(messageByte);
            return new String(decryptBytes);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void initFromStrings(String secret, String IV) {
        key = new SecretKeySpec(decode(secret), "AES");
        this.IV = decode(IV);
    }

    public static String toSHA1(String message) throws Exception {
        String salt = "ckdnvjdvlvmxc";
        String result = null;
        byte[] data = message.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        result = Base64.getEncoder().encodeToString(md.digest(data));
        //result =new String(Base64.getDecoder().decode(md.digest()));
        return result;
    }

    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    private void ExportKey() {
        System.out.println("SecretKey: " + encode(key.getEncoded()));
        System.out.println("IV: " + encode(IV));
    }

    public static void main(String[] args) {
        try {
            Hashing h = new Hashing();
            
            String encrypt = h.encrypt("password123@");
            String decrypt = h.decrypt(encrypt);
            System.out.println(encrypt+"\n"+ decrypt);
            h.ExportKey();

        } catch (Exception ex) {
            Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
