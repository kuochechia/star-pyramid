package com.attendance.demo.starpyramid;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StarPyramidGenerator {

    public List<String> generateLines(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("高度必須為正整數，輸入值：" + n);
        }
        List<String> lines = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            lines.add(" ".repeat(n - i) + "*".repeat(2 * i - 1));
        }
        return lines;
    }
}
