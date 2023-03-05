package org.leyvadev.bookmandiscordbot.dto;

import java.util.List;

public class Team{
    public List<Object> bans;
    public Objectives objectives;
    public int teamId;
    public boolean win;

    public List<Object> getBans() {
        return bans;
    }

    public void setBans(List<Object> bans) {
        this.bans = bans;
    }

    public Objectives getObjectives() {
        return objectives;
    }

    public void setObjectives(Objectives objectives) {
        this.objectives = objectives;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}
