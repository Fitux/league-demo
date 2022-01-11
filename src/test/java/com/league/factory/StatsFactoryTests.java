package com.league.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.league.stats.Stats;

import org.junit.jupiter.api.Test;

public class StatsFactoryTests {
    
    @Test
    public void createStatInstance() {
        StatsFactory factory = new StatsFactory();
        Stats stat = factory.createInstance();

        assertEquals("", stat.getTeamName());
        assertEquals(0, stat.getPoints());
    }
}
