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
    @Test
    void testInsert() {
        list.append('a');
        list.insert('b', 1);
        assertEquals(2, list.length());
        assertEquals('b', list.get(1));
    }
    @Test
    void testDelete() {
        list.append('a');
        assertEquals('a', list.delete(0));
        assertEquals(0, list.length());
    }

    @Test
    void testDeleteInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.delete(0));
    }

    @Test
    void testDeleteAll() {
        list.append('a');
        list.append('b');
        list.append('a');
        list.deleteAll('a');
        assertEquals(1, list.length());
        assertEquals('b', list.get(0));
    }
    @Test
    void testInsertInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.insert('a', 1));
    }
    @Test
    void testReverse() {
        list.append('a');
        list.append('b');
        list.append('c');
        list.reverse();
        assertEquals('c', list.get(0));
        assertEquals('b', list.get(1));
        assertEquals('a', list.get(2));
    }
    @Test
    void testClone() {
        list.append('a');
        list.append('b');
        InitialImplementation cloned = list.clone();
        assertEquals(2, cloned.length());
        assertEquals('a', cloned.get(0));
        assertEquals('b', cloned.get(1));
    }
    @Test
    void testFindFirst() {
        list.append('x');
        list.append('y');
        list.append('x');
        assertEquals(0, list.findFirst('x'));
        assertEquals(1, list.findFirst('y'));
        assertEquals(-1, list.findFirst('z'));
    }

    @Test
    void testFindLast() {
        list.append('x');
        list.append('y');
        list.append('x');
        assertEquals(2, list.findLast('x'));
        assertEquals(1, list.findLast('y'));
        assertEquals(-1, list.findLast('z'));
    }
    @Test
    void testClear() {

        list.append('a');
        list.append('b');
        list.clear();
        assertEquals(0, list.length(), "List should be empty after clear()");


        assertDoesNotThrow(() -> list.clear());
    }
}
