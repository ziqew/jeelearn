package com.abien.nointerfaces.control;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Stereotype;

/**
 *
 * @author Adam Bien, blog.adam-bien.com
 */
@Alternative
@Stereotype
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface NonConfidential {
    
}
