package com.attendance.demo.starpyramid;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class StarPyramidGeneratorTest {

    private final StarPyramidGenerator generator = new StarPyramidGenerator();

    @Test
    void n1_produces_single_star() {
        assertThat(generator.generateLines(1)).containsExactly("*");
    }

    @Test
    void n2_produces_two_centered_lines() {
        assertThat(generator.generateLines(2)).containsExactly(" *", "***");
    }

    @Test
    void n3_produces_three_centered_lines() {
        assertThat(generator.generateLines(3)).containsExactly("  *", " ***", "*****");
    }

    @Test
    void n5_produces_five_centered_lines() {
        assertThat(generator.generateLines(5)).containsExactly(
                "    *", "   ***", "  *****", " *******", "*********"
        );
    }

    @Test
    void zero_height_throws_with_message_containing_positive_integer() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> generator.generateLines(0))
                .withMessageContaining("正整數");
    }

    @Test
    void negative_height_throws() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> generator.generateLines(-3));
    }
}
