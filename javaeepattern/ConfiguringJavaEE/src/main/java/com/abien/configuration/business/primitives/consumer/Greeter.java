package com.abien.configuration.business.primitives.consumer;

import com.abien.configuration.business.primitives.provider.Configurable;
import com.abien.configuration.business.staging.StageDependent;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author adam bien, blog.adam-bien.com
 */

@Stateless
public class Greeter {
    
    @Inject @Configurable("greetings")
    private String message;

    @Inject @StageDependent
    private String stagedMessage;

    @Inject
    private int repetition;
    
    public String hello(){
     StringBuilder builder = new StringBuilder();
        for (int i = 0; i < repetition; i++) {
            builder.append(message);
        }
        return builder.toString();
    }
    
    public String stagedMessage(){
        return stagedMessage;
    }
}
