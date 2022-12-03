package jpass.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;


class StringUtilTest {

	private final String TEXT_HIGHER_80 = "ThisTextHasMoreThan80Characters-ThisTextHasMoreThan80Characters-ThisTextHasMoreThan80Characters";
	private final String TEXT_HIGHER_80_EXPECTED_RESULT = "ThisTextHasMoreThan80Characters-ThisTextHasMoreThan80Characters-ThisTextHasMoreT...";
	private final String TEXT_EXACTLY_80 = "ThisTextExactly80Characters-ThisTextExactly80Characters-ThisTextExactly80Charact";
	private final String TEXT_EXACTLY_80_EXPECTED_RESULT = "ThisTextExactly80Characters-ThisTextExactly80Characters-ThisTextExactly80Charact";
	private final String TEXT_LESS_THAN_80 = "ThisTextLessThan80Characters";
	private final String TEXT_LESS_THAN_80_EXPECTED_RESULT = "ThisTextLessThan80Characters";
	private final String TEXT_EMPTY = "";
	private final String TEXT_EMPTY_EXPECTED_RESULT = "";
	private final String TEXT_EXACTLY_81 = "ThisTextExactly80Characters-ThisTextExactly80Characters-ThisTextExactly80Characte"; // Missing test for boundary value analysis
	private final String TEXT_EXACTLY_81_EXPECTED_RESULT = "ThisTextExactly80Characters-ThisTextExactly80Characters-ThisTextExactly80Charact..."; // Missing test for boundary value analysis

    @ParameterizedTest
    @CsvSource({
    	TEXT_HIGHER_80 + "," +  TEXT_HIGHER_80_EXPECTED_RESULT,
    	TEXT_EXACTLY_80 + "," + TEXT_EXACTLY_80_EXPECTED_RESULT,
    	TEXT_LESS_THAN_80 + "," + TEXT_LESS_THAN_80_EXPECTED_RESULT,
    	TEXT_EMPTY + "," + TEXT_EMPTY_EXPECTED_RESULT,
		TEXT_EXACTLY_81 + "," + TEXT_EXACTLY_81_EXPECTED_RESULT // Boundary value analysis missing test
    })
	void testStripString(String input, String expectedValue) {
    	String strippedText = StringUtils.stripString(input);
		assertEquals(strippedText, expectedValue);
	}
    
