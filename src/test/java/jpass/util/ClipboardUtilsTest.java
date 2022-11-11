package jpass.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ClipboardUtilsTest {

	private final static String CLIPBOARD_TEXT_VALID = "Valid Text";
	private final static String CLIPBOARD_TEXT_VALID_EXPECTED = CLIPBOARD_TEXT_VALID;
	private final static String CLIPBOARD_TEXT_EMPTY = "";
	private final static String CLIPBOARD_TEXT_EMPTY_EXPECTED = "";
	private final static String CLIPBOARD_TEXT_NULL = null;
	private final static String CLIPBOARD_TEXT_NULL_EXPECTED = "";
	private final static String CLIPBOARD_TEXT_EXACTLY_1_CHARACTER = "1";
	private final static String CLIPBOARD_TEXT_EXACTLY_1_CHARACTER_EXPECTED_RESULT = "1";


    @ParameterizedTest
    @MethodSource("provideArgumentsForSetClipboardContent")
	void testSetClipboardContent(String inputText, String expectedContent) throws Exception {
		ClipboardUtils.setClipboardContent(inputText);
		String content = ClipboardUtils.getClipboardContent();
		
		assertEquals(content, expectedContent);
	}
    
    @SuppressWarnings("unused")
   	private static Stream<Arguments> provideArgumentsForSetClipboardContent() {
           return Stream.of(
             Arguments.of(CLIPBOARD_TEXT_VALID, CLIPBOARD_TEXT_VALID_EXPECTED),
             Arguments.of(CLIPBOARD_TEXT_EMPTY, CLIPBOARD_TEXT_EMPTY_EXPECTED),
             Arguments.of(CLIPBOARD_TEXT_NULL, CLIPBOARD_TEXT_NULL_EXPECTED),
				   Arguments.of(CLIPBOARD_TEXT_EXACTLY_1_CHARACTER, CLIPBOARD_TEXT_EXACTLY_1_CHARACTER_EXPECTED_RESULT)
           );
	}


}
