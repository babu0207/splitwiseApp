package com.company.splitwise;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ExpenseOperationImpl implements ExpenseOperation {

    @Override
    public void addEqualExpense(User user, InputBean inputBean){
        Double totalExpenseAmt = user.getUserExpenseDetail().getSpentAmout()+inputBean.getExpenseAmt();
        user.getUserExpenseDetail().setSpentAmout(totalExpenseAmt);
        double amountToBeAdded = inputBean.getExpenseAmt()/ inputBean.getRoommateCount();
        Map<String,Double> owesMap = user.getUserExpenseDetail().getUserOwesMap();
        for(String s: inputBean.getRoommatesId()) {
            Double temp = amountToBeAdded;
            if(!owesMap.isEmpty() && owesMap.containsKey(s)) {
                temp += owesMap.get(s);
            }
            owesMap.put(s,temp);
        }
        user.getUserExpenseDetail().setUserOwesMap(owesMap);
        //printEntry(user);
    }

    @Override
    public void addExactExpense(User user, InputBean inputBean) {
        Double totalExpenseAmt = user.getUserExpenseDetail().getSpentAmout()+inputBean.getExpenseAmt();
        user.getUserExpenseDetail().setSpentAmout(totalExpenseAmt);
        Map<String,Double> owesMap = user.getUserExpenseDetail().getUserOwesMap();
        for(int i = 0;i< inputBean.getRoommateCount();i++) {
            Double temp = inputBean.getRoommatesShare()[i];
            String id = inputBean.getRoommatesId()[i];
            if(!owesMap.isEmpty() && owesMap.containsKey(id)) {
                temp += owesMap.get(id);
            }
            owesMap.put(id,temp);
        }
        user.getUserExpenseDetail().setUserOwesMap(owesMap);
        //printEntry(user);
    }

    @Override
    public void addPercentExpense(User user, InputBean inputBean) {
        Double totalExpenseAmt = user.getUserExpenseDetail().getSpentAmout()+inputBean.getExpenseAmt();
        user.getUserExpenseDetail().setSpentAmout(totalExpenseAmt);
        Map<String,Double> owesMap = user.getUserExpenseDetail().getUserOwesMap();
        for(int i = 0;i< inputBean.getRoommateCount();i++) {
            Double temp = (inputBean.getExpenseAmt()/100)*inputBean.getRoommatesShare()[i];
            String id = inputBean.getRoommatesId()[i];
            if(!owesMap.isEmpty() && owesMap.containsKey(id)) {
                temp += owesMap.get(id);
            }
            owesMap.put(id,temp);
        }
        user.getUserExpenseDetail().setUserOwesMap(owesMap);
        //printEntry(user);
    }

    @Override
    public String showUserExpense(Map<String,User> usersMap) {
        System.out.println("show all expense");
        return null;
    }

    @Override
    public String showUserExpense(Map<String,User> usersMap,String uid) {
        ConcurrentHashMap<String,Double> owesMatrixMap = getOwesMatrixMap(usersMap);
        Set<String> keys = owesMatrixMap.keySet();
        for(String key: keys) {
            String[] temp = key.split("/");
            String tkey = temp[1]+"/"+temp[0];
            if(owesMatrixMap.containsKey(tkey)){
                Double kValue = owesMatrixMap.get(key);
                Double tkValue= owesMatrixMap.get(tkey);
                if( tkValue >= kValue) {
                    Double value = tkValue - kValue;
                    owesMatrixMap.put(tkey, value);
                    owesMatrixMap.remove(key);
                } else {
                    Double value = kValue - tkValue;
                    owesMatrixMap.put(key, value);
                    owesMatrixMap.remove(tkey);
                }
            }
        }
        printOwesMap(owesMatrixMap,uid);
        return null;
    }

    private ConcurrentHashMap<String,Double> getOwesMatrixMap(Map<String,User> usersMap){
        List<User> users = new ArrayList<>();
        ConcurrentHashMap<String,Double> owesMatrixMap = new ConcurrentHashMap<>();
        for(Map.Entry<String,User> e: usersMap.entrySet()){
            User user = e.getValue();
            for(Map.Entry<String,Double> q: user.getUserExpenseDetail().getUserOwesMap().entrySet()){
                String key = q.getKey()+"/"+user.getId();
                Double value =q.getValue();
                owesMatrixMap.put(key,value);
            }
        }
        return owesMatrixMap;

    }

    private void printEntry(User user){
        for(Map.Entry e : user.getUserExpenseDetail().getUserOwesMap().entrySet()){
            System.out.println(e.getKey() + " Owes "+ user.getId()+ " "+ e.getValue());
        }
    }
    private void printOwesMap(Map<String,Double> owesMap,String uid){
        for(Map.Entry<String,Double> e : owesMap.entrySet()){
            String[] temp = e.getKey().split("/");
            if(temp[0].equalsIgnoreCase(temp[1]))
                continue;
            if(uid == null) {
                System.out.println(temp[0] + " Owes " + temp[1] + " " + e.getValue());
            } else {
                if(e.getKey().contains(uid)){
                    System.out.println(temp[0] + " Owes " + temp[1] + " " + e.getValue());
                }
            }
        }
    }
}
