package com.customer.rewardbackend.dto;

public class RewardsDTO {
    private Integer monthofYear;
    private Integer totalPointForTheMonth;

    public Integer getCurrentMonth() {
        return monthofYear;
    }

    public void setCurrentMonth(Integer month) {
        this.monthofYear = month;
    }

    public Integer getTotalPointForTheMonth() {
        return totalPointForTheMonth;
    }

    public void setTotalPointForTheMonth(Integer monthPoints) {
        this.totalPointForTheMonth = totalPointForTheMonth;
    }
}
