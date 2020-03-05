package me.pabloestrada.api.dto;

public class ExerciseSummaryDTO {
    public int lengthOfStreakInDays;
    public float milesRanToday;
    public int numberOfMinutesInGym;

    public ExerciseSummaryDTO(int lengthOfStreakInDays, float milesRanToday, int numberOfMinutesInGym) {
        this.lengthOfStreakInDays = lengthOfStreakInDays;
        this.milesRanToday = milesRanToday;
        this.numberOfMinutesInGym = numberOfMinutesInGym;
    }

    public int getLengthOfStreakInDays() {
        return lengthOfStreakInDays;
    }

    public void setLengthOfStreakInDays(int lengthOfStreakInDays) {
        this.lengthOfStreakInDays = lengthOfStreakInDays;
    }

    public float getMilesRanToday() {
        return milesRanToday;
    }

    public void setMilesRanToday(float milesRanToday) {
        this.milesRanToday = milesRanToday;
    }

    public int getNumberOfMinutesInGym() {
        return numberOfMinutesInGym;
    }

    public void setNumberOfMinutesInGym(int numberOfMinutesInGym) {
        this.numberOfMinutesInGym = numberOfMinutesInGym;
    }
}
