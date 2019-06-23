package com.client.web.Controller;

import com.client.web.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeamController {
    @Autowired
    private TeamService service;

    @GetMapping("/teams")
    public String getAllTeams(ModelMap map){
        map.addAttribute("tours", service.getAllTeams());
        return "teams";
    }

}
