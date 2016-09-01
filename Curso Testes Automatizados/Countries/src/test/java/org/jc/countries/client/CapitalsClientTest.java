/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.countries.client;

import java.util.Arrays;
import java.util.Optional;
import org.jc.countries.domain.Coordinates;
import org.jc.countries.domain.Country;
import org.jc.countries.domain.Currency;
import org.jc.countries.domain.Geography;
import org.jc.countries.domain.Location;
import org.jc.countries.domain.Name;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;

/**
 *
 * @author jean
 */
public class CapitalsClientTest {

    private static final String JSON = ""
        + "[\n"
        + "    {\n"
        + "        \"name\": \"United Kingdom\",\n"
        + "        \"capital\": \"London\",\n"
        + "        \"altSpellings\": [\n"
        + "            \"GB\",\n"
        + "            \"UK\",\n"
        + "            \"Great Britain\"\n"
        + "        ],\n"
        + "        \"relevance\": \"2.5\",\n"
        + "        \"region\": \"Europe\",\n"
        + "        \"subregion\": \"Northern Europe\",\n"
        + "        \"translations\": {\n"
        + "            \"de\": \"Vereinigtes Königreich\",\n"
        + "            \"es\": \"Reino Unido\",\n"
        + "            \"fr\": \"Royaume-Uni\",\n"
        + "            \"ja\": \"イギリス\",\n"
        + "            \"it\": \"Regno Unito\"\n"
        + "        },\n"
        + "        \"population\": 64105654,\n"
        + "        \"latlng\": [\n"
        + "            54,\n"
        + "            -2\n"
        + "        ],\n"
        + "        \"demonym\": \"British\",\n"
        + "        \"area\": 242900,\n"
        + "        \"gini\": 34,\n"
        + "        \"timezones\": [\n"
        + "            \"UTC−08:00\",\n"
        + "            \"UTC−05:00\",\n"
        + "            \"UTC−04:00\",\n"
        + "            \"UTC−03:00\",\n"
        + "            \"UTC−02:00\",\n"
        + "            \"UTC\",\n"
        + "            \"UTC+01:00\",\n"
        + "            \"UTC+02:00\",\n"
        + "            \"UTC+06:00\"\n"
        + "        ],\n"
        + "        \"borders\": [\n"
        + "            \"IRL\"\n"
        + "        ],\n"
        + "        \"nativeName\": \"United Kingdom\",\n"
        + "        \"callingCodes\": [\n"
        + "            \"44\"\n"
        + "        ],\n"
        + "        \"topLevelDomain\": [\n"
        + "            \".uk\"\n"
        + "        ],\n"
        + "        \"alpha2Code\": \"GB\",\n"
        + "        \"alpha3Code\": \"GBR\",\n"
        + "        \"currencies\": [\n"
        + "            \"GBP\"\n"
        + "        ],\n"
        + "        \"languages\": [\n"
        + "            \"en\"\n"
        + "        ]\n"
        + "    }\n"
        + "]";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    public CapitalsClientTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of find method, of class CapitalsClient.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        String capital = "london";
        CapitalsClient instance = new CapitalsClient(
            "http://localhost:" + wireMockRule.port()
        );
        Country expResult
            = new Country(
                new Name("United Kingdom", "United Kingdom"),
                new Geography(
                    "London",
                    64105654L,
                    new Location("Europe", new Coordinates(54.0, -2.0))
                ),
                Arrays.asList(new Currency("GBP"))
            );
         stubFor(get(urlEqualTo('/' + capital))
            .withHeader("Accept", equalTo("application/json"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(JSON)));
        Optional<Country> result = instance.find(capital);
        assertEquals(expResult, result.get());
    }

}
