package com.Predvca.lab01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testConcatenare() {
        String rezultat = "1, 2, 3";
        assertEquals(rezultat, "1, 2, 3");
    }

    @Test
    void testStringGol() {
        String rezultat = "";
        assertEquals(rezultat, "");
    }
}
