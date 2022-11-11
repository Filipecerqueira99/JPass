package jpass.crypt.data;

import jpass.data.DataModel;
import jpass.xml.bind.Entries;
import jpass.xml.bind.Entry;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DataModelTest {

    DataModel datamodel;

    @Before
    public void getInstanceTest() {
        datamodel = DataModel.getInstance();
    }


    @Test
    public void entriesTest() {
        datamodel.setEntries(new Entries());
        assertEquals(new ArrayList<Entry>(), datamodel.getEntries().getEntry());
    }

    @Test
    public void filenameTest() {
        String filename = "filename";
        datamodel.setFileName(filename);
        assertEquals(filename, datamodel.getFileName());
    }

    @Test
    public void modifiedFlagTest() {
        datamodel.setModified(true);
        assertTrue(datamodel.isModified());
    }

    @Test
    public void passwordTest() {
        //Test password
        byte[] passArray = new byte[1];
        datamodel.setPassword(passArray);
        assertEquals(passArray[0], datamodel.getPassword()[0]);
    }

    @Test
    public void clearTest() {
        datamodel.clear();
        Entry entry = new Entry();
        entry.setTitle("title");
        Entries entries = new Entries();
        entries.getEntry().add(entry);

        datamodel.setEntries(entries);
        datamodel.clear();

        assertTrue(datamodel.getEntries().getEntry().isEmpty());
        assertNull(datamodel.getFileName());
        assertNull(datamodel.getPassword());
        assertEquals(false, datamodel.isModified());
    }

    @Test
    public void entryByTitleTest() {
        datamodel.clear();
        String title = "tittle";
        Entry entry = new Entry();
        entry.setTitle(title);

        Entries entries = new Entries();
        entries.getEntry().add(entry);
        datamodel.setEntries(entries);

        assertEquals(datamodel.getEntryByTitle(title), entry);
    }
    @Test
    public void entryIndexByTitleTest() {
        datamodel.clear();
        String title = "title";
        Entry entry = new Entry();
        entry.setTitle(title);

        Entries entries = new Entries();
        entries.getEntry().add(entry);
        datamodel.setEntries(entries);

        assertEquals(datamodel.getEntryIndexByTitle(title), 0);
    }

    @Test
    public void entryByTitleNullTest() {
        assertEquals(datamodel.getEntryByTitle(""), null);
    }

}
