package jpass.data;

import jpass.data.DocumentProcessException;
import jpass.data.EntriesRepository;
import jpass.xml.bind.Entries;
import jpass.xml.bind.Entry;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentProcessExceptionTest {
    byte[] key = {(byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06,
            (byte) 0x07, (byte) 0x08, (byte) 0x09, (byte) 0x0a, (byte) 0x0b, (byte) 0x0c, (byte) 0x0d, (byte) 0x0e,
            (byte) 0x0f, (byte) 0x10, (byte) 0x11, (byte) 0x12, (byte) 0x13, (byte) 0x14, (byte) 0x15, (byte) 0x16,
            (byte) 0x17, (byte) 0x18, (byte) 0x19, (byte) 0x1a, (byte) 0x1b, (byte) 0x1c, (byte) 0x1d, (byte) 0x1e,
            (byte) 0x1f};

    @Test
    public void repositoryReadAndWriteTest() throws IOException, DocumentProcessException {
        EntriesRepository entriesRepository = EntriesRepository.newInstance("test");
        entriesRepository.writeDocument(new Entries());
        Entries entries = entriesRepository.readDocument();
        assertEquals(new ArrayList<Entry>(), entries.getEntry());

        EntriesRepository entriesRepositoryWithKey = EntriesRepository.newInstance("test", key);
        Entries entriesWithKey = entriesRepository.readDocument();
        assertEquals(new ArrayList<Entry>(), entriesWithKey.getEntry());

    }

}
