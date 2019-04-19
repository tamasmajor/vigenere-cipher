package com.major.vigenere.exception;

public class CipherKey {
    private final String key;

    public CipherKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
