package com.epam.jmp.factory;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.CreditBankCard;
import com.epam.jmp.dto.DebitBankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.User;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BankCardFactory {

    private static final Map<BankCardType, Function<User, BankCard>> cardCreators = new HashMap<>();

    static {
        registerCardType(BankCardType.CREDIT, CreditBankCard::new);
        registerCardType(BankCardType.DEBIT, DebitBankCard::new);
    }

    public static BankCard createBankCard(User user, BankCardType cardType) {
        Function<User, BankCard> cardConstructor = cardCreators.get(cardType);
        if (cardConstructor == null) {
            throw new IllegalArgumentException("Unknown card type: " + cardType);
        }
        return cardConstructor.apply(user);
    }

    public static void registerCardType(BankCardType cardType, Function<User, BankCard> constructor) {
        cardCreators.put(cardType, constructor);
    }
}
