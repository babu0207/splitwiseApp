package com.company.splitwise;

import java.util.Locale;

public class InputBean {
    private String operationType;
    private String userId;
    private Double expenseAmt;
    private int roommateCount;
    private String[] roommatesId;
    private String action;
    private Double[] roommatesShare;


    public InputBean(String[] inputArr) {

        int beg = 0;
        if(inputArr.length <= 2){
            operationType = inputArr[0];
            if(inputArr.length == 2) userId = inputArr[1];
        } else {
            operationType = inputArr[beg++];
            userId = inputArr[beg++].toUpperCase();
            expenseAmt = Double.parseDouble(inputArr[beg++]);
            roommateCount = Integer.parseInt(inputArr[beg++]);
            roommatesId = new String[roommateCount];
            roommatesShare = new Double[roommateCount];
            int counter = 1;
            int i = 0;
            while (counter <= roommateCount) {
                roommatesId[i] = inputArr[beg++].toUpperCase();
                i++;
                counter++;
            }
            action = inputArr[beg++];
            if (!action.equals("EQUAL")) {
                i = 0;
                int rep = 1;
                while (rep <= roommateCount) {
                    roommatesShare[i] = Double.parseDouble(inputArr[beg++]);
                    rep++;
                    i++;
                }
            }
        }
    }

    public String getOperationType() {
        return operationType;
    }

    public String getUserId() {
        return userId;
    }

    public Double getExpenseAmt() {
        return expenseAmt;
    }

    public int getRoommateCount() {
        return roommateCount;
    }

    public String[] getRoommatesId() {
        return roommatesId;
    }

    public Double[] getRoommatesShare() {
        return roommatesShare;
    }

    public String getAction() {
        return action;
    }
}
