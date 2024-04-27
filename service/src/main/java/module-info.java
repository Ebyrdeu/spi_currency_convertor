import dev.ebyrdeu.converters.SekToEur;
import dev.ebyrdeu.converters.SekToUsd;
import dev.ebyrdeu.currencies.CurrencyConverter;

module dev.ebyrdeu.serivce {
    requires dev.ebyrdeu.api;
    provides CurrencyConverter with SekToEur, SekToUsd;
}
