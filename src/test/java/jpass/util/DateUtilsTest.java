package jpass.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DateUtilsTest {
	private final static String FORMATTER_VALID_DD_MM_YY = "DD-MM-YY";
	private final static DateTimeFormatter FORMATTER_VALID_DD_MM_YY_EXPECTED = DateTimeFormatter.ofPattern(FORMATTER_VALID_DD_MM_YY);
	private final static String FORMATTER_INVALID = "NotAValidFormatter";
	private final static DateTimeFormatter FORMATTER_INVALID_EXPECTED = DateTimeFormatter.ISO_DATE;
	private final static String FORMATTER_EMPTY = "";
	private final static DateTimeFormatter FORMATTER_EMPTY_EXPECTED = DateTimeFormatter.ISO_DATE;
	private final static String FORMATTER_NULL = null;
	private final static DateTimeFormatter FORMATTER_NULL_EXPECTED = DateTimeFormatter.ISO_DATE;
	
	private final static String DATE_TO_TEST_FORMATTERS = LocalDateTime.now()
            .truncatedTo(ChronoUnit.SECONDS)
            .format(DateTimeFormatter.ISO_DATE_TIME);

    @ParameterizedTest
    @MethodSource("provideArgumentsForCreateFormatter")
	void testCreateFormatter(String inputFormat, DateTimeFormatter expectedFormatter) {
		System.out.println(inputFormat);
		System.out.println(expectedFormatter);
    	DateTimeFormatter createdFormatter = DateUtils.createFormatter(inputFormat);
    	String isoDateFormaterdCreatedFormatter = DateUtils.formatIsoDateTime(DATE_TO_TEST_FORMATTERS,createdFormatter);
    	String isoDateFormaterdExpectedFormatter = DateUtils.formatIsoDateTime(DATE_TO_TEST_FORMATTERS,expectedFormatter);
    	assertEquals(isoDateFormaterdCreatedFormatter,isoDateFormaterdExpectedFormatter);
	}

    @SuppressWarnings("unused")
	private static Stream<Arguments> provideArgumentsForCreateFormatter() {
        return Stream.of(
          Arguments.of(FORMATTER_VALID_DD_MM_YY, FORMATTER_VALID_DD_MM_YY_EXPECTED),
          Arguments.of(FORMATTER_INVALID, FORMATTER_INVALID_EXPECTED),
          Arguments.of(FORMATTER_EMPTY, FORMATTER_EMPTY_EXPECTED),
          Arguments.of(FORMATTER_NULL, FORMATTER_NULL_EXPECTED)
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
				//1ºinput: structure, 2ºinput: date in miliseconds, 3ºinput: date formated
				Arguments.of("YYYY/MM/DD", "96423478300", "1973/01/21"),
				Arguments.of("YYYY/MM/DD", "1287224000", "1970/01/15"),
				Arguments.of("YYYY/MM/DD", "1234567891234", "2009/02/13")
		);
	}

	@ParameterizedTest
	@MethodSource("provideArgumentsForformatIsoDateTimeTwo")//tests the method FormatIsoDateTime more expecially on the second catch
	void testFormatIsoDateTimeTwo(String expectedFormat, String wrongDateFormat) {
		DateTimeFormatter createdFormatter = DateUtils.createFormatter(expectedFormat);

		String isoDateFormaterdCreatedFormatter = DateUtils.formatIsoDateTime(wrongDateFormat, createdFormatter);

		System.out.println(isoDateFormaterdCreatedFormatter);
		//System.out.println(expectedDate);

		assertEquals(isoDateFormaterdCreatedFormatter, "1969/01/01");
	}
	private static Stream<Arguments> provideArgumentsForformatIsoDateTimeTwo() {
		return Stream.of(
				//1ºinput: date, 2ºinput: date in miliseconds
				Arguments.of("YYYY/MM/DD", "a"),
				Arguments.of("YYYY/MM/DD", "abc123"),
				Arguments.of("YYYY/MM/DD", "20/09/02/13")
		);
	}
}