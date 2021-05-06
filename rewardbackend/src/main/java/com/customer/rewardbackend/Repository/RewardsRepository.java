package com.customer.rewardbackend.Repository;

import com.customer.rewardbackend.model.Rewards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardsRepository extends CrudRepository<Rewards, Integer> {

    List<Rewards> findByCustomerName(String customerName);
}
