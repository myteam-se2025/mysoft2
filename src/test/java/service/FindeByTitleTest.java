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

class FindeByTitleTest {

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
    void testFindByTitle() {

        // 1️⃣ Mock للإستراتيجية
        SearchBook mockStrategy = mock(SearchBook.class);

        // 2️⃣ Fake Data — باستخدام الكونستركتر (5 باراميتر)
        List<Book> mockList = new ArrayList<>();
        mockList.add(new Book("Data Structures", "Mark", "11223", "CS", 7));

        when(mockStrategy.findeBook("Data Structures")).thenReturn(mockList);

        // 3️⃣ إنشاء خدمة FindeBooks وإعطاء الإستراتيجية
        FindeBooks service = new FindeBooks();
        service.setStratgy(mockStrategy);

        // 4️⃣ تنفيذ البحث
        List<Book> result = service.findeBook("Data Structures");

        // 5️⃣ Assertions
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Data Structures", result.get(0).getTitle());
        assertEquals("Mark", result.get(0).getAuthor());
        assertEquals("11223", result.get(0).getIsbn());

        // 6️⃣ Verify
        verify(mockStrategy, times(1)).findeBook("Data Structures");
    }

    @Test
    void testNoStrategySet() {

        FindeBooks service = new FindeBooks();

        Exception ex = assertThrows(IllegalStateException.class, () -> {
            service.findeBook("anything");
        });

        assertEquals("Fine stratgy not set", ex.getMessage());
    }
}