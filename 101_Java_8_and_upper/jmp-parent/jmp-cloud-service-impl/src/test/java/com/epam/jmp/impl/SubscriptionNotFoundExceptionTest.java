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
        service = new ServiceImpl();
        bankCard = mock(BankCard.class);
    }

    @Test
    void testSubscriptionNotFoundException() {
        assertThrows(SubscriptionNotFoundException.class, () -> {
            service.getSubscriptionByBankCardNumber("NonExistentCard").orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));
        });
    }
}
