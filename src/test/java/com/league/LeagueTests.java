package com.league;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.league.error.MatchParsingException;
import com.league.factory.StatsFactory;
import com.league.match.Match;
import com.league.stats.Stats;

import org.junit.jupiter.api.Test;

public class LeagueTests {
    private static StatsFactory factory = new StatsFactory();

    @Test
    public void createNewLeague() {
        League<Match,Stats> league = new League<Match,Stats>(factory);

        assertEquals(0, league.getAllFinishedMatches().size());
        assertEquals(0, league.getCurrentStats().size());
        assertEquals("", league.getCurrentRankings());
    }

    @Test
    public void leagueCorrectNumberOfTeams() {
        League<Match,Stats> league = new League<Match,Stats>(factory);

        Match match1, match2, match3, match4, match5;

        try {
            match1 = new Match("lions 3, hawks 3");
            match2 = new Match("reds 3, hawks 3");
            match3 = new Match("lions 3, blues 3");
            match4 = new Match("random1 3, random2 3");
            match5 = new Match("random2 3, random1 3");

            assertEquals(0, league.getCurrentStats().size());
            league.addMatchResult(match1);
            assertEquals(2, league.getCurrentStats().size());
            league.addMatchResult(match2);
            assertEquals(3, league.getCurrentStats().size());
            league.addMatchResult(match3);
            assertEquals(4, league.getCurrentStats().size());
            league.addMatchResult(match4);
            assertEquals(6, league.getCurrentStats().size());
            league.addMatchResult(match5);
            assertEquals(6, league.getCurrentStats().size());
        } catch (MatchParsingException e) {
            fail("Failed during parsing of matches");
        }
    }

    @Test
    public void leagueCorrectNumberOfMatches() {
        League<Match,Stats> league = new League<Match,Stats>(factory);

        Match match1, match2, match3, match4, match5;

        try {
            match1 = new Match("lions 3, hawks 3");
            match2 = new Match("reds 3, hawks 3");
            match3 = new Match("lions 3, blues 3");
            match4 = new Match("random1 3, random2 3");
            match5 = new Match("random2 3, random1 3");

            assertEquals(0, league.getAllFinishedMatches().size());
            league.addMatchResult(match1);
            assertEquals(1, league.getAllFinishedMatches().size());
            league.addMatchResult(match2);
            assertEquals(2, league.getAllFinishedMatches().size());
            league.addMatchResult(match3);
            assertEquals(3, league.getAllFinishedMatches().size());
            league.addMatchResult(match4);
            assertEquals(4, league.getAllFinishedMatches().size());
            league.addMatchResult(match5);
            assertEquals(5, league.getAllFinishedMatches().size());
        } catch (MatchParsingException e) {
            fail("Failed during parsing of matches");
        }
    }

    // Ranking rules
    /*
        - If 2 or more teams have same rank, it will replace the following ranks depending on how many teams have the same ranks
            - I.e. Two rank 3 teams means there is no rank 4, we jump from 3 to 5
            - I.e. Four rank 3 teams means there is no rank 4,5,6, and we jump from 3 to 7
        - Points should be represented by the “# pts” string, except for cases where a team has 1 pt in which case it should be “# pt”.
        - Since this solution assumes input should be case sensitive the output names will be formatted lower case.
    */
    @Test
    public void leagueCorrectRankings() {
        League<Match,Stats> league = new League<Match,Stats>(factory);

        Match match1, match2, match3, match4, match5, match6, match7;

        try {
            match1 = new Match("lions 1, hawks 3");
            match2 = new Match("reds 3, hawks 5");
            match3 = new Match("lions 3, blues 3");
            match4 = new Match("random1 2, random2 3");
            match5 = new Match("random2 3, random1 2");
            match6 = new Match("reds 3, blues 3");
            match7 = new Match("aaa 0, blues 3");

            league.addMatchResult(match1);
            league.addMatchResult(match2);
            league.addMatchResult(match3);
            league.addMatchResult(match4);
            league.addMatchResult(match5);
            league.addMatchResult(match6);
            league.addMatchResult(match7);

            assertEquals("1. hawks, 6 pts\n1. random2, 6 pts\n3. blues, 5 pts\n4. lions, 1 pt\n4. reds, 1 pt\n6. aaa, 0 pts\n6. random1, 0 pts\n", league.getCurrentRankings());
        } catch (MatchParsingException e) {
            fail("Failed during parsing of matches");
        }
    }
}
