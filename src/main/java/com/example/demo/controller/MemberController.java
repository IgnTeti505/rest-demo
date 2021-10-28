package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/member")
public class MemberController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final MemberService service;
    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Member> getAll() {
        LOGGER.debug("getAll...");
        return service.getAll();
    }

    @PostMapping("/save")
    public Member post(@RequestBody Member entity) {
        LOGGER.debug("saving..." + entity);

        return service.post(entity);
    }
}
