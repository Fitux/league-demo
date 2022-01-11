package com.league.stats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

public class StatsTests {
    @Test
    public void newStatsProperties() {
        Stats stat = new Stats();
        assertEquals("", stat.getTeamName());
        assertEquals(0, stat.getPoints());
    }

    @Test
    public void statSetName() {
        Stats stat = new Stats();
        stat.setTeamName("Random");
        assertEquals("Random", stat.getTeamName());
        stat.setTeamName("FC Press.");
        assertEquals("FC Press.", stat.getTeamName());
        stat.setTeamName("CaseSensitive");
        assertNotEquals("casesensitive", stat.getTeamName());
    }

    @Test
    public void statSimpleAddWin() {
        Stats stat = new Stats();
        stat.addWin();
        assertEquals(3, stat.getPoints());
        stat.addWin();
        assertEquals(6, stat.getPoints());
        stat.addWin();
        assertEquals(9, stat.getPoints());
    }

    @Test
    public void statSimpleAddTie() {
        Stats stat = new Stats();
        stat.addTie();
        assertEquals(1, stat.getPoints());
        stat.addTie();
        assertEquals(2, stat.getPoints());
        stat.addTie();
        assertEquals(3, stat.getPoints());
    }

    @Test
    public void statSimpleAddLoss() {
        Stats stat = new Stats();
        stat.addLoss();
        assertEquals(0, stat.getPoints());
        stat.addLoss();
        assertEquals(0, stat.getPoints());
        stat.addLoss();
        assertEquals(0, stat.getPoints());
    }

    @Test
    public void statRandomOperations() {
        Stats stat = new Stats();
        stat.addLoss();
        assertEquals(0, stat.getPoints());
        stat.addWin();
        assertEquals(3, stat.getPoints());
        stat.addWin();
        assertEquals(6, stat.getPoints());
        stat.addTie();
        assertEquals(7, stat.getPoints());
        stat.addTie();
        assertEquals(8, stat.getPoints());
        stat.addWin();
        assertEquals(11, stat.getPoints());
        stat.addLoss();
        assertEquals(11, stat.getPoints());
        stat.addTie();
        assertEquals(12, stat.getPoints());
        stat.addLoss();
        assertEquals(12, stat.getPoints());
    }

    @Test
    public void statCompares() {
        Stats stat1 = new Stats();
        Stats stat2 = new Stats();
        Stats stat3 = new Stats();
        Stats stat4 = new Stats();

        stat1.addWin();
        stat1.addTie();
        assertEquals(4, stat1.getPoints());

        stat2.addWin();
        stat2.addTie();
        stat2.addLoss();
        assertEquals(4, stat2.getPoints());

        stat3.addWin();
        stat3.addLoss();
        assertEquals(3, stat3.getPoints());

        stat4.addWin();
        stat4.addLoss();
        stat4.addWin();
        assertEquals(6, stat4.getPoints());

        assertEquals(0, stat1.compareTo(stat2));
        assertEquals(0, stat2.compareTo(stat1));

        assertEquals(1, stat1.compareTo(stat3));
        assertEquals(-1, stat3.compareTo(stat1));

        assertEquals(-1, stat1.compareTo(stat4));
        assertEquals(1, stat4.compareTo(stat1));
    }
}
