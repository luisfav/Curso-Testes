/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.exercicios;

import com.jcabi.matchers.XhtmlMatchers;
import java.io.StringReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.hamcrest.MatcherAssert;
import org.junit.Test;


/**
 *
 * @author jean
 */
public class ExemploXhtmlMatcherTest {

    @Test
    public void buildsValidXml() {
        MatcherAssert.assertThat(
            "<data><employee id='33'><name>Jeff</name></employee></data>",
            XhtmlMatchers.hasXPath("/data/employee[@id=33]/name")
        );
    }
    
     @Test
  public void buildsOtherValidXml() {
    Source source = new StreamSource(
        new StringReader(
            "<data><employee id='33'><name>Jeff</name></employee></data>"
        )
    );
    MatcherAssert.assertThat(
      XhtmlMatchers.xhtml(source),
      XhtmlMatchers.hasXPath("/data/employee[@id=33]/name")
    );
  }
}
