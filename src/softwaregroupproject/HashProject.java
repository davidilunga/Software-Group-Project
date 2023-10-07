/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaregroupproject;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
/**
 *
 * @author David Ilunga
 */
public class HashProject {
    
    //program initiates a random hash via the processes
    //program firstly initiates a set of variables 
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~!@#$%^&*()+={}][|\\`,./?;:'\"<>";
    private static final int ITERATIONS = 100;
    private static final int KEY_LENGTH = 512;
    
    //getSalt method generates a 'salt' for the password user has entered
    public static String getSalt(int length){
        StringBuilder returnValue = new StringBuilder(length);
        for(int i = 0; i < length; i++){
                //for each character in the password (RANDOM) a random character noted in the 'ALPHABET' is used to "replace" and generates a salt
                // - essentially 'scrambling' the initial password.

            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
            System.out.println(returnValue);
        }
        System.out.println("Final Salt generated: "+ returnValue); //final salt has been generated (password has been jumped with random characters)
        return new String(returnValue);
    }
    
    public static byte[] hash(char[] password, byte[] salt){
        PBEKeySpec spec = new PBEKeySpec (password, salt,ITERATIONS, KEY_LENGTH); 
            //the password characters are converted to a PBE key by assigning an instanced secret-key factor
            
            Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512"); //Instance key used is SHA512 - a 64-bit arithmetic operation.
            System.out.println("skf: "+skf);
            System.out.println("skf return: "+skf.generateSecret(spec).getEncoded()); 
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: "+e.getMessage(),e); 
        } finally {
            spec.clearPassword();
            System.gc();
        }
    }
    
    public static String generateSecurePassword(String password, String salt){ //scrambled password is generated inclusive of a sha512 encryption + a salt
        String returnValue = null;
        System.out.println("Password: "+password+", salt: "+salt); //continued procedure from here creates a hash by merging a salt + password (PBKDF2 operation)
        
        //below: password hashing is begun - hash converted into 'bytes'
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
        System.out.println("Secure Password: "+securePassword);
        
        //base64 encodes password + saalt (hash)
        returnValue = Base64.getEncoder().encodeToString(securePassword);
        System.out.println("Secure password converted:"+ returnValue);
        return returnValue;
    }
    
    public static boolean verifyUserPassword(String providedPassword, String securedPassword, String salt){ //verification of the hash corresponding to hash storres in the DB.
        boolean returnValue = false;
        String newSecurePassword = generateSecurePassword(providedPassword, salt);
        returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);
        return returnValue;
    }
    /**
     * @param args the command line arguments
     *
    public static void main(String[] args) {
        // TODO code application logic here
        
        String slt = getSalt(30);
        System.out.println("Salt: "+slt);
        String password = generateSecurePassword("people12", slt);
        System.out.println("Password Salted: " + password);
        boolean valid = verifyUserPassword("people12", password, slt);    
        System.out.println("Valid: "+valid);
    }
    */
}
