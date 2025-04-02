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
    void testInsertInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.insert('a', 1));
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

    @Test
    void testInsertAtZeroEmptyList() {
        list.insert('a', 0);
        assertEquals(1, list.length());
        assertEquals('a', list.get(0));
    }

    @Test
    void testInsertAtEnd() {
        list.append('a');
        list.insert('b', 1);
        assertEquals(2, list.length());
        assertEquals('b', list.get(1));
    }

    
    @Test
    void testDeleteMiddleElement() {
        list.append('a');
        list.append('b');
        list.append('c');
        assertEquals('b', list.delete(1));
        assertEquals(2, list.length());
        assertEquals('a', list.get(0));
        assertEquals('c', list.get(1));
    }

    @Test
    void testDeleteLastElement() {
        list.append('a');
        list.append('b');
        assertEquals('b', list.delete(1));
        assertEquals(1, list.length());
    }


    @Test
    void testDeleteAllFromEmptyList() {
        list.deleteAll('a');
        assertEquals(0, list.length());
    }

    @Test
    void testDeleteAllNonExistent() {
        list.append('b');
        list.append('c');
        list.deleteAll('a');
        assertEquals(2, list.length());
    }

    @Test
    void testDeleteAllMultipleConsecutive() {
        list.append('a');
        list.append('a');
        list.append('b');
        list.append('a');
        list.append('a');
        list.deleteAll('a');
        assertEquals(1, list.length());
        assertEquals('b', list.get(0));
    }


    @Test
    void testCloneModificationIndependent() {
        list.append('a');
        InitialImplementation cloned = list.clone();
        cloned.append('b');
        assertEquals(1, list.length());
        assertEquals(2, cloned.length());
    }

    @Test
    void testCloneOfEmptyList() {
        InitialImplementation cloned = list.clone();
        assertEquals(0, cloned.length());
    }


    @Test
    void testFindFirstInEmptyList() {
        assertEquals(-1, list.findFirst('a'));
    }

    @Test
    void testFindLastInEmptyList() {
        assertEquals(-1, list.findLast('a'));
    }

    @Test
    void testFindFirstLastSameElement() {
        list.append('a');
        assertEquals(0, list.findFirst('a'));
        assertEquals(0, list.findLast('a'));
    }


    @Test
    void testClearThenReuse() {
        list.append('a');
        list.clear();
        list.append('b');
        assertEquals(1, list.length());
        assertEquals('b', list.get(0));
    }


    @Test
    void testExtendWithEmptyList() {
        list.append('a');
        InitialImplementation other = new InitialImplementation();
        list.extend(other);
        assertEquals(1, list.length());
    }

    @Test
    void testExtendWithMultipleElements() {
        InitialImplementation other = new InitialImplementation();
        other.append('b');
        other.append('c');

        list.append('a');
        list.extend(other);

        assertEquals(3, list.length());
        assertEquals('a', list.get(0));
        assertEquals('b', list.get(1));
        assertEquals('c', list.get(2));
    }

    @Test
    void testExtendEmptyList() {
        InitialImplementation other = new InitialImplementation();
        other.append('a');
        other.append('b');

        list.extend(other);

        assertEquals(2, list.length());
        assertEquals('a', list.get(0));
        assertEquals('b', list.get(1));
    }


    @Test
    void testGetInvalidIndex() {
        list.append('a');
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }
}
