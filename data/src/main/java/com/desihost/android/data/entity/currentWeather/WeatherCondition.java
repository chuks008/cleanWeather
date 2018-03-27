package com.desihost.android.data.entity.currentWeather;

import com.google.gson.annotations.SerializedName;

public class WeatherCondition {

    @SerializedName("id")
    private int conditionId;

    @SerializedName("main")
    private String conditionName;

    @SerializedName("description")
    private String conditionDescription;

    @SerializedName("icon")
    private String conditionIcon;

    public int getConditionId() {
        return conditionId;
    }

    public void setConditionId(int conditionId) {
        this.conditionId = conditionId;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public String getConditionDescription() {
        return conditionDescription;
    }

    public void setConditionDescription(String conditionDescription) {
        this.conditionDescription = conditionDescription;
    }

    public String getConditionIcon() {
        return conditionIcon;
    }

    public void setConditionIcon(String conditionIcon) {
        this.conditionIcon = conditionIcon;
    }
}
