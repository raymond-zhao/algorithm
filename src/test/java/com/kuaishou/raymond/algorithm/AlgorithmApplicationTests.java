package com.kuaishou.raymond.algorithm;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class AlgorithmApplicationTests {

    @Resource
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void testHelloEndpoint() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/hello")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"))
                .andReturn();
        log.info("result = {}", result);
        // You can perform additional assertions based on the result if needed
        // For example, you might want to check headers, status, etc.
    }
}
