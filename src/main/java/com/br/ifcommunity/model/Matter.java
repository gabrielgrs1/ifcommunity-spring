package com.br.ifcommunity.model;

import java.io.Serializable;
import java.util.Objects;

public class Matter implements Serializable {

    private int matterId;
    private String matterName;
    private int period;

    public Matter(int matterId, String matterName, int period) {
        this.matterId = matterId;
        this.matterName = matterName;
        this.period = period;
    }

    public int getMatterId() {
        return matterId;
    }

    public void setMatterId(int matterId) {
        this.matterId = matterId;
    }

    public String getMatterName() {
        return matterName;
    }

    public void setMatterName(String matterName) {
        this.matterName = matterName;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }


    @Override
    public String toString() {
        return "Matter{" +
                "matterId=" + matterId +
                ", matterName='" + matterName + '\'' +
                ", period=" + period +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matter matter = (Matter) o;
        return matterId == matter.matterId &&
                period == matter.period &&
                Objects.equals(matterName, matter.matterName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(matterId, matterName, period);
    }
}
