package jpass.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.io.IOException;
import java.util.stream.Stream;

import org.junit.After;
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

	// new tests

	@ParameterizedTest
	@MethodSource("StringClipboard")
	public void TestSetClipboardContent(String value) throws IOException {

		try {
			ClipboardUtils.setClipboardContent(value);
			String cliboardContent = ClipboardUtils.getClipboardContent();
			System.out.println(cliboardContent);
			assertEquals(value, cliboardContent);
		}
		catch(Exception e) {
			System.out.println(e);
			//  Block of code to handle errors
		}
	}
	@After
	public void afterTestSetClipboardContent() throws Exception{
		ClipboardUtils.clearClipboardContent();
	}
	private static Stream<Arguments> StringClipboard() {
		return Stream.of(
				arguments("valueRandomOne"),
				arguments("valueRandomSecond"),
				arguments("valueRandomThird")
		);
	}

}
