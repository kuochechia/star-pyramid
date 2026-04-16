package com.demo.starpyramid;

import com.demo.starpyramid.exception.InvalidHeightException;
import com.demo.starpyramid.generator.StarPyramidGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StarPyramidGeneratorTest {

    private StarPyramidGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new StarPyramidGenerator();
    }

    @Test
    void height1_producesSingleStar() {
        List<String> lines = generator.generate(1);
        assertThat(lines).containsExactly("*");
    }

    @Test
    void height2_producesTwoCenteredLines() {
        List<String> lines = generator.generate(2);
        assertThat(lines).containsExactly(" *", "***");
    }

    @Test
    void height3_producesThreeCenteredLines() {
        List<String> lines = generator.generate(3);
        assertThat(lines).containsExactly("  *", " ***", "*****");
    }

    @Test
    void height5_producesFiveCenteredLines() {
        List<String> lines = generator.generate(5);
        assertThat(lines).containsExactly(
                "    *",
                "   ***",
                "  *****",
                " *******",
                "*********"
        );
    }

    @Test
    void height0_throwsInvalidHeightException() {
        assertThatThrownBy(() -> generator.generate(0))
                .isInstanceOf(InvalidHeightException.class)
                .hasMessageContaining("positive integer");
    }

    @Test
    void negativeHeight_throwsInvalidHeightException() {
        assertThatThrownBy(() -> generator.generate(-3))
                .isInstanceOf(InvalidHeightException.class)
                .hasMessageContaining("positive integer");
    }
}
