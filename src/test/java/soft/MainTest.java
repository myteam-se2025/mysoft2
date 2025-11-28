package soft;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    void testMainFrameCreation() {
        // التأكد من عدم وقوع استثناء عند تشغيل main
        assertDoesNotThrow(() -> {
            Main.main(new String[]{}); // نستدعي main كما هو
        });
    }
}