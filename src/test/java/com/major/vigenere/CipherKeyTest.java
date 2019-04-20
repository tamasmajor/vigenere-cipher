package com.major.vigenere;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CipherKey")
class CipherKeyTest {

    @Nested
    @DisplayName("Key")
    class GetKeyTests {

        @Test
        public void constructedWithKey_returnsKey() {
            CipherKey cipherKey = new CipherKey("KEY", new char[] { 'E', 'K', 'Y' });
            assertEquals("KEY", cipherKey.getKey());
        }

        @Test
        public void keyContainsLetterOutsideOfAlphabet_throwsException() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new CipherKey("KEY", new char[] { 'K', 'E' }));
        }
    }

    @Nested
    @DisplayName("Shifting calculation")
    class ShiftingCalculationTests {

        @Test
        public void alphabetHasOneLetter_shiftingIsZero() {
            CipherKey cipherKey = new CipherKey("A", new char[] { 'A' });
            assertEquals(0, cipherKey.shiftingAtPosition(0));
        }

        @Nested
        @DisplayName("Alphabet with multiple letters")
        class MultiLetterAlphabet {

            final String key = "EBD";
            final char[] alphabet = new char[] { 'A', 'B', 'C', 'D', 'E' };
            final CipherKey cipherKey = new CipherKey(key, alphabet);

            @Test
            public void overallPositionNotBiggerThanKeyLength_shiftingIsCurrentKeyLettersPositionInAlphabet() {
                assertEquals(4, cipherKey.shiftingAtPosition(0)); // (E)BD
                assertEquals(1, cipherKey.shiftingAtPosition(1)); // E(B)D
                assertEquals(3, cipherKey.shiftingAtPosition(2)); // EB(D)
            }
            
            @Test
            public void overallPositionIsMultipleOfKeyLength_shiftingIsAlwaysPositionOfFirstKeyCharInAlphabet() {
                assertEquals(4, cipherKey.shiftingAtPosition(0)); // (E)BD
                assertEquals(4, cipherKey.shiftingAtPosition(3)); // EBD(E)
                assertEquals(4, cipherKey.shiftingAtPosition(6)); // EBDEBD(E)
            }

            @Test
            public void overallPositionIsBiggerThenKeyLength_usedPositionIsUsingKeyLengthModulo() {
                assertEquals(1, cipherKey.shiftingAtPosition(4));
                assertEquals(3, cipherKey.shiftingAtPosition(11));
            }
        }

    }

}