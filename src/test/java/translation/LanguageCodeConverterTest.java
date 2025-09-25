package translation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanguageCodeConverterTest {

    @Test
    public void fromLanguageCodeEN() {
        LanguageCodeConverter converter = new LanguageCodeConverter();
        assertEquals("English", converter.fromLanguageCode("en"));
    }

    @Test
    public void fromLanguageCodeCaseSensitiveJA() {
        LanguageCodeConverter converter = new LanguageCodeConverter();
        assertEquals("Japanese", converter.fromLanguageCode("JA"));
    }

    @Test
    public void fromLanguageEN() {
        LanguageCodeConverter converter = new LanguageCodeConverter();
        assertEquals("en", converter.fromLanguage("English"));
    }

    @Test
    public void fromLanguageCaseSensitiveTH() {
        LanguageCodeConverter converter = new LanguageCodeConverter();
        assertEquals("th", converter.fromLanguage("tHaI"));
    }

    @Test
    public void fromLanguageCodeAllLoaded() {
        LanguageCodeConverter converter = new LanguageCodeConverter();
        assertEquals(184, converter.getNumLanguages());
    }
}