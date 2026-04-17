package com.demo.starpyramid.service;

import com.demo.starpyramid.dto.InvertedStarPyramidResponse;
import com.demo.starpyramid.dto.StarHourglassResponse;
import com.demo.starpyramid.dto.StarPyramidResponse;
import com.demo.starpyramid.generator.StarPyramidGenerator;
import org.springframework.stereotype.Service;

@Service
public class StarPyramidServiceImpl implements StarPyramidService {

    private final StarPyramidGenerator generator;

    public StarPyramidServiceImpl(StarPyramidGenerator generator) {
        this.generator = generator;
    }

    @Override
    public StarPyramidResponse generate(int height) {
        return new StarPyramidResponse(generator.generate(height));
    }

    @Override
    public InvertedStarPyramidResponse generateInverted(int height) {
        return new InvertedStarPyramidResponse(generator.generateInverted(height));
    }

    @Override
    public StarHourglassResponse generateHourglass(int height) {
        return new StarHourglassResponse(generator.generateHourglass(height));
    }
}
