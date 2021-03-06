package com.abien.cdi.performance;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author blog.adam-bien.com
 */
@Path("dependent")
@Stateless
public class DependentScopeTester {

    @Inject
    DependentScopedBean bean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get(){
        return String.valueOf(bean.get());
    }
}
