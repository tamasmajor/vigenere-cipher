package com.major.vigenere;

import java.util.HashMap;
import java.util.Map;

public class VigenereCipher {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String key;

    private final Map<Character, Integer> indexByLetter;
    private final char[][] vigenereTable;

    public VigenereCipher(String key) {
        this.key = key;
        this.indexByLetter = calculateLetterIndices();
        this.vigenereTable = constructVigenereTable();
    }

    public String encrypt(String plainText) {
        char[] plain = plainText.toCharArray();
        char[] cipher = new char[plain.length];
        for (int i = 0; i < plain.length; i++) {
            char currentKeyLetter = key.charAt(i % key.length());
            int currentIdx = indexByLetter.get(plain[i]);
            int currentKeyIdx = indexByLetter.get(currentKeyLetter);
            cipher[i] = vigenereTable[currentIdx][currentKeyIdx];
        }
        return String.valueOf(cipher);
    }

    private Map<Character, Integer> calculateLetterIndices() {
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
