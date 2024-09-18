package com.sunshine.donations.controller;

import com.sunshine.donations.requests.CreateDonationRequest;
import com.sunshine.donations.requests.CreateGiftRequest;
import com.sunshine.donations.response.ApiResponseHandler;
import com.sunshine.donations.service.donation.IDonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/donations")
public class DonationController {
    /* Donation service injection */
    private final IDonationService donationService;

    /* Endpoint to get all donations requests */
    @GetMapping
    public ResponseEntity<Object> getAllDonations(){
        try{
            return ApiResponseHandler.buildResponse(
                    "Donations list",
                    HttpStatus.OK,
                    donationService.getAllDonations()
            );
        }catch (Exception e){
            return ApiResponseHandler.buildResponse(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                null
            );
        }
    }

    /* Endpoint to create a donation request */
    @PostMapping
    public ResponseEntity<Object> createDonation(@RequestBody CreateDonationRequest donationRequest){
        try {
            return ApiResponseHandler.buildResponse(
                    "Donation created successfully",
                    HttpStatus.CREATED,
                    donationService.createDonation(donationRequest)
            );
        }catch (Exception e){
            return ApiResponseHandler.buildResponse(
              e.getMessage(),
              HttpStatus.INTERNAL_SERVER_ERROR,
              null
            );
        }
    }

    /* Endpoint to get all gifts made for a given donation request */
    @GetMapping("/{donationId}/gifts")
    public ResponseEntity<Object> getDonationGifts(@PathVariable Long donationId){
       try{
           return ApiResponseHandler.buildResponse(
             "Donation id: "+donationId+" gifts",
             HttpStatus.OK,
             donationService.getDonationGifts(donationId)
           );
       }catch (Exception e){
           return ApiResponseHandler.buildResponse(
             e.getMessage(),
             HttpStatus.NOT_FOUND,
             null
           );
       }
    }

    /* Endpoint to add a gift for a given donation request */
    @PostMapping("/{donationId}/gift")
    public ResponseEntity<Object> addDonationGift(
            @PathVariable Long donationId,
            @RequestBody CreateGiftRequest createGiftRequest
    ){
        try{
            return ApiResponseHandler.buildResponse(
              "Gift saved successfully for the donation",
              HttpStatus.OK,
              donationService.addGift(donationId, createGiftRequest)
            );
        }catch (Exception e){
            return ApiResponseHandler.buildResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }

    /* Endpoint to get a given donation request by its id */
    @GetMapping("/{donationId}")
    public ResponseEntity<Object> getDonationById(
            @PathVariable Long donationId
    ){
        try{
            return ApiResponseHandler.buildResponse(
                    "Donation retrieved",
                    HttpStatus.OK,
                    donationService.getDonationById(donationId)
            );
        }catch (Exception e){
            return ApiResponseHandler.buildResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                null
            );
        }
    }

    /* Endpoint to update a donation request */
    @PutMapping("/{donationId}")
    public ResponseEntity<Object> updateDonation(
            @PathVariable Long donationId,
            @RequestBody CreateDonationRequest donationRequest
            ){
        try{
            return ApiResponseHandler.buildResponse(
                    "Donation updated successfully",
                    HttpStatus.OK,
                    donationService.updateDonation(donationId, donationRequest)
            );
        }catch (Exception e){
            return ApiResponseHandler.buildResponse(
              e.getMessage(),
              HttpStatus.INTERNAL_SERVER_ERROR,
              null
            );
        }
    }

    /* Endpoint to get donations of a fundraiser  */
    @GetMapping("/fundraiser/{userId}")
    public ResponseEntity<Object> getUserDonationsRequests(
            @PathVariable Long userId
    ){
        try{
            return ApiResponseHandler.buildResponse(
              "Fundraiser :${userId} donations",
              HttpStatus.OK,
              donationService.getFundraiserDonations(userId)
            );
        }catch (Exception e){
            return ApiResponseHandler.buildResponse(
              e.getMessage(),
              HttpStatus.NOT_FOUND,
              null
            );
        }
    }
}
