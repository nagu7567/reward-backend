package com.customer.rewardbackend.service.Impl;

import com.customer.rewardbackend.Repository.RewardsRepository;
import com.customer.rewardbackend.dto.RewardsDTO;
import com.customer.rewardbackend.dto.TotalRewardsDTO;
import com.customer.rewardbackend.model.Rewards;
import com.customer.rewardbackend.service.RewardsCalculationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RewardsCalculationServiceImpl implements RewardsCalculationService {
    @Autowired
    private RewardsRepository rewardsRepository;

    @Override
    public TotalRewardsDTO getCustomerRewards(String customerName) {
        List<Rewards> rewards = rewardsRepository.findByCustomerName(customerName);
        Map<Object, Integer> months = rewards.stream()
                .collect(Collectors.groupingBy(element -> element.getPurchaseDate().getMonth(), Collectors.summingInt(element -> element.getPointsEarned())));

        List<RewardsDTO> rewardsDTO = new ArrayList<RewardsDTO>();
        Long total = 0l;
        for (Map.Entry<Object, Integer> entry : months.entrySet()) {
            RewardsDTO dto = new RewardsDTO();
            dto.setCurrentMonth(((Integer) entry.getKey() + 1));
            Integer monthTotal = entry.getValue();
            total += monthTotal;
            dto.setTotalPointForTheMonth(monthTotal);

            rewardsDTO.add(dto);
        }

        TotalRewardsDTO customerDetails = new TotalRewardsDTO();
        customerDetails.setTotalPoints(total);
        customerDetails.setRewards(rewardsDTO);
        return customerDetails;
    }

    @Override
    public void calculateRewards(List<Rewards> list) {
        for (Rewards r : list) {
            Integer points = calculatePoints(r);
            r.setPointsEarned(points);
            rewardsRepository.save(r);
        }
    }

    private Integer calculatePoints(Rewards r) {
        Integer points = 0;
        Integer amount = r.getCostOfPurchase();
        if (amount <= 50) {
            return points;
        } else if (amount <= 100) {
            points += (amount - 50) * 1;
        } else {
            points += 50;
            points += (amount % 100) * 2;
        }

        return points;
    }

    @Override
    public void generateSampleDataSet() throws ParseException {
        List<Rewards> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //1st customer
        Rewards Nag = new Rewards();
        Nag.setCustomerName("Nag");
        Nag.setCostOfPurchase(120);
        Nag.setPurchaseDate(sdf.parse("05/05/2021"));

        Rewards Nag1 = new Rewards();
        Nag1.setCustomerName("Nag");
        Nag1.setCostOfPurchase(50);
        Nag1.setPurchaseDate(sdf.parse("05/04/2021"));

        list.add(Nag);
        list.add(Nag1);

        // 2nd Customer

        Rewards Arjun1 = new Rewards();
        Arjun1.setCustomerName("Arjun");
        Arjun1.setCostOfPurchase(100);
        Arjun1.setPurchaseDate(sdf.parse("04/29/2021"));

        Rewards Arjun2 = new Rewards();
        Arjun2.setCustomerName("Arjun");
        Arjun2.setCostOfPurchase(120);
        Arjun2.setPurchaseDate(sdf.parse("04/28/2021"));

        Rewards Arjun3 = new Rewards();
        Arjun3.setCustomerName("Arjun");
        Arjun3.setCostOfPurchase(130);
        Arjun3.setPurchaseDate(sdf.parse("04/27/2021"));

        Rewards Arjun4 = new Rewards();
        Arjun4.setCustomerName("Arjun");
        Arjun4.setCostOfPurchase(60);
        Arjun4.setPurchaseDate(sdf.parse("04/26/2021"));

        list.add(Arjun1);
        list.add(Arjun2);
        list.add(Arjun3);
        list.add(Arjun4);

        calculateRewards(list);
    }
}
