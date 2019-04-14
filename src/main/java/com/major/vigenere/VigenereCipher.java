package com.major.vigenere;

import java.util.HashMap;
import java.util.Map;

public class VigenereCipher {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String key;

    private final Map<Character, Integer> indexByLetter;
    private final Map<Integer, Character> letterByIndex;

    public VigenereCipher(String key) {
        this.key = key;
        this.indexByLetter = calculateLetterIndices();
        this.letterByIndex = calculateLetterByIndex();
    }

    public String encrypt(String plainText) {
        char[] plain = plainText.toCharArray();
        char[] cipher = new char[plain.length];
        int shift = indexByLetter.get(key.charAt(0));
        for (int i = 0; i < plain.length; i++) {
            int currentIdx = indexByLetter.get(plain[i]);
            int shiftedIdx = (currentIdx + shift) % ALPHABET.length();
            cipher[i] = letterByIndex.get(shiftedIdx);
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


    private Map<Integer, Character> calculateLetterByIndex() {
        Map<Integer, Character> letters = new HashMap<>();
        for (int idx = 0; idx < ALPHABET.length(); idx++) {
            letters.put(idx, ALPHABET.charAt(idx));
        }
        return letters;
    }
}
