package dev.ebyrdeu.currencies;

public interface CurrencyConverter {
    double convert(double amount, Currency from, Currency to);
}
