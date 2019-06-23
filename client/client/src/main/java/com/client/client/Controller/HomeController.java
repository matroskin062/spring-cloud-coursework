package com.client.client.Controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value= {"/index"})
public class HomeController {
    @GetMapping
    public String index(ModelMap modelMap){
        modelMap.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        return "index";
    }
}
