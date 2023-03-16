package ru.netology.geo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.Set;
import java.util.stream.Stream;

public class GeoServiceImplTests {

    @ParameterizedTest
    @MethodSource("ipParameters")
    public void testByIp(String ip, Location expected){
        GeoService geoService = new GeoServiceImpl();
        //Location result = geoService.byIp(ip);
//        System.out.println(result.getCountry());

        Set<Location> result= Set.of(geoService.byIp(ip));
        Set<Location> ex= Set.of(expected);
       // System.out.println(result.toString()+" just in comparison with "+ex.toString());
        //Assert.assertTrue(new ReflectionEquals(expected, excludeFields).matches(actual));
        //Assertions.assertEquals(result, ex);
        geoService= null;
     }

    private static Stream<Arguments> ipParameters() {
        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.", new Location("New York", Country.USA, " 10th Avenue", 32))
        );
    }
}
