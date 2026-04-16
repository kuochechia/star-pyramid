package com.demo.starpyramid.printer;

import com.demo.starpyramid.generator.StarPyramidGenerator;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.util.List;

@Component
public class StarPyramidPrinter {

    private final StarPyramidGenerator generator;

    public StarPyramidPrinter(StarPyramidGenerator generator) {
        this.generator = generator;
    }

    public void print(List<String> lines, PrintWriter writer) {
        lines.forEach(writer::println);
        writer.flush();
    }

    /**
     * 依序輸出倒立金字塔各行（不修改行內字元）。
     */
    public void printInverted(int height, PrintWriter writer) {
        print(generator.generateInverted(height), writer);
    }
}
