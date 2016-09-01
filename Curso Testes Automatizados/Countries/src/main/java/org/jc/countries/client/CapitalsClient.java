/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.countries.client;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jcabi.http.Request;
import com.jcabi.http.request.ApacheRequest;
import com.jcabi.http.response.RestResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.jc.countries.domain.Coordinates;
import org.jc.countries.domain.Country;
import org.jc.countries.domain.Currency;
import org.jc.countries.domain.Geography;
import org.jc.countries.domain.Location;
import org.jc.countries.domain.Name;

/**
 *
 * @author jean
 */
public class CapitalsClient {
    private static final String DEFAULT_API_ENDPOINT =
        "https://restcountries.eu/rest/v1/capital";

    private final String api;

    public CapitalsClient(String api) {
        this.api = api;
    }

    public CapitalsClient() {
        this(DEFAULT_API_ENDPOINT);
    }
    
    public Optional<Country> find(String capital) {
        Optional<Country> country;
        try {
            String body = new ApacheRequest(api)
                .uri().path('/' + capital).back()
                .method(Request.GET)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .fetch()
                .as(RestResponse.class)
                .body();
            country = parse(body);
        } catch (IOException ex) {
            country = Optional.empty();
            Logger.getLogger(CapitalsClient.class.getName()).log(
                Level.SEVERE,
                "Faild to find country for " + capital,
                ex
            );
        }
        return country;
    }

    private Optional<Country> parse(String body) {
        Object document = Configuration
            .defaultConfiguration()
            .jsonProvider()
            .parse(body);
        Geography geography = geography(document);
        Name name = name(document);
        List<Currency> crs = currencies(document);
        Country country = new Country(name, geography, crs);
        return Optional.of(country);
    }

    private List<Currency> currencies(Object document) {
        List<String> currencies = JsonPath.read(document, "$[0]['currencies']");
        List<Currency> crs = currencies.stream()
            .map(curr -> new Currency(curr))
            .collect(Collectors.toList());
        return crs;
    }

    private Geography geography(Object document) {
        Location location = location(document);
        String capital = JsonPath.read(document, "$[0]['capital']");
        Number number = JsonPath.read(document, "$[0]['population']");
        Long population = number.longValue();
        Geography geography = new Geography(capital, population, location);
        return geography;
    }

    private Name name(Object document) {
        String local = JsonPath.read(document, "$[0]['name']");
        String international = JsonPath.read(document, "$[0]['nativeName']");
        Name name = new Name(international, local);
        return name;
    }

    private Location location(Object document) {
        
        System.out.println(JsonPath.read(document, "$[0]['latlng']").getClass().getCanonicalName());
        Number lat = JsonPath.read(document, "$[0]['latlng'][0]");
        Number lng = JsonPath.read(document, "$[0]['latlng'][1]");
        Coordinates coordinates = new Coordinates(lat.doubleValue(), lng.doubleValue());
        String region = JsonPath.read(document, "$[0]['region']");
        Location location = new Location(region, coordinates);
        return location;
    }
    
    
}
