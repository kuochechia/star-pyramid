package com.attendance.demo.starpyramid;

import org.springframework.stereotype.Component;

import java.io.PrintStream;

@Component
public class StarPyramidPrinter {

    private final StarPyramidGenerator generator;

    public StarPyramidPrinter(StarPyramidGenerator generator) {
        this.generator = generator;
    }

    public void print(int n, PrintStream out) {
        for (String line : generator.generateLines(n)) {
            out.println(line);
        }
    }
}
