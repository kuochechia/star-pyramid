package com.demo.starpyramid.generator;

import com.demo.starpyramid.exception.InvalidHeightException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    /**
     * 產生倒立置中星星金字塔：第 1 行最寬，第 {@code height} 行最窄。
     */
    public List<String> generateInverted(int height) {
        if (height <= 0) {
            throw new InvalidHeightException(height);
        }
        return IntStream.rangeClosed(1, height)
                .mapToObj(i -> " ".repeat(i - 1) + "*".repeat(2 * height - 2 * i + 1))
                .toList();
    }

    /**
     * 產生置中星星沙漏：上半為倒立金字塔、下半為正立金字塔，最窄行不重複。
     */
    public List<String> generateHourglass(int height) {
        if (height <= 0) {
            throw new InvalidHeightException(height);
        }
        List<String> inverted = generateInverted(height);
        List<String> upright = generate(height);
        return Stream.concat(inverted.stream(), upright.subList(1, height).stream()).toList();
    }
}
