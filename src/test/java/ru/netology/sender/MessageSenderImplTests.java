package ru.netology.sender;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MessageSenderImplTests {

    @ParameterizedTest
    @MethodSource("senderParameters")
    public void testMessageSender(Country countrySetup, Location locationSetup, String expected) {
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Mockito.any()))
                .thenReturn(String.valueOf(countrySetup));
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.anyString()))
                .thenReturn(locationSetup);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        String result = messageSender.send(headers);
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> senderParameters() {
        return Stream.of(
                Arguments.of(Country.RUSSIA, new Location("Moscow", Country.RUSSIA, "Lenina", 15), "RUSSIA"),
                Arguments.of(Country.USA, new Location("New York", Country.USA, " 10th Avenue", 32), "USA")
        );
    }
}
