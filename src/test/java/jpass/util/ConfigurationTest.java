package jpass.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import jpass.ui.EntryDetailsTable;
import jpass.xml.bind.Entry;
import org.junit.jupiter.api.Test;

import jpass.util.Configuration;

import java.time.format.DateTimeFormatter;
import java.util.function.Function;

class ConfigurationTest {

    @Test
    void testConfigurationCreation() {
        Configuration conf = Configuration.getInstance();
        assertNotNull(conf);
    }
    @Test
    void testConfigurationGetInteger() {
        Integer val = Configuration.getInstance().getInteger("default.password.generation.length",-1);
        assertEquals(14, val);
    }

    @Test
    void testConfigurationGetIntegerDefaultValue() {
        Integer val = Configuration.getInstance().getInteger("not_key_from_properties",-1);
        assertEquals(-1, val);
    }


    @Test
    void testConfigurationGetBoolean() {
        Boolean val = Configuration.getInstance().is("clear.clipboard.on.exit.enabled",true);
        assertEquals(false, val);
    }

    @Test
    void testConfigurationGetBooleanDefault() {
        Boolean val = Configuration.getInstance().is("not_key_from_properties",true);
        assertEquals(true, val);
    }

    @Test
    void testConfigurationGetProperty() {
        String val = Configuration.getInstance().get("clear.clipboard.on.exit.enabled","default");
        assertEquals("false", val);
        assertNotEquals("default", val);
    }


    @Test
    void testConfigurationGetArray() {
        String[] val = Configuration.getInstance().getArray("entry.details", null);
        String[] expected = new String[2];
        expected[0]="TITLE";
        expected[1]="MODIFIED";

        assertArrayEquals(expected, val);

        String[] val2 = Configuration.getInstance().getArray("not_a_real_entry_details", null);
        assertEquals(null, val2);

        String[] defaultArray = new String[1];
        String[] val3 = Configuration.getInstance().getArray("not_a_real_entry_details", defaultArray);
        assertArrayEquals(defaultArray, val3);

    }
}
