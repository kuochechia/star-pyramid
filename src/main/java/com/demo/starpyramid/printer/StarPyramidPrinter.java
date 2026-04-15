package com.demo.starpyramid.printer;

import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.util.List;

@Component
public class StarPyramidPrinter {

    public void print(List<String> lines, PrintWriter writer) {
        lines.forEach(writer::println);
        writer.flush();
    }
}
