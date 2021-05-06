package com.customer.rewardbackend.controller;

import com.customer.rewardbackend.dto.TotalRewardsDTO;
import com.customer.rewardbackend.service.RewardsCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RewardsBackendController {
    @Autowired
    private RewardsCalculationService rewardsCalculationService;

    @GetMapping("/getRewards")
    public TotalRewardsDTO getCustomerRewards(String name) throws ParseException {
        return rewardsCalculationService.getCustomerRewards(name);
    }

    @GetMapping("/getTestData")
    public void generateTestSet() throws ParseException {
        rewardsCalculationService.generateSampleDataSet();
    }
}
