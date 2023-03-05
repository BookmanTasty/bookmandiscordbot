package org.leyvadev.bookmandiscordbot.dto;

public class ChampionMasteryDto {
    public int championId;
    public int championLevel;
    public int championPoints;
    public long lastPlayTime;
    public int championPointsSinceLastLevel;
    public int championPointsUntilNextLevel;
    public boolean chestGranted;
    public int tokensEarned;
    public String summonerId;

    public int getChampionId() {
        return championId;
    }

    public String getChampionIdString() {
        return String.valueOf(championId);
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public int getChampionLevel() {
        return championLevel;
    }

    public void setChampionLevel(int championLevel) {
        this.championLevel = championLevel;
    }

    public int getChampionPoints() {
        return championPoints;
    }

    public void setChampionPoints(int championPoints) {
        this.championPoints = championPoints;
    }

    public long getLastPlayTime() {
        return lastPlayTime;
    }

    public void setLastPlayTime(long lastPlayTime) {
        this.lastPlayTime = lastPlayTime;
    }

    public int getChampionPointsSinceLastLevel() {
        return championPointsSinceLastLevel;
    }

    public void setChampionPointsSinceLastLevel(int championPointsSinceLastLevel) {
        this.championPointsSinceLastLevel = championPointsSinceLastLevel;
    }

    public int getChampionPointsUntilNextLevel() {
        return championPointsUntilNextLevel;
    }

    public void setChampionPointsUntilNextLevel(int championPointsUntilNextLevel) {
        this.championPointsUntilNextLevel = championPointsUntilNextLevel;
    }

    public boolean isChestGranted() {
        return chestGranted;
    }

    public void setChestGranted(boolean chestGranted) {
        this.chestGranted = chestGranted;
    }

    public int getTokensEarned() {
        return tokensEarned;
    }

    public void setTokensEarned(int tokensEarned) {
        this.tokensEarned = tokensEarned;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }
}
