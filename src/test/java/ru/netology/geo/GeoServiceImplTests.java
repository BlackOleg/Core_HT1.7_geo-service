package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import java.util.stream.Stream;

public class GeoServiceImplTests {

    @ParameterizedTest
    @MethodSource("ipParameters")
    public void testByIp(String ip, Location expected) {
        GeoService geoService = new GeoServiceImpl();
        Location result = geoService.byIp(ip);
//        String resultsb = new StringBuilder().append(result.getCountry())
//                .append(result.getCity())
//                .append(result.getStreet())
//                .append(result.getBuiling())
//                .toString();
//        String expectedsb = new StringBuilder().append(expected.getCountry())
//                .append(expected.getCity())
//                .append(expected.getStreet())
//                .append(expected.getBuiling())
//                .toString();
        //Assert.assertTrue(new ReflectionEquals(expected, excludeFields).matches(actual));
        Assertions.assertEquals(result, expected);

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
