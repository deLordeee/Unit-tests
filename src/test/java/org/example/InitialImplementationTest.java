package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InitialImplementationTest {
    private InitialImplementation list;

    @BeforeEach
    void setUp() {
        list = new InitialImplementation();
    }

    @Test
    void testAppend() {
        list.append('a');
        assertEquals(1, list.length());
        assertEquals('a', list.get(0));
    }


}
