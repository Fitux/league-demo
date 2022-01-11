package com.league.match;

import com.league.error.MatchParsingException;

public class Match implements IMatch {
    private String localTeam;
    private int localScore;
    private String awayTeam;
    private int awayScore;
    private int result;

    // Constructor in charge of parsing the string to get the match results
    /*
    - Rules
        - Only two team scores
        - Different names
        - Names to lower case
        - Discard repeated spaces
        - No negative scores
        - Results
            - 1 -> Win to local team if score is > than away team
            - 0 -> Tie between both teams if scores are equal
            - -1 -> Win to away team if score is > than local team
    */
    public Match (String matchString) throws MatchParsingException {
        String[] teamScores = matchString.trim().split(",");

        if(teamScores.length != 2) {
            throw new MatchParsingException("Error: Match { " + matchString + " } does not contain two results");
        }

        localTeam = teamScores[0].trim().substring(0, teamScores[0].trim().lastIndexOf(" ")).replaceAll(" +", " ").trim().toLowerCase();
        awayTeam = teamScores[1].trim().substring(0, teamScores[1].trim().lastIndexOf(" ")).replaceAll(" +", " ").trim().toLowerCase();

        if(localTeam.equals(awayTeam)) {
            throw new MatchParsingException("Error: Match { " + matchString + " } both teams can't have the same name");
        }

        try {
            localScore = Integer.parseInt(teamScores[0].trim().substring(teamScores[0].trim().lastIndexOf(" ")+1));
            awayScore = Integer.parseInt(teamScores[1].trim().substring(teamScores[1].trim().lastIndexOf(" ")+1));

            if(localScore < 0 || awayScore < 0) {
                throw new MatchParsingException("Error: Match { " + matchString + " } scores can't be negative");
            }
        } catch (NumberFormatException ex) {
            throw new MatchParsingException("Error: Match { " + matchString + " } scores can't be parsed: " + ex.getMessage());
        }
        
        // Code where the results are saved depending on the scores of the teams
        if (localScore > awayScore) {
            result = 1;
        } else if (localScore < awayScore) {
            result = -1;
        } else {
            result = 0;
        }
    }

    @Override
    public String getLocalTeamName() {
        return localTeam;
    }

    @Override
    public String getAwayTeamName() {
        return awayTeam;
    }

    //Function to know the result of the match where 1 means a Win for the local team, 0 a Tie and -1 a Win for the away team
    @Override
    public int getResult() {
        return result;
    }
}
