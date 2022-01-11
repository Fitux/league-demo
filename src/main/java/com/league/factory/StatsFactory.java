package com.league.factory;

import com.league.stats.Stats;

public class StatsFactory implements IStatsFactory<Stats> {

    @Override
    public Stats createInstance() {
        return new Stats();
    }
    
}
