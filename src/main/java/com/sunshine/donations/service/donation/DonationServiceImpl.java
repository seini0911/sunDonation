package com.sunshine.donations.service.donation;

import com.sunshine.donations.dto.GiftDto;
import com.sunshine.donations.exceptions.ApiException;
import com.sunshine.donations.exceptions.ResourceNotFoundException;
import com.sunshine.donations.model.Donation;
import com.sunshine.donations.model.Gift;
import com.sunshine.donations.model.User;
import com.sunshine.donations.repository.donation.DonationRepository;
import com.sunshine.donations.requests.CreateDonationRequest;
import com.sunshine.donations.requests.CreateGiftRequest;
import com.sunshine.donations.service.gift.IGiftService;
import com.sunshine.donations.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DonationServiceImpl implements IDonationService{
    private static final Logger log = LoggerFactory.getLogger(DonationServiceImpl.class);
    private final DonationRepository donationRepository;
    private final IUserService userService;
    private final IGiftService giftService;
    @Override
    public Donation createDonation(CreateDonationRequest donationRequest) {
        log.info("**** Saving a new donation with target amount of :{}", donationRequest.getTargetAmount());
        User fundraiser = userService.findUserById(donationRequest.getFundraiserId());
        Donation newDonation = Donation.builder()
                .fundraiser(fundraiser)
                .targetAmount(BigDecimal.valueOf(donationRequest.getTargetAmount()))
                .currentAmountReceived(BigDecimal.valueOf(0))
                .gifts(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
        return donationRepository.save(newDonation);
    }

    @Override
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    @Override
    public Gift addGift(Long donationId, CreateGiftRequest createGiftRequest) {
        //get the donation to which the gift has to be added
        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(()-> new ResourceNotFoundException("This donation is not found or no longer exist"));

        BigDecimal currentAmount =  donation.getCurrentAmountReceived() != null ? donation.getCurrentAmountReceived() : BigDecimal.valueOf(0);
        BigDecimal targetAmount =  donation.getTargetAmount() != null ? donation.getTargetAmount() : BigDecimal.valueOf(0);


        //check if the donation currentAmount < targetAmount
        if(!donation.isCompleted() && currentAmount.compareTo(targetAmount) < 0){
            log.info("**** Donation has not yet reached the target amount");
            //we update the currentAmountReceived
           BigDecimal newAmountReceived = currentAmount.add(BigDecimal.valueOf(createGiftRequest.getAmount()));
           donation.setCurrentAmountReceived(newAmountReceived);
           //get the donor of the gift
           User giftDonor = userService.findUserById(createGiftRequest.getDonorId());
           //create a gift object to save
           Gift giftToSave = Gift.builder()
                   .donor(giftDonor)
                   .amount(BigDecimal.valueOf(createGiftRequest.getAmount()))
                   .donation(donation)
                   .date(LocalDateTime.now())
                   .build();
            Gift savedGift = giftService.createNewGift(giftToSave);
           //update the donation with the new gift added
           List<Gift> currentGifts =  donation.getGifts();
            currentGifts.add(savedGift);
            donation.setGifts(currentGifts);

            //update the donation status (to completed if the target amount has been reached
            donation.setIsCompleted();
            //save the donation
            donationRepository.save(donation);
            return savedGift;
        }else{
            log.info("**** Donation has reached the target amount");
           throw new ApiException("The donation objective has been reached, thanks for your kindness");
        }
    }

    @Override
    public List<GiftDto> getDonationGifts(Long donationId) {
        Donation donation = donationRepository.findById(donationId).orElseThrow(()-> new ResourceNotFoundException("No donation found with such identifier"));
        log.info("**** Donation Gifts : {}",donation.getGifts());
        List<Gift> gifts = donation.getGifts();
        return gifts
                .stream()
                .map(gift -> GiftDto.builder()
                        .id(gift.getId())
                        .amount(gift.getAmount())
                        .date(gift.getDate())
                        .build())
                .toList();
    }

    @Override
    public Donation getDonationById(Long donationId) {
        return donationRepository.findById(donationId)
                .orElseThrow(()-> new ResourceNotFoundException("No donation found with id :"+ donationId));
    }

    @Override
    public Donation updateDonation(Long donationId, CreateDonationRequest donationRequest) {
        Donation donation = this.getDonationById(donationId);
        if(donationRequest.getFundraiserId() != null) donation.setFundraiser(userService.findUserById(donationRequest.getFundraiserId()));
        donation.setTargetAmount(BigDecimal.valueOf(donationRequest.getTargetAmount()));
        //update the donation status
        donation.setIsCompleted();
        return donationRepository.save(donation);
    }

    @Override
    public List<Donation> getFundraiserDonations(Long userId) {
        User fundraiser = userService.findUserById(userId);
       return donationRepository.findAllByFundraiser(fundraiser)
               .orElseThrow(()-> new ResourceNotFoundException("No donation found for this fundraiser"));
    }

}
