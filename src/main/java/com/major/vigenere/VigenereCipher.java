package com.major.vigenere;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class VigenereCipher {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String key;

    private final Map<Character, Integer> indexByAlphabetElement;
    private final char[][] vigenereTable;

    public VigenereCipher(String key) {
        this.key = key;
        this.indexByAlphabetElement = constructIndexByAlphabet();
        this.vigenereTable = constructVigenereTable();
    }

    public String encrypt(String plainText) {
        char[] plain = sanitize(plainText);
        char[] cipher = new char[plain.length];
        for (int i = 0; i < plain.length; i++) {
            char currentKeyChar = key.charAt(i % key.length());
            int alphabetIdxForCurrentPlainItem = indexByAlphabetElement.get(plain[i]);
            int alphabetIdxForCurrentKeyItem = indexByAlphabetElement.get(currentKeyChar);
            cipher[i] = vigenereTable[alphabetIdxForCurrentPlainItem][alphabetIdxForCurrentKeyItem];
        }
        return String.valueOf(cipher);
    }

    private char[] sanitize(String plainText) {
        char[] plain = plainText.toUpperCase().toCharArray();
        char[] sanitized = new char[plain.length];
        int numOfValid = 0;
        for (int i = 0; i < plain.length; i++) {
            if (indexByAlphabetElement.containsKey(plain[i])) {
                sanitized[numOfValid] = plain[i];
                numOfValid++;
            }
        }
        return Arrays.copyOf(sanitized, numOfValid);
    }

    private Map<Character, Integer> constructIndexByAlphabet() {
        Map<Character, Integer> indices = new HashMap<>();
        for (int idx = 0; idx < ALPHABET.length(); idx++) {
            indices.put(ALPHABET.charAt(idx), idx);
        }
        return indices;
    }

    private char[][] constructVigenereTable() {
        int len = ALPHABET.length();
        char[][] vigenere = new char[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                vigenere[i][j] = ALPHABET.charAt((j + i) % len);
            }
        }
        return vigenere;
    }
}
