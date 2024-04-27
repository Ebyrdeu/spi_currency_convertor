package dev.ebyrdeu.converters;

import dev.ebyrdeu.annotations.ProviderName;
import dev.ebyrdeu.currencies.Currency;
import dev.ebyrdeu.currencies.CurrencyConverter;

import static dev.ebyrdeu.currencies.Currency.EUR;
import static dev.ebyrdeu.currencies.Currency.SEK;

@ProviderName(name = "Converting from SEK to EUR")
public class SekToEur implements CurrencyConverter {

    public SekToEur() {
    }

    @Override
    public double convert(double amount, Currency from, Currency to) {
        if (from == SEK && to == EUR) return amount * 0.086;
        return amount;
    }
}
