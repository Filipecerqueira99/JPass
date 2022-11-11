package jpass.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CryptUtilsTest {

	private final static char[] TEXT_TO_ENCRYPT_VALID = new char[] {'v','a','l','i','d'};
	private final static String TEXT_TO_ENCRYPT_VALID_EXPECTED = "ec654fac9599f62e79e2706abef23dfb7c07c08185aa86db4d8695f0b718d1b3";
	private final static char[] TEXT_TO_ENCRYPT_EMPTY = new char[] {};
	private final static String TEXT_TO_ENCRYPT_EMPTY_EXPECTED = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
	private final static char[] TEXT_TO_ENCRYPT_NULL = null;
	private final static String TEXT_TO_ENCRYPT_NULL_EXPECTED = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

    @ParameterizedTest
    @MethodSource("provideArgumentsForCreateFormatter")
	void testGetSha256Hash(char[] text, String expectedSha256Hash) throws Exception {
    	byte[] encryptedText = CryptUtils.getSha256Hash(text);
    	String encryptedTextAsString = bytesToHex(encryptedText);
    	assertEquals(expectedSha256Hash, encryptedTextAsString);
	}
    
    @SuppressWarnings("unused")
	private static Stream<Arguments> provideArgumentsForCreateFormatter() {
        return Stream.of(
          Arguments.of((Object)TEXT_TO_ENCRYPT_VALID, (Object)TEXT_TO_ENCRYPT_VALID_EXPECTED),
          Arguments.of((Object)TEXT_TO_ENCRYPT_EMPTY, (Object)TEXT_TO_ENCRYPT_EMPTY_EXPECTED),
          Arguments.of((Object)TEXT_TO_ENCRYPT_NULL, (Object) TEXT_TO_ENCRYPT_NULL_EXPECTED)
        );
    }
    
    @SuppressWarnings("unused")
	private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
