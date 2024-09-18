package com.sunshine.donations.service.donation;

import com.sunshine.donations.dto.GiftDto;
import com.sunshine.donations.model.Donation;
import com.sunshine.donations.model.Gift;
import com.sunshine.donations.model.User;
import com.sunshine.donations.requests.CreateDonationRequest;
import com.sunshine.donations.requests.CreateGiftRequest;

import java.util.List;

public interface IDonationService {

    Donation createDonation(CreateDonationRequest donationRequest);
    List<Donation> getAllDonations();

    Gift addGift(Long donationId, CreateGiftRequest createGiftRequest);

    List<GiftDto> getDonationGifts(Long donationId);

    Donation getDonationById(Long donationId);

    Donation updateDonation(Long donationId, CreateDonationRequest donationRequest);

    List<Donation> getFundraiserDonations(Long userId);
}
