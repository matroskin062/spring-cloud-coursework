package com.client.client.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String teamName;

    @Column(nullable = false)
    private String league;

    @Column(nullable = false)
    private int position;

    @Column
    private String coach;

    @JsonIgnore
    @Column(name = "presenceFlag", nullable = false)
    private boolean presenceFlag = true;

    public Team(String teamName, String league, int position, String coach) {
        this.teamName = teamName;
        this.league = league;
        this.position = position;
        this.coach = coach;
    }

    public Team() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public boolean isPresenceFlag() {
        return presenceFlag;
    }

    public void setPresenceFlag(boolean presenceFlag) {
        this.presenceFlag = presenceFlag;
    }
}