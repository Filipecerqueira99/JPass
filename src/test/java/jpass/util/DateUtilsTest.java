package jpass.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
}
