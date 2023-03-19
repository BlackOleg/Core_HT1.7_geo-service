package ru.netology.geo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class GeoServiceImplTests {
    static GeoService geoService;
    static Location result;
    @BeforeAll
    public static void before() {
        System.out.println("GeoService tests started..");
    }
    @AfterAll
    public static void after() {
        geoService = null;
        result = null;
        System.out.println("GeoService tests completed");
    }
    @ParameterizedTest
    @MethodSource("ipParameters")
    public void testByIp(String ip, Location expected) {
        geoService = new GeoServiceImpl();
        Location result = geoService.byIp(ip);
        Assertions.assertAll(() -> {
                    assertEquals(expected.getCountry(), result.getCountry());
                    assertEquals(expected.getCity(), result.getCity());
                    assertEquals(expected.getBuiling(), result.getBuiling());
                    assertEquals(expected.getStreet(), result.getStreet());
                }
        );
    }

    private static Stream<Arguments> ipParameters() {
        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.", new Location("New York", Country.USA, null, 0))
        );
    }
}
