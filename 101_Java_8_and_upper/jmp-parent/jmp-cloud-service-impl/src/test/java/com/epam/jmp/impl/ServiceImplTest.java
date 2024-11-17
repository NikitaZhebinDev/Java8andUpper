package com.epam.jmp.impl;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.exception.SubscriptionNotFoundException;
import com.epam.jmp.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServiceImplTest {

    private Service service;
    private User user;
    private BankCard bankCard;

    @BeforeEach
    void setUp() {
        service = new ServiceImpl();
        user = new User("Alice", "Smith", LocalDate.of(1990, 5, 15));
        bankCard = mock(BankCard.class);
    }

    @Test
    void testSubscribe() {
        service.subscribe(bankCard);

        Optional<Subscription> subscription = service.getSubscriptionByBankCardNumber("DefaultDebitNumber");

        assertTrue(subscription.isPresent(), "Subscription should be present.");
        assertEquals("DefaultDebitNumber", subscription.get().getBankcard(), "The bank card number should match.");
    }

    @Test
    void testGetSubscriptionByBankCardNumberNotFound() {
        assertThrows(SubscriptionNotFoundException.class, () -> {
            service.getSubscriptionByBankCardNumber("NonExistingCard").orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));
        });
    }

    @Test
    void testGetAllUsers() {
        List<User> users = service.getAllUsers();

        assertNotNull(users, "User list should not be null.");
        assertEquals(3, users.size(), "There should be exactly 3 users.");
    }

    @Test
    void testGetAverageUsersAge() {
        double averageAge = service.getAverageUsersAge();

        assertTrue(averageAge > 0, "The average age should be a positive number.");
    }

    @Test
    void testGetAllSubscriptionsByCondition() {
        service.subscribe(bankCard);

        Predicate<Subscription> subscriptionPredicate = subscription -> subscription.getBankcard().equals("DefaultDebitNumber");

        List<Subscription> filteredSubscriptions = service.getAllSubscriptionsByCondition(subscriptionPredicate);

        assertFalse(filteredSubscriptions.isEmpty(), "The filtered subscriptions should not be empty.");
        assertEquals("DefaultDebitNumber", filteredSubscriptions.get(0).getBankcard(), "The filtered subscription should match the card number.");
    }
}
