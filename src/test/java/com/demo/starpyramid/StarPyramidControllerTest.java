package com.demo.starpyramid;

import com.demo.common.GlobalExceptionHandler;
import com.demo.starpyramid.controller.StarPyramidController;
import com.demo.starpyramid.dto.InvertedStarPyramidResponse;
import com.demo.starpyramid.dto.StarPyramidResponse;
import com.demo.starpyramid.exception.InvalidHeightException;
import com.demo.starpyramid.service.StarPyramidService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StarPyramidController.class)
@Import(GlobalExceptionHandler.class)
class StarPyramidControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StarPyramidService starPyramidService;

    @Test
    void validHeight_returns200WithLines() throws Exception {
        when(starPyramidService.generate(3))
                .thenReturn(new StarPyramidResponse(List.of("  *", " ***", "*****")));

        mockMvc.perform(get("/api/star-pyramid/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.lines.length()").value(3))
                .andExpect(jsonPath("$.data.lines[0]").value("  *"))
                .andExpect(jsonPath("$.data.lines[2]").value("*****"));
    }

    @Test
    void invalidHeight_returns400() throws Exception {
        when(starPyramidService.generate(0))
                .thenThrow(new InvalidHeightException(0));

        mockMvc.perform(get("/api/star-pyramid/0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void negativeHeight_returns400() throws Exception {
        when(starPyramidService.generate(-1))
                .thenThrow(new InvalidHeightException(-1));

        mockMvc.perform(get("/api/star-pyramid/-1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void nonIntegerHeight_returns400() throws Exception {
        mockMvc.perform(get("/api/star-pyramid/2.5"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void nonNumericHeight_returns400() throws Exception {
        mockMvc.perform(get("/api/star-pyramid/abc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void inverted_validHeight_returns200WithInvertedLines() throws Exception {
        when(starPyramidService.generateInverted(3))
                .thenReturn(new InvertedStarPyramidResponse(List.of("*****", " ***", "  *")));

        mockMvc.perform(get("/api/star-pyramid/inverted/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.lines.length()").value(3))
                .andExpect(jsonPath("$.data.lines[0]").value("*****"))
                .andExpect(jsonPath("$.data.lines[2]").value("  *"));
    }

    @Test
    void inverted_invalidHeight_returns400() throws Exception {
        when(starPyramidService.generateInverted(0))
                .thenThrow(new InvalidHeightException(0));

        mockMvc.perform(get("/api/star-pyramid/inverted/0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").exists());
    }
}
