package com.league;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import com.league.error.MatchParsingException;
import com.league.factory.StatsFactory;
import com.league.match.Match;
import com.league.stats.Stats;

public class App {
    public static void main(String[] args) throws Exception {
        StatsFactory factory = new StatsFactory();
        League<Match,Stats> league = new League<Match,Stats>(factory);
        
        Scanner scanner = new Scanner(System.in);

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = br.readLine()) != null) {
               Match match = new Match(line);
               league.addMatchResult(match);
            }
        } catch (MatchParsingException ex) {
            System.out.println("Error while parsing matches: " + ex);
        }

        System.out.println(league.getCurrentRankings());
        System.out.println("Press ENTER to exit");
        scanner.nextLine();
        scanner.close();
    }
}
