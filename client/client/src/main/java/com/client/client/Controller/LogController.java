package com.client.client.Controller;

import com.client.client.Client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogController {
    @Autowired
    private RestClient restClient;

    @GetMapping("/logs")
    public ModelAndView getLogs(){
        ModelAndView modelAndView = new ModelAndView("logger");
        modelAndView.addObject("isAdmin", crudAuthorized());
        modelAndView.addObject("logs", restClient.getLogs());
        return modelAndView;
    }
    private boolean crudAuthorized(){
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> ((GrantedAuthority) a).getAuthority().equals("ROLE_ADMIN"));
    }
}
