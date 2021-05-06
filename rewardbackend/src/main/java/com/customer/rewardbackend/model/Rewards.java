package com.customer.rewardbackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Rewards {

    private String id;
    private String customerName;
    private Integer costOfPurchase;
    private Integer pointsEarned;
    private Date purchaseDate;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCostOfPurchase() {
        return costOfPurchase;
    }

    public void setCostOfPurchase(Integer costOfPurchase) {
        this.costOfPurchase = costOfPurchase;
    }

    public Integer getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(Integer pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }
}
