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

class FindeBooksTest {

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
    void testFindeBooks() {

        // 1️⃣ Mock للإستراتيجية
        SearchBook mockStrategy = mock(SearchBook.class);

        // 2️⃣ إضافة كتب لإرجاعها (خمس باراميترات حسب الكونستركتر)
        List<Book> mockList = new ArrayList<>();
        mockList.add(new Book("Java", "James", "12345", "Programming", 5));

        when(mockStrategy.findeBook("Java")).thenReturn(mockList);

        // 3️⃣ إنشاء الخدمة وإعطاء الإستراتيجية
        FindeBooks service = new FindeBooks();
        service.setStratgy(mockStrategy);

        // 4️⃣ تنفيذ البحث
        List<Book> result = service.findeBook("Java");

        // 5️⃣ Assertions
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Java", result.get(0).getTitle());

        // 6️⃣ Verify
        verify(mockStrategy, times(1)).findeBook("Java");
    }

    @Test
    void testNoStratgySet() {
        FindeBooks service = new FindeBooks();

        Exception ex = assertThrows(IllegalStateException.class, () -> {
            service.findeBook("test");
        });

        assertEquals("Fine stratgy not set", ex.getMessage());
    }
}