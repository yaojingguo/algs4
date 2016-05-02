package yao;

import org.junit.Test;

import static com.google.common.truth.Truth.*;

public class ValidateTest {

    @Test
    public void testValidate() {
        assertThat(Validate.validate("a*", "aab")).isFalse();
        assertThat(Validate.validate("a*", "aa")).isTrue();
    }

}
