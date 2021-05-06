package com.customer.rewardbackend.service;

import com.customer.rewardbackend.dto.TotalRewardsDTO;
import com.customer.rewardbackend.model.Rewards;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface RewardsCalculationService {
    TotalRewardsDTO getCustomerRewards(String customerName);

    void calculateRewards(List<Rewards> list);

    void generateSampleDataSet() throws ParseException;
}
