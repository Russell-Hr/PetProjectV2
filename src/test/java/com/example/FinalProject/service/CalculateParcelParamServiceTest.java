package com.example.FinalProject.service;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CalculateParcelParamServiceTest {

    private final CalculateParcelParamService calculateParcelParamService = new CalculateParcelParamService();
    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(calculateParcelParamService).build();

//    @Test
//    public void calculatePrice() throws Exception {
//        //MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/index_jsp");
//        ResultActions result = mockMvc.perform(request);
//        result.andExpect(MockMvcResultMatchers.view().name("index.jsp"));

}
