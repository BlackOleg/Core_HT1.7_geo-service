package ru.netology.i18n;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class LocalizationServiceImplTests {
    static LocalizationService localizationService;
    @BeforeAll
    public static void before() {
        System.out.println("LocalizationService tests started..");
    }
    @AfterAll
    public static void after() {
        localizationService = null;
        System.out.println("LocalizationService tests completed");
    }
    @ParameterizedTest
    @MethodSource("localeParameters")
    public void testLocale(Country country, String expected) {
        localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(country);
        Assertions.assertEquals(result, expected);
    }

    private static Stream<Arguments> localeParameters() {
        return Stream.of(
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome"),
                Arguments.of(Country.BRAZIL, "Welcome")
        );
    }
}
