package com.demo.starpyramid.dto;

import java.util.List;

public record StarPyramidResponse(List<String> lines) {

    public StarPyramidResponse {
        if (lines == null) {
            throw new IllegalArgumentException("lines must not be null");
        }
    }
}
