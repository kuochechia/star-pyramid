package com.demo.starpyramid.dto;

import java.util.List;

public record InvertedStarPyramidResponse(List<String> lines) {

    public InvertedStarPyramidResponse {
        if (lines == null) {
            throw new IllegalArgumentException("lines must not be null");
        }
    }
}
