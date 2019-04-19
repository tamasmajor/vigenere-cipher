package com.major.vigenere.exception;

public class CipherKey {
    private final String key;
    private final int[] shifting;

    public CipherKey(String key, char[] alphabet) {
        this.key = key;
        this.shifting = calculateShifting(key, alphabet);
    }

    public String getKey() {
        return key;
    }

    public int shiftingAtPosition(int overallPosition) {
        int keyPosition = overallPosition % key.length();
        return shifting[keyPosition];
    }

    private int[] calculateShifting(String key, char[] alphabet) {
        int[] shifting = new int[key.length()];
        for (int i = 0; i < shifting.length; i++) {
            shifting[i] = alphabetIndex(key.charAt(i), alphabet);
        }
        return shifting;
    }

    private int alphabetIndex(char c, char[] alphabet) {
        for (int i = 0; i < alphabet.length; i++) {
            if (c == alphabet[i]) {
                return i;
            }
        }
        throw new IllegalArgumentException("Cipher key must be constructed using chars from the alphabet");
    }
}
