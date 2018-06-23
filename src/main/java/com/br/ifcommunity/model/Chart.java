package com.br.ifcommunity.model;

import java.util.Objects;

public class Chart {
    private String programmingLanguage;
    private int countManyPostsTime;
    private String matterName;

    public Chart() {
    }

    public Chart(String matterName) {
        this.matterName = matterName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chart chart = (Chart) o;
        return countManyPostsTime == chart.countManyPostsTime &&
                Objects.equals(programmingLanguage, chart.programmingLanguage) &&
                Objects.equals(matterName, chart.matterName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(programmingLanguage, countManyPostsTime, matterName);
    }

    @Override
    public String toString() {
        return "Chart{" +
                "programmingLanguage='" + programmingLanguage + '\'' +
                ", countManyPostsTime=" + countManyPostsTime +
                ", matterName='" + matterName + '\'' +
                '}';
    }

}
