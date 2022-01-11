package com.league;

import java.util.List;

import com.league.match.IMatch;
import com.league.stats.IStats;

public interface ILeague <T extends IMatch, K extends IStats> {
    // Function to update League by adding a match result
    public void addMatchResult(T match);
    
    // Function to get current stats of all the teams
    public List<K> getCurrentStats();

    // Function to get all finished matches results of these league
    public List<T> getAllFinishedMatches();

    // Function to get the current rankings of all the teams in the league
    public String getCurrentRankings();

    // There can be other functions here like
    
    // Function to get team information like, pts, previous matches, total score, etc
    // public String getTeamData(String teamName);
}
