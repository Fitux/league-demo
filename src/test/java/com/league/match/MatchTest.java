package com.league.match;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.league.error.MatchParsingException;

import org.junit.jupiter.api.Test;

public class MatchTest {
    @Test
    public void matchNormalCase() {
        Match match;
        
        try {
            match = new Match("lions 3, hawks 3");
            assertEquals("lions", match.getLocalTeamName());
            assertEquals("hawks", match.getAwayTeamName());
            assertEquals(0, match.getResult());
        } catch (MatchParsingException e) {
            fail("Match parsing failed: " + e);
        }
    }

    @Test
    public void matchMoreThanOneDigitScores() {
        Match match;
        
        try {
            match = new Match("lions 300, hawks 3");
            assertEquals("lions", match.getLocalTeamName());
            assertEquals("hawks", match.getAwayTeamName());
            assertEquals(1, match.getResult());
        } catch (MatchParsingException e) {
            fail("Match parsing failed: " + e);
        }

        try {
            match = new Match("lions 3, hawks 300");
            assertEquals("lions", match.getLocalTeamName());
            assertEquals("hawks", match.getAwayTeamName());
            assertEquals(-1, match.getResult());
        } catch (MatchParsingException e) {
            fail("Match parsing failed: " + e);
        }

        try {
            match = new Match("lions 100, hawks 100");
            assertEquals("lions", match.getLocalTeamName());
            assertEquals("hawks", match.getAwayTeamName());
            assertEquals(0, match.getResult());
        } catch (MatchParsingException e) {
            fail("Match parsing failed: " + e);
        }
    }

    @Test
    public void matchNamesToLowerCase() {
        Match match;
        
        try {
            match = new Match("RanDom CAPitalIZatIoN 15, HAWKS 0");
            assertEquals("random capitalization", match.getLocalTeamName());
            assertEquals("hawks", match.getAwayTeamName());
            assertEquals(1, match.getResult());
        } catch (MatchParsingException e) {
            fail("Match parsing failed: " + e);
        }
    }

    @Test
    public void matchBothTeamsWithSameName() {        
        try {
            new Match(" sunders 15,    SuNDERs   0");
            fail("Match parsing failed: Should not accept both teams having the same name");
        } catch (MatchParsingException e) {
            assertEquals("Error: Match {  sunders 15,    SuNDERs   0 } both teams can't have the same name", e.getMessage());
        }
    }

    @Test
    public void matchNamesWithSpaces() {
        Match match;
        
        try {
            match = new Match("random name 1, purple purple purple 5");
            assertEquals("random name", match.getLocalTeamName());
            assertEquals("purple purple purple", match.getAwayTeamName());
            assertEquals(-1, match.getResult());
        } catch (MatchParsingException e) {
            fail("Match parsing failed: " + e);
        }
    }

    @Test
    public void matchWithRandomSpaces() {
        Match match;
        
        try {
            match = new Match("   random      name     6   , purple   purple  purple     20  ");
            assertEquals("random name", match.getLocalTeamName());
            assertEquals("purple purple purple", match.getAwayTeamName());
            assertEquals(-1, match.getResult());
        } catch (MatchParsingException e) {
            fail("Match parsing failed: " + e);
        }
    }

    @Test
    public void matchWithNegativeScores() {   
        try {
            new Match("name -6, purple 20");
            fail("Match parsing failed: Shoult not accept negatives");
        } catch (MatchParsingException e) {
            assertEquals("Error: Match { name -6, purple 20 } scores can't be negative", e.getMessage());
        }

        try {
            new Match("name 6, purple -20");
            fail("Match parsing failed: Shoult not accept negatives");
        } catch (MatchParsingException e) {
            assertEquals("Error: Match { name 6, purple -20 } scores can't be negative", e.getMessage());
        }
    }

    @Test
    public void matchLocalWins() {
        Match match;
        
        try {
            match = new Match("SSss 7, aAaA 6");
            assertEquals(1, match.getResult());
        } catch (MatchParsingException e) {
            fail("Match parsing failed: " + e);
        }
    }

    public void matchAwayWins() {
        Match match;
        
        try {
            match = new Match("random1 1, random2 3");
            assertEquals(-1, match.getResult());
        } catch (MatchParsingException e) {
            fail("Match parsing failed: " + e);
        }
    }

    public void matchLocalAndAwayTie() {
        Match match;
        
        try {
            match = new Match("Reds FC 3, BLUE 3");
            assertEquals(0, match.getResult());
        } catch (MatchParsingException e) {
            fail("Match parsing failed: " + e);
        }
    }
}
