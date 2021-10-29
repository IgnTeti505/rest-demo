package com.example.demo.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

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

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MemberServiceImpl service;

    String preUrl = "/api/member";

    @Test
    public void getAllTest() throws Exception {
        String url = preUrl + "/all";
        List<Member> memberList = new ArrayList<>();
        memberList.add(new Member(1L, "Juan", "Perez", 20));
        memberList.add(new Member(2L, "Alex", "Tellez", 20));

        when(service.getAll()).thenReturn(memberList);

        mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void postTest() throws Exception {
        String url = preUrl + "/save";
        Member member = new Member("Juan", "Perez", 20);

        mockMvc.perform(post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(member)))
                .andExpect(status().isOk());

    }
}
