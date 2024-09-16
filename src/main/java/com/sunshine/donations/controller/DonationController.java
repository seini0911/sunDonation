package com.sunshine.donations.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/donations")
public class DonationController {

    @GetMapping
    public String getAllDonations(){
        return "All donations list";
    }
}
