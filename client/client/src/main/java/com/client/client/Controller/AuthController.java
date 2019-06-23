package com.client.client.Controller;

import com.client.client.Model.Authorities;
import com.client.client.Model.Users;
import com.client.client.Service.AuthService;
import com.client.client.Service.RoleService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {
    @Autowired
    AuthService userRep;

    @Autowired
    RoleService roleRep;

    @GetMapping(value = "/")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        modelMap.addAttribute("isAdmin", crudAuthorized());
        return "index";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView model, String error, String logout){
        model.setViewName("login");
        if(error != null)
            model.addObject("errorMsg", "Invalid username or pass");
        return model;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(String error, ModelAndView model){
        model.setViewName("registration");
        if(error != null)
            model.addObject("errorMsg", "invalid username or pass");

        return model;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration_post(ModelAndView model, @ModelAttribute Users users, String role){
        model.setViewName("registration");
        Users usersFromDb = userRep.findByUsername(users.getUsername());
        if(usersFromDb !=null){
            model.addObject("errorMsg", "User exist");
            return model;
        }else if(!users.getPassword().isEmpty()&(!users.getUsername().isEmpty())){
            users.setEnabled(true);
            userRep.save(users);
            Authorities a = new Authorities(users.getUsername(), "ROLE_"+role.toUpperCase());
            roleRep.save(a);
        }
        return new ModelAndView("redirect:/login");
    }

    private boolean crudAuthorized(){
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> ((GrantedAuthority) a).getAuthority().equals("ROLE_ADMIN"));
    }
}
