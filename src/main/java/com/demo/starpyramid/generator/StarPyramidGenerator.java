package com.demo.starpyramid.generator;

import com.demo.starpyramid.exception.InvalidHeightException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class StarPyramidGenerator {

    public List<String> generate(int height) {
        if (height <= 0) {
            throw new InvalidHeightException(height);
        }
        return IntStream.rangeClosed(1, height)
                .mapToObj(i -> " ".repeat(height - i) + "*".repeat(2 * i - 1))
                .toList();
    }
}
