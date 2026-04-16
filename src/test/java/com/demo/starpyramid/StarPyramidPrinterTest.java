package com.demo.starpyramid;

import com.demo.starpyramid.generator.StarPyramidGenerator;
import com.demo.starpyramid.printer.StarPyramidPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StarPyramidPrinterTest {

    private StarPyramidPrinter printer;
    private StringWriter output;
    private PrintWriter writer;

    @BeforeEach
    void setUp() {
        printer = new StarPyramidPrinter(new StarPyramidGenerator());
        output = new StringWriter();
        writer = new PrintWriter(output);
    }

    @Test
    void printsAllLinesInOrder() {
        List<String> lines = List.of("  *", " ***", "*****");

        printer.print(lines, writer);

        String[] printed = output.toString().split(System.lineSeparator());
        assertThat(printed).containsExactly("  *", " ***", "*****");
    }

    @Test
    void doesNotModifyLineContent() {
        List<String> lines = List.of(" *", "***");

        printer.print(lines, writer);

        String result = output.toString();
        assertThat(result).contains(" *");
        assertThat(result).contains("***");
        assertThat(result).doesNotContain("  *");
    }

    @Test
    void eachLineIsSeparatedByNewline() {
        List<String> lines = List.of("*", "***");

        printer.print(lines, writer);

        assertThat(output.toString()).contains(System.lineSeparator());
    }

    @Test
    void printInverted_outputsInvertedLinesInOrder() {
        printer.printInverted(3, writer);

        String[] printed = output.toString().split(System.lineSeparator());
        assertThat(printed).containsExactly("*****", " ***", "  *");
    }
}
