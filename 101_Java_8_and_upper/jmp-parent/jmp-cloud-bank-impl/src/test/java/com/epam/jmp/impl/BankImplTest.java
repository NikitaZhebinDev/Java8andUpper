package com.epam.jmp.impl;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.CreditBankCard;
import com.epam.jmp.dto.DebitBankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.Bank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BankImplTest {

    private Bank bank;
    private User user;

    @BeforeEach
    void setUp() {
        bank = new BankImpl();
        user = mock(User.class);
    }

    @Test
    void testCreateCreditBankCard() {
        when(user.getName()).thenReturn("Alice");

        BankCard card = bank.createBankCard(user, BankCardType.CREDIT);

        assertNotNull(card, "The card should not be null.");
        assertTrue(card instanceof CreditBankCard, "The card should be of type CreditBankCard.");
        assertEquals("DefaultCreditNumber", card.getNumber(), "The card number should match the default value.");
        assertEquals(user, card.getUser(), "The card should be associated with the correct user.");
    }

    @Test
    void testCreateDebitBankCard() {
        when(user.getName()).thenReturn("Bob");

        BankCard card = bank.createBankCard(user, BankCardType.DEBIT);

        assertNotNull(card, "The card should not be null.");
        assertTrue(card instanceof DebitBankCard, "The card should be of type DebitBankCard.");
        assertEquals("DefaultDebitNumber", card.getNumber(), "The card number should match the default value.");
        assertEquals(user, card.getUser(), "The card should be associated with the correct user.");
    }

    @Test
    void testCreateUnknownBankCardType() {
        assertThrows(IllegalArgumentException.class, () -> {
            bank.createBankCard(user, BankCardType.valueOf("UNKNOWN"));
        }, "An exception should be thrown for an unknown card type.");
    }
}
