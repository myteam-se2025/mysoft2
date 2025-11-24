package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modl.Book;

class FindeByIdTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testFindById() {

        // 1️⃣ Mock للإستراتيجية
        SearchBook mockStrategy = mock(SearchBook.class);

        // 2️⃣ Fake data — يستخدم نفس الكونستركتر تبعك (5 باراميترات)
        List<Book> mockList = new ArrayList<>();
        mockList.add(new Book("Clean Code", "Robert", "98765", "Programming", 3));

        when(mockStrategy.findeBook("10")).thenReturn(mockList);

        // 3️⃣ نستخدم FindeBooks ونمرر له الإستراتيجية
        FindeBooks service = new FindeBooks();
        service.setStratgy(mockStrategy);

        // 4️⃣ تنفيذ البحث
        List<Book> result = service.findeBook("10");

        // 5️⃣ Assertions
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Clean Code", result.get(0).getTitle());
        assertEquals("Robert", result.get(0).getAuthor());
        assertEquals("98765", result.get(0).getIsbn());

        // 6️⃣ Verify
        verify(mockStrategy, times(1)).findeBook("10");
    }

    @Test
    void testNoStrategySet() {

        FindeBooks service = new FindeBooks();

        Exception ex = assertThrows(IllegalStateException.class, () -> {
            service.findeBook("any");
        });

        assertEquals("Fine stratgy not set", ex.getMessage());
    }
}