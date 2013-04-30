package com.abien.configuration.business.primitives.provider;

import java.util.Map;

/**
 *
 * @author blog.adam-bien.com
 */
public interface ConfigurationProvider {
    
    public Map<String,String> getConfiguration();

}
