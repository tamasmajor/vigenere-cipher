package com.major.vigenere.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CipherKeyTest {

    @Test
    public void getKey_constructedWithKey_returnsKey() {
        CipherKey cipherKey = new CipherKey("CIPHERKEY", new char[0]);
        assertEquals("CIPHERKEY", cipherKey.getKey());
    }

}