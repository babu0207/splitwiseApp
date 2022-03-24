package com.company.splitwise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.DoubleStream;

public class SplitwiseController {
    private ExpenseOperation expenseOperation;
    private Map<String,User> usersMap;

    public SplitwiseController() {
        usersMap = new HashMap<>();
        User u = new User("U1","arjun","a@b.g.com","922341");
        u.setUserExpenseDetail(new UserExpenseDetail());
        usersMap.put("U1",u);
        u = new User("U2","arvind","a@b.g.com","922341");
        u.setUserExpenseDetail(new UserExpenseDetail());
        usersMap.put("U2",u);
        u = new User("U3","vineet","a@b.g.com","922341");
        u.setUserExpenseDetail(new UserExpenseDetail());
        usersMap.put("U3",u);
        u = new User("U4","kashu","a@b.g.com","922341");
        u.setUserExpenseDetail(new UserExpenseDetail());
        usersMap.put("U4",u);
        expenseOperation = new ExpenseOperationImpl();
    }

    String processInput(String input){
        String output = "Please enter your input.";
        if(input.isEmpty()){
            return output;
        }
        String[] inputArr = input.split(" ");
        if(inputArr.length ==1){
            if(inputArr[0].equals("SHOW")){
                expenseOperation.showUserExpense(usersMap,null);
            } else {
                output = "Invalid input";
                return output;
            }
        } else {
            InputBean inputBean = parseInput(inputArr);
            if(inputBean.getOperationType().equalsIgnoreCase("show")){
                expenseOperation.showUserExpense(usersMap,inputBean.getUserId());

            } else {
                if(inputBean.getAction().equals("PERCENT") && !isSum(100.00,inputBean.getRoommatesShare())){
                    output = "Invalid Entry: Percent share is not 100%";
                }
                if(inputBean.getAction().equals("EXACT") && !isSum(inputBean.getExpenseAmt(),inputBean.getRoommatesShare())){
                    output = "Invalid Entry: Mismatch in exact share in expense amount";
                }
                performAction(inputBean);
            }

        }
        return output;

    }

    InputBean parseInput(String[] inputArr) {
        InputBean input = new InputBean(inputArr);
        return input;
    }

    boolean isSum(Double value, Double arr[]){
        double sum=0.0;
        for(Double d :arr){
            sum+= d;
        }
        return (value == sum);
    }
    void performAction(InputBean inputBean) {
        if(inputBean.getOperationType().equalsIgnoreCase("expense")){
            switch(inputBean.getAction()){
                case "EQUAL":
                    expenseOperation.addEqualExpense(usersMap.get(inputBean.getUserId()), inputBean);
                    break;

                case "PERCENT":
                    expenseOperation.addPercentExpense(usersMap.get(inputBean.getUserId()), inputBean);
                    break;
                case "EXACT":
                    expenseOperation.addExactExpense(usersMap.get(inputBean.getUserId()), inputBean);
                    break;
                default:
                    break;
            }
        }
    }

}
