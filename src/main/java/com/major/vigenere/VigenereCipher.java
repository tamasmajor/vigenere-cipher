package com.major.vigenere;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class VigenereCipher {
    private static final String DEFAULT_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final char[] alphabet;
    private final CipherKey cipherKey;
    private final Map<Character, Integer> alphabetPositionByLetter;

    public VigenereCipher(String key) {
        this(key, DEFAULT_ALPHABET.toCharArray()); // TODO: Make it possible to provide custom alphabet
    }

    private VigenereCipher(String key, char[] alphabet) {
        this.alphabet = alphabet;
        this.cipherKey = new CipherKey(key, alphabet);
        this.alphabetPositionByLetter = constructIndexByAlphabet();
    }

    public String encrypt(String plainText) {
        char[] plain = sanitize(plainText);
        char[] cipher = new char[plain.length];
        for (int i = 0; i < plain.length; i++) {
            cipher[i] = encryptLetter(plain[i], i);
        }
        return String.valueOf(cipher);
    }

    public String decrypt(String cipherText) {
        return "";
    }

    private char encryptLetter(char plainLetter, int overallPosition) {
        int plainLetterAlphabetIndex = alphabetPositionByLetter.get(plainLetter);
        int shifting = cipherKey.shiftingAtPosition(overallPosition);
        int shiftedPosition = (plainLetterAlphabetIndex + shifting) % alphabet.length;
        return alphabet[shiftedPosition];
    }

    private char[] sanitize(String plainText) {
        char[] plain = plainText.toUpperCase().toCharArray();
        char[] sanitized = new char[plain.length];
        int numOfValid = 0;
        for (int i = 0; i < plain.length; i++) {
            if (alphabetPositionByLetter.containsKey(plain[i])) {
                sanitized[numOfValid] = plain[i];
                numOfValid++;
            }
        }
        return Arrays.copyOf(sanitized, numOfValid);
    }

    private Map<Character, Integer> constructIndexByAlphabet() {
        Map<Character, Integer> indices = new HashMap<>();
        for (int idx = 0; idx < alphabet.length; idx++) {
            indices.put(alphabet[idx], idx);
        }
        return indices;
    }
}
