package edu.pan;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

class ControlledFailureTest {
    @Test
    void demonstratePipelineFailure() {
        fail("Intentional failure: Pan Eduard IT2-2312");
    }
}
