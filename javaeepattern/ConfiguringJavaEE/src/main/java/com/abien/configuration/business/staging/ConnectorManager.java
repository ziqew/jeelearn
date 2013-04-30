package com.abien.configuration.business.staging;

import javax.enterprise.inject.Produces;

/**
 *
 * @author adam bien, blog.adam-bien.com
 */
public class ConnectorManager {
    @Produces @StageDependent
    public EISConnector connect(Stage projectStage){
       switch(projectStage){
           case Production: 
               return new LegacyConnector();
           default:
               return new EISMock();
       }
    }
}
