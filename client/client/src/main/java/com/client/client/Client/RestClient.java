package com.client.client.Client;

import com.client.client.Model.Log;
import com.client.client.Model.Player;
import com.client.client.Model.Team;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("rest-service")
public interface RestClient {
    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    Iterable<Team> getAllTeams();

    @GetMapping("/teams/{id}")
    Team getTeamById(@PathVariable Integer id);

    @GetMapping("/teams/delete/{id}")
    Boolean deleteTeam(@PathVariable Integer id);

    @PostMapping("/teams/create")
    Team createTeam(@RequestBody Team team);

    @PostMapping("teams/update/{id}")
    Team updateTeam(@PathVariable Integer id, @RequestBody Team team);

    @GetMapping("/players")
    Iterable<Player> getAllPlayers();

    @GetMapping("/players/{id}")
    Player getPlayerById(@PathVariable Integer id);

    @GetMapping("/players/delete/{id}")
    Boolean deletePlayer(@PathVariable Integer id);

    @PostMapping("/players/create")
    Player createPlayer(@RequestBody Player player);

    @PostMapping("/players/update/{id}")
    Player updatePlayer(@PathVariable Integer id, @RequestBody Player player);

    @GetMapping("/logs")
    List<Log> getLogs();
}
