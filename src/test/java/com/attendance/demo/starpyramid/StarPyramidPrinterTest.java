package com.attendance.demo.starpyramid;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class StarPyramidPrinterTest {

    private final StarPyramidGenerator generator = new StarPyramidGenerator();
    private final StarPyramidPrinter printer = new StarPyramidPrinter(generator);

    @Test
    void print_height3_outputs_lines_in_order() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        printer.print(3, new PrintStream(buffer));
        String[] lines = buffer.toString().split(System.lineSeparator());
        assertThat(lines).containsExactly("  *", " ***", "*****");
    }

    @Test
    void print_outputs_exactly_n_lines() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        printer.print(5, new PrintStream(buffer));
        String[] lines = buffer.toString().split(System.lineSeparator());
        assertThat(lines).hasSize(5);
    }
}
