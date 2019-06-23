package com.client.web.Service;

import com.client.web.Model.Team;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("rest-service")
@RequestMapping("/teams")
@Component
public interface TeamService {
    @GetMapping
    Iterable<Team> getAllTeams();
}
