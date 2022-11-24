package jpass.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

	private static Stream<Arguments> provideArgumentsForCreateFormatter() {
        return Stream.of(
          Arguments.of((Object)TEXT_TO_ENCRYPT_VALID, (Object)TEXT_TO_ENCRYPT_VALID_EXPECTED),
          Arguments.of((Object)TEXT_TO_ENCRYPT_EMPTY, (Object)TEXT_TO_ENCRYPT_EMPTY_EXPECTED),
          Arguments.of((Object)TEXT_TO_ENCRYPT_NULL, (Object) TEXT_TO_ENCRYPT_NULL_EXPECTED)
        );
    }
    



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



    @ParameterizedTest
    @MethodSource("argumentsTestGetSha256HashAssignment8")
    void testGetSha256HashAssignment8(char[] text) throws Exception {
        byte[] encryptedText = CryptUtils.getPKCS5Sha256Hash(text);
        byte[] expected = shaOneThousand(new String(text), 1000);
        String encryptedTextAsString = bytesToHex(encryptedText);
        String expectedAsString = bytesToHex(expected);
        assertEquals(encryptedTextAsString, expectedAsString);
    }

    private byte[] shaOneThousand(String text, int iteration) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] result = digest.digest(text.getBytes(StandardCharsets.UTF_8));
        for(int i = 0; i < iteration; i++) {
            result = digest.digest(result);
        }
        return result;
    }

    private static Stream<Arguments> argumentsTestGetSha256HashAssignment8() {
        return Stream.of(
                Arguments.of( new char[] {'v','a','l','i','d'}),
                Arguments.of(new char[] {})
        );
    }

}