    @ParameterizedTest
    @NullSource
	void testStripStringNullInput(String input) {
		NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
			StringUtils.stripString(input);
		}, "NullPointerException was expected");
		
		Assertions.assertEquals("For input string: null ", thrown.getMessage());
	}

	private static final String XML_EMPTY = "";
	private static final String XML_EMPTY_EXPECTED_RESULT = "";
	private static final String XML_ONLY_VALID_CHARACTERS = "valid";
	private static final String XML_ONLY_VALID_CHARACTERS_EXPECTED = "valid";
	private static final String XML_ONLY_INVALID_CHARACTERS = new String(new char[]{0x5,0x5});
	private static final String XML_ONLY_INVALID_CHARACTERS_EXPECTED = "??";
	private static final String XML_BOTH_VALID_INVALID_CHARACTERS = new String(new char[]{0x5,0x5,0x20});
	private static final String XML_BOTH_VALID_INVALID_CHARACTERS_EXPECTED = "??a";
	private static final String XML_NULL = null;
	private static final String XML_NULL_RESULT = null;

	// BOUNDARY VALUE ANALYSIS
	private static final String XML_ON_POINT1 = new String(new char[]{0x8});
	private static final String XML_ON_POINT1_EXPECTED = new String(new char[]{0x8});
	private static final String XML_OFF_POINT1 = new String(new char[]{0x9});
	private static final String XML_OFF_POINT1_EXPECTED = new String(new char[]{0x9});
	private static final String XML_OFF_POINT2 = new String(new char[]{0xA});
	private static final String XML_OFF_POINT2_EXPECTED = new String(new char[]{0xA});
	private static final String XML_ON_POINT2 = new String(new char[]{0xB});
	private static final String XML_ON_POINT2_EXPECTED = new String(new char[]{0xB});
	private static final String XML_ON_POINT3 = new String(new char[]{0xC});
	private static final String XML_ON_POINT3_EXPECTED = new String(new char[]{0xC});
	private static final String XML_OFF_POINT3 = new String(new char[]{0xD});
	private static final String XML_OFF_POINT3_EXPECTED = new String(new char[]{0xD});
	private static final String XML_ON_POINT4 = new String(new char[]{0xE});
	private static final String XML_ON_POINT4_EXPECTED = new String(new char[]{0xD});
	private static final String XML_ON_POINT5 = new String(new char[]{0x1F});
	private static final String XML_ON_POINT5_EXPECTED = new String(new char[]{0x1F});
	private static final String XML_OFF_POINT5 = new String(new char[]{0x20});
	private static final String XML_OFF_POINT5_EXPECTED = new String(new char[]{0x20});
	private static final String XML_OFF_POINT6 = new String(new char[]{0xD7FF});
	private static final String XML_OFF_POINT6_EXPECTED = new String(new char[]{0xD7FF});
	private static final String XML_ON_POINT6 = new String(new char[]{0xD800});
	private static final String XML_ON_POINT6_EXPECTED = new String(new char[]{0xD800});
	private static final String XML_ON_POINT7 = new String(new char[]{0xDFF});
	private static final String XML_ON_POINT7_EXPECTED = new String(new char[]{0xDFF});
	private static final String XML_OFF_POINT7 = new String(new char[]{0xE000});
	private static final String XML_OFF_POINT7_EXPECTED = new String(new char[]{0xE000});
	private static final String XML_OFF_POINT8 = new String(new char[]{0xFFFD});
	private static final String XML_OFF_POINT8_EXPECTED = new String(new char[]{0xFFFD});
	private static final String XML_ON_POINT8 = new String(new char[]{0xFFFE});
	private static final String XML_ON_POINT8_EXPECTED = new String(new char[]{0xFFFE});
	private static final String XML_ON_POINT9 = new String(new char[]{0xFFFF});
	private static final String XML_ON_POINT9_EXPECTED = new String(new char[]{0xFFFF});
	private static final String XML_OFF_POINT9 = new String(new char[]{10000});
	private static final String XML_OFF_POINT9_EXPECTED = new String(new char[]{10000});
	/*private static final String XML_OFF_POINT10 = new String(new char[]{0010FFFF});
	private static final String XML_OFF_POINT10_EXPECTED = new String(new char[]{0010FFFF});
	private static final String XML_ON_POINT10 = new String(new char[]{0x1100000});
	private static final String XML_ON_POINT10_EXPECTED = new String(new char[]{110000});*/

    @ParameterizedTest
    @MethodSource("provideArgumentsForStripNonValidXMLCharacters")
    void testStripNonValidXMLCharacters(String xmlToStrip, String expectedResult) {
    	String result = StringUtils.stripNonValidXMLCharacters(xmlToStrip);
    	assertEquals(expectedResult, result);
    }
    
    @SuppressWarnings("unused")
	private static Stream<Arguments> provideArgumentsForStripNonValidXMLCharacters() {
        return Stream.of(
			  Arguments.of(XML_EMPTY, XML_EMPTY_EXPECTED_RESULT),
			  Arguments.of(XML_ONLY_VALID_CHARACTERS, XML_ONLY_VALID_CHARACTERS_EXPECTED),
			  Arguments.of(XML_ONLY_INVALID_CHARACTERS,  XML_ONLY_INVALID_CHARACTERS_EXPECTED),
			  Arguments.of(XML_BOTH_VALID_INVALID_CHARACTERS,  XML_BOTH_VALID_INVALID_CHARACTERS_EXPECTED),
			  Arguments.of(XML_NULL,  XML_NULL_RESULT),
			  // BOUNDARY VALUE ANALYSIS TEST
			  Arguments.of(XML_ON_POINT1, XML_ON_POINT1_EXPECTED),
			  Arguments.of(XML_OFF_POINT1 , XML_OFF_POINT1_EXPECTED),
			  Arguments.of(XML_OFF_POINT2, XML_OFF_POINT2_EXPECTED),
			  Arguments.of(XML_ON_POINT2, XML_ON_POINT2_EXPECTED),
			  Arguments.of(XML_ON_POINT3, XML_ON_POINT3_EXPECTED),
			  Arguments.of(XML_OFF_POINT3, XML_OFF_POINT3_EXPECTED),
			  Arguments.of(XML_ON_POINT4, XML_ON_POINT4_EXPECTED),
			  Arguments.of(XML_ON_POINT5, XML_ON_POINT5_EXPECTED),
			  Arguments.of(XML_OFF_POINT5, XML_OFF_POINT5_EXPECTED),
				Arguments.of(XML_OFF_POINT6, XML_OFF_POINT6_EXPECTED),
				Arguments.of(XML_ON_POINT6, XML_ON_POINT6_EXPECTED),
				Arguments.of(XML_ON_POINT7, XML_ON_POINT7_EXPECTED),
				Arguments.of(XML_OFF_POINT7, XML_OFF_POINT7_EXPECTED),
				Arguments.of(XML_OFF_POINT8, XML_OFF_POINT8_EXPECTED),
				Arguments.of(XML_ON_POINT8, XML_ON_POINT8_EXPECTED),
				Arguments.of(XML_ON_POINT9, XML_ON_POINT9_EXPECTED),
				Arguments.of(XML_OFF_POINT9, XML_OFF_POINT9_EXPECTED)
        );
    }
    
    @ParameterizedTest
    @CsvSource({
    	TEXT_HIGHER_80 + "," +  TEXT_HIGHER_80_EXPECTED_RESULT,
    	TEXT_LESS_THAN_80 + "," + TEXT_LESS_THAN_80_EXPECTED_RESULT,
    })
	void testDataFlowStripStringProvidingLengthNotNullText(String input, String expectedValue) {
    	String strippedText = StringUtils.stripString(input,80);
		assertEquals(strippedText, expectedValue);
	}
    
    @Test
	void testDataFlowStripStringProvidingLengthNullText() {
    	String strippedText = StringUtils.stripString(null,80);
		assertEquals(strippedText, null);
	}
}

