package com.league;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.league.factory.IStatsFactory;
import com.league.match.IMatch;
import com.league.stats.IStats;

public class League<T extends IMatch, K extends IStats> implements ILeague<T,K> {
    private IStatsFactory<K> statsFactory;
    
    private List<T> matchHistory;
    private HashMap<String,K> teamStats;
    
    public League(IStatsFactory<K> statsFactory) {
        this.statsFactory = statsFactory;
        matchHistory = new ArrayList<T>();
        teamStats = new LinkedHashMap<String,K>();
    }

    @Override
    public void addMatchResult(T match) {
        String localTeam, awayTeam;

        localTeam = match.getLocalTeamName();
        awayTeam = match.getAwayTeamName();

        if (teamStats.get(localTeam) == null) {
            teamStats.put(localTeam, statsFactory.createInstance());
            teamStats.get(localTeam).setTeamName(localTeam);
        }

        if (teamStats.get(awayTeam) == null) {
            teamStats.put(awayTeam, statsFactory.createInstance());
            teamStats.get(awayTeam).setTeamName(awayTeam);
        }

        if (match.getResult() > 0) {
            teamStats.get(localTeam).addWin();
            teamStats.get(awayTeam).addLoss();
        } else if (match.getResult() < 0) {
            teamStats.get(localTeam).addLoss();
            teamStats.get(awayTeam).addWin();
        } else {
            teamStats.get(localTeam).addTie();
            teamStats.get(awayTeam).addTie();
        }

        matchHistory.add(match);
    }

    @Override
    public List<K> getCurrentStats() {
        return new ArrayList<K>(this.teamStats.values());
    }

    @Override
    public List<T> getAllFinishedMatches() {
        return this.matchHistory;
    }

    // Ranking rules
    /*
        - If 2 or more teams have same rank, it will replace the following ranks depending on how many teams have the same ranks
            - I.e. Two rank 3 teams means there is no rank 4, we jump from 3 to 5
            - I.e. Four rank 3 teams means there is no rank 4,5,6, and we jump from 3 to 7
        - Points should be represented by the “# pts” string, except for cases where a team has 1 pt in which case it should be “# pt”.
        - Since this solution assumes input should be case sensitive the output names will be formatted lower case.
    */
    @Override
    public String getCurrentRankings() {
        StringBuilder stringBuilder = new StringBuilder("");

        List<K> stats = getCurrentStats();

        Collections.sort(stats, Collections.reverseOrder());

        int rank = 0;
        int realRank = 0;
        int rankPoints = -1;
        int teamPoints;
        for (K stat : stats) {
            teamPoints = stat.getPoints();
            realRank++;

            if(teamPoints != rankPoints) {
                rank = realRank;
                rankPoints = teamPoints;
            } 

            stringBuilder.append(rank).append(". ").append(stat.getTeamName()).append(", ").append(teamPoints);

            if(teamPoints == 1) {
                stringBuilder.append(" pt\n");
            } else {
                stringBuilder.append(" pts\n");
            }
        }

        return stringBuilder.toString();
    }
}
