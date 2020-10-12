package com.project.web.sign;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/center", method = RequestMethod.GET)
    public String personCenter() {

        return "user/center";
    }
}
