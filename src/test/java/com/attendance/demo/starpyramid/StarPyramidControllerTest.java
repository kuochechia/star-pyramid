package com.attendance.demo.starpyramid;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StarPyramidController.class)
class StarPyramidControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StarPyramidGenerator generator;

    @Test
    void height3_returns_200_with_3_correct_lines() throws Exception {
        when(generator.generateLines(3)).thenReturn(List.of("  *", " ***", "*****"));

        mockMvc.perform(get("/api/pyramid").param("height", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lines").isArray())
                .andExpect(jsonPath("$.lines.length()").value(3))
                .andExpect(jsonPath("$.lines[0]").value("  *"))
                .andExpect(jsonPath("$.lines[1]").value(" ***"))
                .andExpect(jsonPath("$.lines[2]").value("*****"));
    }

    @Test
    void height0_returns_400_with_error() throws Exception {
        when(generator.generateLines(0))
                .thenThrow(new IllegalArgumentException("高度必須為正整數，輸入值：0"));

        mockMvc.perform(get("/api/pyramid").param("height", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void negative_height_returns_400_with_error() throws Exception {
        when(generator.generateLines(-1))
                .thenThrow(new IllegalArgumentException("高度必須為正整數，輸入值：-1"));

        mockMvc.perform(get("/api/pyramid").param("height", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void non_numeric_height_returns_400() throws Exception {
        mockMvc.perform(get("/api/pyramid").param("height", "abc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void decimal_height_returns_400() throws Exception {
        mockMvc.perform(get("/api/pyramid").param("height", "2.5"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }
}
