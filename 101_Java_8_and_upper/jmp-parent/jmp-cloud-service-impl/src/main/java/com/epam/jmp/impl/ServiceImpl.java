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
        Subscription subscription = new Subscription(bankCard.getNumber(), LocalDate.now());
        subscriptions.add(subscription);
        System.out.println("Subscribed: " + subscription);
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
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

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition) {
        return subscriptions.stream()
            .filter(condition)
            .collect(Collectors.toUnmodifiableList());
    }
}
