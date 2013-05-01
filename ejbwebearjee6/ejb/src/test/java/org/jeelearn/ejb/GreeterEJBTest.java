package org.jeelearn.ejb;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class GreeterEJBTest {
		   @Deployment
		   public static JavaArchive createDeployment() {
		      return ShrinkWrap.create(JavaArchive.class, "test.jar")
		               .addClasses(
		            		   GreeterEJB.class
		                     );
		   }
		   
		   @EJB
		   private GreeterEJB greeter;
		   
		   @Test
		   public void shouldBeAbleToInjectEJB() throws Exception {
		      
		      String userName = "Devoxx";
		      
		      Assert.assertEquals(
		            "Hello " + userName,
		            greeter.sayHello(userName));
		   }
}
