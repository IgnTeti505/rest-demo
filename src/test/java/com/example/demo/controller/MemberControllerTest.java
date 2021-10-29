package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.service.impl.MemberServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MemberServiceImpl service;

    private final String preUrl = "/api/member";

    @Test
    public void getAllTest() throws Exception {
        String url = preUrl + "/all";
        List<Member> entities = new ArrayList<>();
        entities.add(new Member(1L, "Juan", "Perez", 20));
        entities.add(new Member(2L, "Alex", "Tellez", 20));

        when(service.getAll()).thenReturn(entities);

        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void postTest() throws Exception {
        String url = preUrl + "/save";
        Member entity = new Member("Juan", "Perez", 20);

        mockMvc.perform(post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(entity)))
                .andExpect(status().isOk());
    }
}
