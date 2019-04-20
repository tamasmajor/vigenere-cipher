package com.major.vigenere;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VigenereCipherTest {

    @Test
    public void canCreateCipherWithKey() {
        VigenereCipher cipher = new VigenereCipher("KEY");
        assertNotNull(cipher);
    }

    @Nested
    @DisplayName("encryption")
    class Encryption {

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
        public void encrypt_keyIsLetterBC_plainTextIsShiftedByOneAndTwoCharsInSuccession() {
            VigenereCipher cipher = new VigenereCipher("BC");
            String cipherText = cipher.encrypt("LEMON");
            assertEquals("MGNQO", cipherText);
        }

        @Test
        public void encrypt_plainTextWithCharsOutsideOfAlphabet_skipsThoseChars() {
            VigenereCipher cipher = new VigenereCipher("BC");
            String cipherText = cipher.encrypt("-LEM ON!-");
            assertEquals("MGNQO", cipherText);
        }

        @Test
        public void encrypt_plainTextWithLowerCaseLetter_cipherIsCaseInsensitive() {
            VigenereCipher cipher = new VigenereCipher("BC");
            String cipherText = cipher.encrypt("leMon");
            assertEquals(cipher.encrypt("LEMON"), cipherText);
        }

        @Test
        public void encrypt_genericText() {
            VigenereCipher cipher = new VigenereCipher("SECRET");
            String cipherText = cipher.encrypt("THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG");
            assertEquals("LLGHYBUODISPFJQONNETUFZXJXJVPTRCFFK", cipherText);
        }
    }

    @Nested
    @DisplayName("decryption")
    class Decryption {

        @Test
        public void decrypt_emptyPlainText_cipherTextIsEmpty() {
            VigenereCipher cipher = new VigenereCipher("SECRET");
            String plainText = cipher.decrypt("");
            assertEquals("", plainText);
        }

        @Test
        public void decrypt_keyIsLetterA_cipherTextEqualsPlainText() {
            VigenereCipher cipher = new VigenereCipher("A");
            String plainText = cipher.decrypt("LEMON");
            assertEquals("LEMON", plainText);
        }

        @Test
        public void decrypt_cipherTextContainLowerCaseLetter_decryptsAsUppercase() {
            VigenereCipher cipher = new VigenereCipher("BC");
            String plainText = cipher.decrypt("leMon");
            assertEquals(cipher.decrypt("LEMON"), plainText);
        }

        @Test
        public void decrypt_cipherTextContainsLettersOutsideAlphabet_throwsException() {
            VigenereCipher cipher = new VigenereCipher("SECRET");
            assertThrows(IllegalArgumentException.class, () -> cipher.decrypt("ILL.EGAL"));
        }

        @Test
        public void decrypt_generalText() {
            VigenereCipher cipher = new VigenereCipher("SEC");
            String plainText = cipher.decrypt("LLGIYKUODJSYFJQPNWETUGZGJXJWPCRCFGK");
            assertEquals("THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG", plainText);
        }
    }

}