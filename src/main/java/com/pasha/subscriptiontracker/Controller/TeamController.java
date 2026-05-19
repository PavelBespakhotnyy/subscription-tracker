package com.pasha.subscriptiontracker.Controller;

import com.pasha.subscriptiontracker.Database.DAO.TeamDAO;
import com.pasha.subscriptiontracker.Model.Team;

public class TeamController {
    private TeamDAO teamDAO = new TeamDAO();

    public boolean create(Team team) {
        return teamDAO.create(team);
    }

    public Team getByInviteCode(String code) {
        return teamDAO.getByInviteCode(code);
    }
}
