package com.demo.starpyramid.service;

import com.demo.starpyramid.dto.InvertedStarPyramidResponse;
import com.demo.starpyramid.dto.StarPyramidResponse;

public interface StarPyramidService {

    StarPyramidResponse generate(int height);

    InvertedStarPyramidResponse generateInverted(int height);
}
