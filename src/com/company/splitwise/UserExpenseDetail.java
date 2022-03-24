package com.company.splitwise;

import java.util.HashMap;
import java.util.Map;

public class UserExpenseDetail {
    private Double spentAmout;
    private Map<String,Double> userOwesMap;

    public UserExpenseDetail(){
        this.userOwesMap = new HashMap<>();
        this.spentAmout = 0.0;
    }

    public Double getSpentAmout() {
        return spentAmout;
    }

    public void setSpentAmout(Double spentAmout) {
        this.spentAmout = spentAmout;
    }

    public Map<String, Double> getUserOwesMap() {
        return userOwesMap;
    }

    public void setUserOwesMap(Map<String, Double> userOwesMap) {
        this.userOwesMap = userOwesMap;
    }
}
