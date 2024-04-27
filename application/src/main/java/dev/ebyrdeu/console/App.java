package dev.ebyrdeu.console;

import dev.ebyrdeu.annotations.ProviderName;
import dev.ebyrdeu.currencies.Currency;
import dev.ebyrdeu.currencies.CurrencyConverter;

import java.util.List;
import java.util.Scanner;
import java.util.ServiceLoader;

public class App {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var converters = loadConverters();

        if (converters.isEmpty()) {
            throw new RuntimeException("No converter found");
        }


        var selectedConverter = selectConverter(converters);
        if (selectedConverter == null) {
            throw new RuntimeException("Invalid converter selected");
        }

        double amount = getAmountSek();
        Currency targetCurrency = getTargetCurrency();


        convertFromTo(selectedConverter, amount, targetCurrency);
    }

    private static void convertFromTo(CurrencyConverter selectedConverter, double amount, Currency targetCurrency) {
        var convertedAmount = selectedConverter.convert(amount, Currency.SEK, targetCurrency);
        // jest trying template feature
        var result = STR."Converterd result is \{convertedAmount} \{targetCurrency} from original \{amount} \{Currency.SEK}";
        System.out.println(result);
    }

    // you kinda need once more to choose currency
    // it can be done without enum for sure.
    // But it's works... so I leave as it is
    private static Currency getTargetCurrency() {
        System.out.println("""
                Select target currency
                1. EUR
                2. USD
                """);
        int target = scanner.nextInt();
        scanner.nextLine();

        if (target == 1) {
            return Currency.EUR;
        }

        return Currency.USD;
    }

    private static double getAmountSek() {
        System.out.println("Amount of SEK you want to convert");
        var amount = scanner.nextDouble();
        scanner.nextLine();
        return amount;
    }


    // Just because it easier for me to work with list than iterators
    private static List<CurrencyConverter> loadConverters() {
        return ServiceLoader.load(CurrencyConverter.class)
                .stream()
                .filter(converter -> converter.type().isAnnotationPresent(ProviderName.class)
                ).map(ServiceLoader.Provider::get).toList();
    }

    private static CurrencyConverter selectConverter(List<CurrencyConverter> converters) {
        System.out.println("Available converters:");

        for (int i = 0; i < converters.size(); i++) {
            var providerName = converters.get(i).getClass().getAnnotation(ProviderName.class);
            System.out.println(STR."\{i + 1}. \{providerName.name()}");
        }

        int input = scanner.nextInt();
        scanner.nextLine();

        if (input > 0 && input <= converters.size()) {
            return converters.get(input - 1);
        }

        return null;
    }

}