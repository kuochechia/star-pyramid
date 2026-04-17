package com.demo.starpyramid.controller;

import com.demo.common.ApiResponse;
import com.demo.starpyramid.dto.InvertedStarPyramidResponse;
import com.demo.starpyramid.dto.StarHourglassResponse;
import com.demo.starpyramid.dto.StarPyramidResponse;
import com.demo.starpyramid.service.StarPyramidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/star-pyramid")
public class StarPyramidController {

    private final StarPyramidService starPyramidService;

    public StarPyramidController(StarPyramidService starPyramidService) {
        this.starPyramidService = starPyramidService;
    }

    @GetMapping("/inverted/{height}")
    public ResponseEntity<ApiResponse<InvertedStarPyramidResponse>> generateInverted(@PathVariable int height) {
        try {
            InvertedStarPyramidResponse response = starPyramidService.generateInverted(height);
            return ResponseEntity.ok(ApiResponse.ok(response));
        } catch (Exception ex) {
            log.error("Failed to generate inverted star pyramid for height={}", height, ex);
            throw ex;
        }
    }

    @GetMapping("/hourglass/{height}")
    public ResponseEntity<ApiResponse<StarHourglassResponse>> generateHourglass(@PathVariable int height) {
        try {
            StarHourglassResponse response = starPyramidService.generateHourglass(height);
            return ResponseEntity.ok(ApiResponse.ok(response));
        } catch (Exception ex) {
            log.error("Failed to generate star hourglass for height={}", height, ex);
            throw ex;
        }
    }

    @GetMapping("/{height}")
    public ResponseEntity<ApiResponse<StarPyramidResponse>> generate(@PathVariable int height) {
        try {
            StarPyramidResponse response = starPyramidService.generate(height);
            return ResponseEntity.ok(ApiResponse.ok(response));
        } catch (Exception ex) {
            log.error("Failed to generate star pyramid for height={}", height, ex);
            throw ex;
        }
    }
}
