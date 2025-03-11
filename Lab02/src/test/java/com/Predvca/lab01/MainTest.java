package com.Predvca.lab01;

import com.google.common.base.Joiner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testConcatenare() {
        String rezultat = Joiner.on(", ").join(1, 2, 3);
        assertEquals("1, 2, 3", rezultat);
    }

    @Test
    void testSpecialChar() {
        String rezultat = Joiner.on("$%%#").join("$", "#");
        assertEquals("$$%%##", rezultat);
    }
}
