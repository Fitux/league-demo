package com.league.match;

public interface IMatch {
    //Function to know the local team name
    String getLocalTeamName();

    //Function to know the away team name
    String getAwayTeamName();

    //Function to know the result of the match where 1 means a Win for the local team, 0 a Tie and -1 a Win for the away team
    int getResult();
}
