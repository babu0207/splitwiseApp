package com.company.splitwise;

public class User {
    private String id;
    private String name;
    private String email;
    private String mobile;
    private UserExpenseDetail userExpenseDetail;

    public User(String id, String name, String email, String mobile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public UserExpenseDetail getUserExpenseDetail() {
        return userExpenseDetail;
    }

    public void setUserExpenseDetail(UserExpenseDetail userExpenseDetail) {
        this.userExpenseDetail = userExpenseDetail;
    }
}
