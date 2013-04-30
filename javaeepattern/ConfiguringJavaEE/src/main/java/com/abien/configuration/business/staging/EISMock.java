package com.abien.configuration.business.staging;

/**
 *
 * @author adam bien, blog.adam-bien.com
 */
public class EISMock implements EISConnector{

    @Override
    public String fetchInfo() {
        return "From Mock";
    }
    
}
