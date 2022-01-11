package com.league.stats;

public interface IStats extends Comparable<IStats> {
    void addWin();
    void addTie();
    void addLoss();
    int getPoints();
    String getTeamName();
    void setTeamName(String name);
}
