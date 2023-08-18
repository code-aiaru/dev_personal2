package org.spring.dev_project_personal2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 시작주소를 받아주는 Method
    @GetMapping("/")
    public String index(){
        return "index";
    }

}
