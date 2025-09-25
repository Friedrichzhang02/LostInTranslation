package translation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryCodeConverterTest {

    @Test
    public void fromCountryCodeUSA() {
        CountryCodeConverter converter = new CountryCodeConverter();
        assertEquals("United States of America (the)", converter.fromCountryCode("usa"));
    }

    @Test
    public void fromCountryCodeCaseSensitiveChina() {
        CountryCodeConverter converter = new CountryCodeConverter();
        assertEquals("China", converter.fromCountryCode("cHn"));
    }

    @Test public void fromCountryToCountryCodeUSA() {
        CountryCodeConverter converter = new CountryCodeConverter();
        assertEquals("USA", converter.fromCountry("United States of America (the)"));
    }

    @Test public void fromCountryToCountryCodeCaseSensitiveKorea() {
        CountryCodeConverter converter = new CountryCodeConverter();
        assertEquals("KOR", converter.fromCountry("kOreA (thE republic OF)"));
    }

    @Test
    public void fromCountryCodeAllLoaded() {
        CountryCodeConverter converter = new CountryCodeConverter();
        assertEquals(249, converter.getNumCountries());
    }
}