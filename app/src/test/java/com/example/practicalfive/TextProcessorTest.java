package com.example.practicalfive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TextProcessorTest {
    @Test
    public void normalizesEverySupportedMunicipality() {
        assertEquals("Beijing", TextProcessor.processMunicipality("beijing"));
        assertEquals("Beijing", TextProcessor.processMunicipality("BEIJING"));
        assertEquals("Beijing", TextProcessor.processMunicipality("  Beijing  "));
        assertEquals("Shanghai", TextProcessor.processMunicipality("SHANGHAI"));
        assertEquals("Tianjin", TextProcessor.processMunicipality("tianjin"));
        assertEquals("Chongqing", TextProcessor.processMunicipality("cHoNgQiNg"));
    }

    @Test
    public void rejectsNullBlankAndUnsupportedMunicipalities() {
        assertNull(TextProcessor.processMunicipality(null));
        assertNull(TextProcessor.processMunicipality(""));
        assertNull(TextProcessor.processMunicipality("   \t\n"));
        assertNull(TextProcessor.processMunicipality("Guangzhou"));
    }
}
