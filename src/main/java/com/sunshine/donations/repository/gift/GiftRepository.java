package com.sunshine.donations.repository.gift;

import com.sunshine.donations.model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {
//    Optional<Gift> findByDonor(Long donorId);
}
