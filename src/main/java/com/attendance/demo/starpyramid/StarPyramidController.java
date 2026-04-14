package com.attendance.demo.starpyramid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StarPyramidController {

    private final StarPyramidGenerator generator;

    public StarPyramidController(StarPyramidGenerator generator) {
        this.generator = generator;
    }

    @GetMapping("/pyramid")
    public Map<String, List<String>> getPyramid(@RequestParam int height) {
        return Map.of("lines", generator.generateLines(height));
    }
}
