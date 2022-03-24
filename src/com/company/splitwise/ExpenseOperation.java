package com.company.splitwise;

import java.util.List;
import java.util.Map;

public interface ExpenseOperation {
    void addEqualExpense(User users, InputBean inputBean);
    void addExactExpense(User users, InputBean inputBean);
    void addPercentExpense(User users, InputBean inputBean);
    String showUserExpense(Map<String,User> usersMap);
    String showUserExpense(Map<String,User> usersMap, String uid);

}
