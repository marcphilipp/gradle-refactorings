package org.example.lib;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    @Test
    void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertEquals(Set.of("foo", "bar"), classUnderTest.someLibraryMethod());
    }
}
