package jpass.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jpass.util.Configuration;

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
    void testConfigurationGetBoolean() {
        Boolean val = Configuration.getInstance().is("clear.clipboard.on.exit.enabled",true);
        assertEquals(false, val);
    }

    @Test
    void testConfigurationGetArray() {
        String[] val = Configuration.getInstance().getArray("entry.details", null);
        String[] expected = new String[2];
        expected[0]="TITLE";
        expected[1]="MODIFIED";

        assertArrayEquals(expected, val);
    }
}
