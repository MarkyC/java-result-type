package name.marcocirillo.result;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


class SuccessTest {
    @Test
    void isSuccess() {
        assertTrue(Success.of("whatever").isSuccess());
    }

    @Test
    void getResult() {
        assertEquals("whatever", Success.of("whatever").getResult());
    }

    @Test
    void fold() {
        assertEquals("works", Success.of("whatever").fold(
                        s -> "works",
                        f -> fail("Shouldn't happen")));
    }
}