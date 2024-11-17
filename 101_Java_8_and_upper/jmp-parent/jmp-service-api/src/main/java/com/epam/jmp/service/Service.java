package com.epam.jmp.service;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {

    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();

    /**
     * Calculates the average age of all users based on their birthdates.
     * @return the average age of all users.
     */
    default double getAverageUsersAge() {
        return getAllUsers().stream()
            .mapToDouble(user -> ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()))
            .average()
            .orElse(0);
    }

    /**
     * Static method to check if a user is over 18 years old.
     * @param user the user to check.
     * @return true if the user is over 18 years old, false otherwise.
     */
    static boolean isPayableUser(User user) {
        long age = ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now());
        return age >= 18;
    }

    /**
     * Gets all subscriptions that match a given condition (Predicate).
     * @param condition the condition to filter subscriptions.
     * @return a list of subscriptions that match the condition.
     */
    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition);

}
