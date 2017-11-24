package com.cz.common.util.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by jomalone_jia on 2017/6/30.
 */
public class HashSaltUtil {
    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    // The following constants may be changed without breaking existing hashes.
    public static final int SALT_BYTE_SIZE = 24; //加盐长度
    public static final int HASH_BYTE_SIZE = 24;
    public static final int PBKDF2_ITERATIONS = 1000;

    public static final int ITERATION_INDEX = 0;
    public static final int SALT_INDEX = 1;
    public static final int PBKDF2_INDEX = 2;

    /**
     * Returns a salted PBKDF2 hash of the password.
     *
     * @param   password    the password to hash
     * @return              a salted PBKDF2 hash of the password
     */
    public static String createHash(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        return createHash(password.toCharArray());
    }

    /**
     * Returns a salted PBKDF2 hash of the password.
     *
     * @param   password    the password to hash
     * @return              a salted PBKDF2 hash of the password
     */
    public static String createHash(char[] password)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);

        // Hash the password
        byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);

        // format iterations:salt:hash
        return /*PBKDF2_ITERATIONS + ":" +*/  toHex(hash) + ":" + toHex(salt);
    }

    /**
     * Validates a password using a hash.
     *
     * @param   password        the password to check
     * @param   passwordHash     the hash of the valid password
     * @return                  true if the password is correct, false if not
     */
    public static boolean validatePassword(String password, String passwordHash,String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        return validatePassword(password.toCharArray(), passwordHash,salt);
    }

    /**
     * Validates a password using a hash.
     *
     * @param   password        the password to check
     * @param   passwordHash     the hash of the valid password
     * @return                  true if the password is correct, false if not
     */
    public static boolean validatePassword(char[] password, String passwordHash,String saltStr)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        // Decode the hash into its parameters
       // String[] params = correctHash.split(":");
        //int iterations = Integer.parseInt(params[ITERATION_INDEX]);
        int iterations = 1000;
        byte[] hash = fromHex(passwordHash);
        byte[] salt = fromHex(saltStr);
        // Compute the hash of the provided password, using the same salt,
        // iteration count, and hash length
        byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
        // Compare the hashes in constant time. The password is correct if
        // both hashes match.
        return slowEquals(hash, testHash);
    }



    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param   a       the first byte array
     * @param   b       the second byte array
     * @return          true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b)
    {
        int diff = a.length ^ b.length;
        for(int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    /**
     *  计算密码伪随机
     *  Computes the PBKDF2 hash of a password.
     *
     * @param   password    the password to hash.
     * @param   salt        the salt
     * @param   iterations  the iteration count (slowness factor) 循环次数
     * @param   bytes       the length of the hash to compute in bytes
     * @return              the PBDKF2 hash of the password
     */
    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }

    /**
     * Converts a string of hexadecimal characters into a byte array.
     *
     * @param   hex         the hex string
     * @return              the hex string decoded into a byte array
     */
    private static byte[] fromHex(String hex)
    {
        byte[] binary = new byte[hex.length() / 2];
        for(int i = 0; i < binary.length; i++)
        {
            binary[i] = (byte)Integer.parseInt(hex.substring(2*i, 2*i+2), 16);
        }
        return binary;
    }

    /**
     * Converts a byte array into a hexadecimal string.
     *
     * @param   array       the byte array to convert
     * @return              a length*2 character string encoding the byte array
     */
    private static String toHex(byte[] array)
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }

    /**
     * Tests the basic functionality of the PasswordHash class
     *
     * @param   args        ignored
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
        System.out.println(createHash("123456"));
        System.out.println(validatePassword("123456","f332b59f1777b84c38a8329fa63927db30d5f817f76fdf4b","0c65c06b51f48798b3ba1a45e87dab26216544f91743c6d1"));
    }
}
