package com.example.myfitnessbuddy;

import java.util.ArrayList;

public class Statistics {
    StatisticsInfo statistics;

    public static Statistics createStatisticsInstance()
    {
        return new Statistics();
    }

    public StatisticsInfo getStatistics() {
        return statistics;
    }

    public ArrayList<String> calculateNewGoals(StatisticsInfo si)
    {
        return new ArrayList<String>();
    }

    public void calculateTimeUntilAchievingGoals(ArrayList<String> goals)
    {}

    public void calculateMistakes()
    {}

    public void proposeChangesForMistakes(ArrayList<String> mistakes)
    {}

    public void calculateFuturePersonalRecords()
    {}

    public ArrayList<String> calculatePossibleMistakes()
    {
        return new ArrayList<String>();
    }
}
