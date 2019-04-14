package com.major.vigenere;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class VigenereCipherTest {

    @Test
    public void canCreateCipherWithKey() {
        VigenereCipher cipher = new VigenereCipher("KEY");
        assertNotNull(cipher);
    }

}