package com.demo.starpyramid.dto;

import java.util.List;

public record StarHourglassResponse(List<String> lines) {

    public StarHourglassResponse {
        if (lines == null) {
            throw new IllegalArgumentException("lines must not be null");
        }
    }
}
