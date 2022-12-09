package jpass.util;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DateUtilsTest {
	private final static String FORMATTER_VALID_DD_MM_YY = "dd-MM-yyyy";
	private final static DateTimeFormatter FORMATTER_VALID_DD_MM_YY_EXPECTED = DateTimeFormatter.ofPattern(FORMATTER_VALID_DD_MM_YY);
	private final static String FORMATTER_INVALID = "NotAValidFormatter";
	private final static DateTimeFormatter FORMATTER_INVALID_EXPECTED = DateTimeFormatter.ISO_DATE;
	private final static String FORMATTER_EMPTY = "";
	private final static DateTimeFormatter FORMATTER_EMPTY_EXPECTED = DateTimeFormatter.ofPattern(FORMATTER_EMPTY);
	private final static String FORMATTER_NULL = null;
	private final static DateTimeFormatter FORMATTER_NULL_EXPECTED = DateTimeFormatter.ISO_DATE;

	private final static String FORMATTER_VALID_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy/MM/dd HH:mm:ss.SSS";
	private final static DateTimeFormatter FORMATTER_VALID_YYYY_MM_DD_HH_MM_SS_SSS_EXPECTED = DateTimeFormatter.ofPattern(FORMATTER_VALID_YYYY_MM_DD_HH_MM_SS_SSS);
	
	private final static String DATE_TO_TEST_FORMATTERS = LocalDateTime.now()
            .truncatedTo(ChronoUnit.SECONDS)
            .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	
    @ParameterizedTest
    @MethodSource("provideArgumentsForCreateFormatter")
	void testCreateFormatter(String inputFormat, DateTimeFormatter expectedFormatter) {
		System.out.println(inputFormat);
		System.out.println(expectedFormatter);
    	DateTimeFormatter createdFormatter = DateUtils.createFormatter(inputFormat);
    	String isoDateFormaterdCreatedFormatter="";
    	isoDateFormaterdCreatedFormatter = DateUtils.formatIsoDateTime(DATE_TO_TEST_FORMATTERS,createdFormatter);
    	String isoDateFormaterdExpectedFormatter = DateUtils.formatIsoDateTime(DATE_TO_TEST_FORMATTERS,expectedFormatter);
    	assertEquals(isoDateFormaterdCreatedFormatter,isoDateFormaterdExpectedFormatter);
	}

    @SuppressWarnings("unused")
	private static Stream<Arguments> provideArgumentsForCreateFormatter() {
        return Stream.of(
          Arguments.of(FORMATTER_VALID_DD_MM_YY, FORMATTER_VALID_DD_MM_YY_EXPECTED),
          Arguments.of(FORMATTER_INVALID, FORMATTER_INVALID_EXPECTED),
          Arguments.of(FORMATTER_EMPTY, FORMATTER_EMPTY_EXPECTED),
          Arguments.of(FORMATTER_NULL, FORMATTER_NULL_EXPECTED),
          Arguments.of(FORMATTER_VALID_YYYY_MM_DD_HH_MM_SS_SSS,FORMATTER_VALID_YYYY_MM_DD_HH_MM_SS_SSS_EXPECTED)
        );
    }

	@ParameterizedTest
	@MethodSource("provideArgumentsForformatIsoDateTimeOne")//tests the method FormatIsoDateTime more expecially on the second try
	void testFormatIsoDateTimeOne(String expectedFormat, String dateMilliseconds, String expectedDate) {
		DateTimeFormatter createdFormatter = DateUtils.createFormatter(expectedFormat);

		String isoDateFormaterdCreatedFormatter = DateUtils.formatIsoDateTime(dateMilliseconds, createdFormatter);

		System.out.println(isoDateFormaterdCreatedFormatter);
		System.out.println(expectedDate);

		assertEquals(isoDateFormaterdCreatedFormatter,expectedDate);
	}
	private static Stream<Arguments> provideArgumentsForformatIsoDateTimeOne() {
		return Stream.of(
				//1튷nput: structure, 2튷nput: date in miliseconds, 3튷nput: date formated
				Arguments.of("yyyy/MM/dd", "96423478300", "1973/01/21"),
				Arguments.of("yyyy/MM/dd", "1287224000", "1970/01/15"),
				Arguments.of("yyyy/MM/dd", "1234567891234", "2009/02/13")
		);
	}

	@ParameterizedTest
	@MethodSource("provideArgumentsForformatIsoDateTimeTwo")//tests the method FormatIsoDateTime more expecially on the second catch
	void testFormatIsoDateTimeTwo(String expectedFormat, String wrongDateFormat) {
		DateTimeFormatter createdFormatter = DateUtils.createFormatter(expectedFormat);

		String isoDateFormaterdCreatedFormatter = DateUtils.formatIsoDateTime(wrongDateFormat, createdFormatter);

		System.out.println(isoDateFormaterdCreatedFormatter);
		//System.out.println(expectedDate);

		assertEquals(isoDateFormaterdCreatedFormatter, "1970/01/01");
	}
	private static Stream<Arguments> provideArgumentsForformatIsoDateTimeTwo() {
		return Stream.of(
				//1튷nput: date, 2튷nput: date in miliseconds
				Arguments.of("yyyy/MM/dd", "a"),
				Arguments.of("yyyy/MM/dd", "abc123"),
				Arguments.of("yyyy/MM/dd", "20/09/02/13")
		);
	}
	
	@Test
	void testFormatIsoDateTimeConsideringMiliseconds() {
		DateTimeFormatter createdFormatter = DateUtils.createFormatter("yyyy/MM/dd HH:mm:ss.SSS");
		
		String isoDateFormaterdCreatedFormatter = DateUtils.formatIsoDateTime("2022-12-04T19:29:38.123", createdFormatter);

		System.out.println(isoDateFormaterdCreatedFormatter);
		//System.out.println(expectedDate);	

		assertTrue("2022/12/04 19:29:38.000".equalsIgnoreCase(isoDateFormaterdCreatedFormatter));
	}
	
}
