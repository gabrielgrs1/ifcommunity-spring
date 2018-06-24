package com.br.ifcommunity.model;

import java.util.Objects;

public class Chart {
    private String programmingLanguage;
    private int countManyPostsTime;
    private String matterName;
    private int likes;
    private int deslikes;


    public Chart() {
    }

    public Chart(String matterName) {
        this.matterName = matterName;
    }

    public Chart(int likes, int deslikes) {
        this.likes = likes;
        this.deslikes = deslikes;
    }

    public Chart(String programmingLanguage, int countManyPostsTime, String matterName) {
        this.programmingLanguage = programmingLanguage;
        this.countManyPostsTime = countManyPostsTime;
        this.matterName = matterName;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public int getCountManyPostsTime() {
        return countManyPostsTime;
    }

    public void setCountManyPostsTime(int countManyPostsTime) {
        this.countManyPostsTime = countManyPostsTime;
    }

    public String getMatterName() {
        return matterName;
    }

    public void setMatterName(String matterName) {
        this.matterName = matterName;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDeslikes() {
        return deslikes;
    }

    public void setDeslikes(int deslikes) {
        this.deslikes = deslikes;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "programmingLanguage='" + programmingLanguage + '\'' +
                ", countManyPostsTime=" + countManyPostsTime +
                ", matterName='" + matterName + '\'' +
                ", likes=" + likes +
                ", deslikes=" + deslikes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chart chart = (Chart) o;
        return countManyPostsTime == chart.countManyPostsTime &&
                likes == chart.likes &&
                deslikes == chart.deslikes &&
                Objects.equals(programmingLanguage, chart.programmingLanguage) &&
                Objects.equals(matterName, chart.matterName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(programmingLanguage, countManyPostsTime, matterName, likes, deslikes);
    }
}
