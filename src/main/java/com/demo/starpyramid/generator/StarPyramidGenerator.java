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
     * 產生置中星星沙漏：參數為<strong>輸出總行數</strong> {@code n}（非單臂高度）。
     * 先以臂高 {@code h = n/2 + 1} 組出對稱完整沙漏，再取前 {@code n} 行。
     */
    public List<String> generateHourglass(int height) {
        if (height <= 0) {
            throw new InvalidHeightException(height);
        }
        int h = height / 2 + 1;
        List<String> inverted = generateInverted(h);
        List<String> upright = generate(h);
        List<String> full = Stream.concat(inverted.stream(), upright.subList(1, h).stream()).toList();
        return List.copyOf(full.subList(0, height));
    }
}
