package com.league.stats;

public class Stats implements IStats {
    private String teamName;
    private int wins;
    private int losses;
    private int ties;

    public Stats () {
        this.teamName = "";
        wins = 0;
        losses = 0;
        ties = 0;
    }

    // Here we could add the properties for goals scored and goals received or others
    @Override
    public void addWin() {
        wins += 1;
    }
    
    @Override
    public void addTie() {
        ties += 1;
    }

    @Override
    public void addLoss() {
        losses += 1;
    }

    /*
    - Rules
        - Tie is 1 point
        - Victory is 3 points
        - Loss is 0 points
    */
    @Override
    public int getPoints() {
        return (wins * 3) + (ties * 1) + (losses * 0);
    }

    @Override
    public String getTeamName() {
        return teamName;
    }

    /*
    - Rules
        - Order by points
        - If same points then then the order is alphabetical
    */
    @Override
    public int compareTo(IStats o) {
        if(this.getPoints() == o.getPoints()) {
            return o.getTeamName().compareTo(this.getTeamName());
        }

        return Integer.compare(this.getPoints(), o.getPoints());
    }

    @Override
    public void setTeamName(String name) {
        this.teamName = name;
    }
}
