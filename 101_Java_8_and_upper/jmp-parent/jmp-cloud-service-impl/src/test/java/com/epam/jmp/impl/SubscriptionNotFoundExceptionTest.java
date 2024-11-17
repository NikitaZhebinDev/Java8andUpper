package com.epam.jmp.exception;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.impl.ServiceImpl;
import com.epam.jmp.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class SubscriptionNotFoundExceptionTest {

    private Service service;
    private BankCard bankCard;

    @BeforeEach
    void setUp() {
        service = new ServiceImpl(); // ServiceImpl instance
        bankCard = mock(BankCard.class);  // Mock a bank card
    }

    @Test
    void testSubscriptionNotFoundException() {
        // Simulate that the card is not found
        assertThrows(SubscriptionNotFoundException.class, () -> {
            service.getSubscriptionByBankCardNumber("NonExistentCard").orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));
        });
    }
}
