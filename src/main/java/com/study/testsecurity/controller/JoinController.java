package com.study.testsecurity.controller;

import com.study.testsecurity.dto.JoinDTO;
import com.study.testsecurity.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService; //생성자 주입 방식 의존성 주입

    @GetMapping(value = "/join")
    public String joinP(){

        return "join";
    }

    @PostMapping(value = "/joinProc")
    public String joinProcess(JoinDTO joinDTO){

        log.info("User Name = {}", joinDTO.getUsername());

        joinService.joinProcess(joinDTO);

        return "redirect:/login";
    }
}
