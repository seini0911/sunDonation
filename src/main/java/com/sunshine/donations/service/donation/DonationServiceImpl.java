package com.sunshine.donations.service.donation;

import com.sunshine.donations.repository.donation.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DonationServiceImpl implements IDonationService{
    private final DonationRepository donationRepository;
}
