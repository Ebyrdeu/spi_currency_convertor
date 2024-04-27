package dev.ebyrdeu.converters;

import dev.ebyrdeu.currencies.Currency;
import dev.ebyrdeu.currencies.CurrencyConverter;
import dev.ebyrdeu.annotations.ProviderName;

import static dev.ebyrdeu.currencies.Currency.SEK;
import static dev.ebyrdeu.currencies.Currency.USD;


@ProviderName(name = "Converting from SEK to USD")
public class SekToUsd implements CurrencyConverter {

    public SekToUsd() {
    }

    @Override
    public double convert(double amount, Currency from, Currency to) {
        if (from == SEK && to == USD) return amount * 0.091;
        return amount;
    }
}