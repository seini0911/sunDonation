package com.sunshine.donations.service.gift;

import com.sunshine.donations.repository.gift.GiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GiftService implements IGiftService{
    private final GiftRepository giftRepository;
}
