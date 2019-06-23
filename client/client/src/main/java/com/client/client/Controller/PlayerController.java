package com.client.client.Controller;

import com.client.client.Client.RestClient;
import com.client.client.Model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    RestClient restClient;

    @GetMapping
    public String getAllPlayers(ModelMap model){
        model.addAttribute("isAdmin", crudAuthorized());
        model.addAttribute("players", restClient.getAllPlayers());
        return "players";
    }

    @GetMapping("/{id}")
    public ModelAndView getPlayerById(ModelAndView model, @PathVariable Integer id){
        model.addObject("isAdmin", crudAuthorized());
        model.setViewName("playerInfo");
        model.addObject("player", restClient.getPlayerById(id));
        return model;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deletePlayer(@PathVariable Integer id){
        restClient.deletePlayer(id);
        return new ModelAndView("redirect:/players");
    }

    @PostMapping("/create")
    public ModelAndView createPlayer(@ModelAttribute Player player){
        restClient.createPlayer(player);
        return new ModelAndView("redirect:/players");
    }

    @PostMapping("/update/{id}")
    public ModelAndView updatePlayer(@PathVariable Integer id, @ModelAttribute Player player){
        restClient.updatePlayer(id, player);
        return new ModelAndView("redirect:/players");
    }

    private boolean crudAuthorized(){
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> ((GrantedAuthority) a).getAuthority().equals("ROLE_ADMIN"));
    }

}
