package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {

    final List<Member> members = new ArrayList<>();

    public List<Member> findAll() {
        return members;
    }

    public Member save(Member entity) {
        entity.setId((long) members.size()+1);
        members.add(entity);

        return entity;
    }
}
