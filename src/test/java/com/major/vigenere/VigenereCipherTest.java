package com.major.vigenere;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class VigenereCipherTest {

    @Test
    public void canCreateCipherWithKey() {
        VigenereCipher cipher = new VigenereCipher("KEY");
        assertNotNull(cipher);
    }
    
    @Test
    public void encrypt_emptyPlainText_cipherTextIsEmpty() {
        VigenereCipher cipher = new VigenereCipher("SECRET");
        String cipherText = cipher.encrypt("");
        assertEquals("", cipherText);
    }
    
    @Test
    public void encrypt_keyIsLetterA_cipherTextEqualsPlainText() {
        VigenereCipher cipher = new VigenereCipher("A");
        String cipherText = cipher.encrypt("LEMON");
        assertEquals("LEMON", cipherText);
    }

    @Test
    public void encrypt_keyIsLetterC_plainTextIsShiftedByTwoChars() {
        VigenereCipher cipher = new VigenereCipher("C");
        String cipherText = cipher.encrypt("LEMON");
        assertEquals("NGOQP", cipherText);
    }

    @Test
    public void encrypt_keyIsLetterBC_plainTextIsShiftedByOneAndTwoCharsInSuccession() {
        VigenereCipher cipher = new VigenereCipher("BC");
        String cipherText = cipher.encrypt("LEMON");
        assertEquals("MGNQO ", cipherText);
    }

}