package com.example.FinalProject.controller;

import com.example.FinalProject.command.DirectLinkController;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


public class DirectLinkControllerTest {

    private final DirectLinkController directLinkController = new DirectLinkController();
    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(directLinkController).build();

    @Test
    public void indexJsp() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/index_jsp");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.view().name("index.jsp"));
    }
}
