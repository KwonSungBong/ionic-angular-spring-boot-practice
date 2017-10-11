package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ksb on 2017. 8. 19..
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("test", "test");
        return "index";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form() {
        return "login";
    }

    @RequestMapping(value = "/social", method = RequestMethod.GET)
    public String social() {
        return "social";
    }

}
