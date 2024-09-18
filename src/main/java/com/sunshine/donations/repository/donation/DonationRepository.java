package com.sunshine.donations.repository.donation;

import com.sunshine.donations.model.Donation;
import com.sunshine.donations.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    Optional<List<Donation>> findAllByFundraiser(User fundraiser);
}
