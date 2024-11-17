package com.epam.jmp.impl;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.exception.SubscriptionNotFoundException;
import com.epam.jmp.service.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class ServiceImpl implements Service {

    private final List<Subscription> subscriptions = new ArrayList<>();

    @Override
    public void subscribe(BankCard bankCard) {
        // Create a new subscription with the current date
        Subscription subscription = new Subscription(bankCard.getNumber(), LocalDate.now());
        subscriptions.add(subscription);
        System.out.println("Subscribed: " + subscription);
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        // Using orElseThrow to throw SubscriptionNotFoundException if not found
        return Optional.ofNullable(subscriptions.stream()
            .filter(subscription -> subscription.getBankcard().equals(cardNumber))
            .findFirst()
            .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found for card number: " + cardNumber)));
    }

    @Override
    public List<User> getAllUsers() {
        return List.of(
            new User("Alice", "Smith", LocalDate.of(1990, 5, 15)),
            new User("Bob", "Johnson", LocalDate.of(1985, 3, 22)),
            new User("Charlie", "Brown", LocalDate.of(2000, 12, 10))
        );
    }

    /**
     * Method to get all users who are over 18 years old
     * */
    public List<User> getUsersOver18() {
        return getAllUsers().stream()
            .filter(Service::isPayableUser)
            .collect(Collectors.toUnmodifiableList()); // Collect into an unmodifiable list
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition) {
        return subscriptions.stream()
            .filter(condition)  // Apply the predicate filter
            .collect(Collectors.toList()); // Collect the filtered results into a list
    }
}
