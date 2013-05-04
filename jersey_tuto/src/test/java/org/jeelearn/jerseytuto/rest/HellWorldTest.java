package org.jeelearn.jerseytuto.rest;

import static org.junit.Assert.assertEquals;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;
import org.junit.Test;


public class HellWorldTest extends JerseyTest {

    public HellWorldTest()throws Exception {
        super("org.jeelearn.jerseytuto.rest");
    }

    @Test
    public void testHelloWorld() {
        WebResource webResource = resource();
        String responseMsg = webResource.path("helloworld").get(String.class);
        assertEquals("Hello World", responseMsg);
    }
}
