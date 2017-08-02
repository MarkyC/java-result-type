package name.marcocirillo.result;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class FailureTest {
    @Test
    void isSuccess() {
        assertFalse(Failure.of(new RuntimeException()).isSuccess());
    }

    @Test
    void getError() {
        Exception err = new RuntimeException();
        assertEquals(err, Failure.of(err).getError());
    }

    @Test
    void fold() {
        assertEquals("works", Failure.of(new RuntimeException()).fold(
                        s -> fail("Shouldn't happen"),
                        f -> "works"));
    }
}