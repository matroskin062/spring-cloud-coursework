package com.client.client.Controller;

import com.client.client.Client.RestClient;
import com.client.client.Model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    RestClient restClient;

    @GetMapping
    public String getAllTeams(ModelMap model){
        model.addAttribute("teams", restClient.getAllTeams());
        model.addAttribute("isAdmin", crudAuthorized());
        return "teams";
    }

    @GetMapping("/{id}")
    public ModelAndView getTeamById(ModelAndView model, @PathVariable Integer id){
        model.addObject("isAdmin", crudAuthorized());
        model.setViewName("teamInfo");
        model.addObject("team", restClient.getTeamById(id));
        return model;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteTeam(@PathVariable Integer id){
        restClient.deleteTeam(id);
        return new ModelAndView("redirect:/teams");
    }

    @PostMapping("/create")
    public ModelAndView createTeam(@ModelAttribute Team team){
        restClient.createTeam(team);
        return new ModelAndView("redirect:/teams");
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateTeam(@PathVariable Integer id, @ModelAttribute Team team){
        restClient.updateTeam(id, team);
        return new ModelAndView("redirect:/teams");
    }

    private boolean crudAuthorized(){
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> ((GrantedAuthority) a).getAuthority().equals("ROLE_ADMIN"));
    }
}
