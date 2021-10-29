package com.example.demo.service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.impl.MemberServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MemberServiceTest {

    @Mock
    MemberRepository repository;
    @InjectMocks
    MemberServiceImpl service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTest() {
        List<Member> memberList = new ArrayList<>();
        memberList.add(new Member(1L, "Juan", "Perez", 20));
        memberList.add(new Member(2L, "Alex", "Tellez", 20));

        when(repository.findAll()).thenReturn(memberList);
        List<Member> response = service.getAll();

        assertEquals(2, response.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void postTest() {
        Member member = new Member(1L,"Juan","Perez",20);

        service.post(member);

        verify(repository, times(1)).save(member);
    }
}
