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
        this.alphabetPositionByLetter = constructLetterPositionInAlphabetMapping();
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
        char[] cipher = cipherText.toUpperCase().toCharArray();
        char[] plain = new char[cipher.length];
        for (int i = 0; i < cipher.length; i++) {
            plain[i] = decryptLetter(cipher[i], i);
        }
        return String.valueOf(plain);
    }

    private char encryptLetter(char plainLetter, int overallPosition) {
        int plainLetterAlphabetIndex = alphabetPositionByLetter.get(plainLetter);
        int shifting = cipherKey.shiftingAtPosition(overallPosition);
        int shiftedPosition = (plainLetterAlphabetIndex + shifting) % alphabet.length;
        return alphabet[shiftedPosition];
    }

    private char decryptLetter(char cipherLetter, int overallPosition) {
        if (alphabetPositionByLetter.containsKey(cipherLetter)) {
            int cipherLetterAlphabetIndex = alphabetPositionByLetter.get(cipherLetter);
            int shifting = cipherKey.shiftingAtPosition(overallPosition);
            int shiftedPosition = Math.floorMod(cipherLetterAlphabetIndex - shifting, alphabet.length);
            return alphabet[shiftedPosition];
        } else {
            throw new IllegalArgumentException("Ciphertext should contain letters only from the alphabet");
        }
    }

    /**
     * Removes the characters from the plain text which are not part of the alphabet
     * @param  plainText the original, plain text
     * @return           the plain text as uppercase and without the letters outside of the alphabet
     */
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

    private Map<Character, Integer> constructLetterPositionInAlphabetMapping() {
        Map<Character, Integer> indices = new HashMap<>();
        for (int idx = 0; idx < alphabet.length; idx++) {
            indices.put(alphabet[idx], idx);
        }
        return indices;
    }
}
