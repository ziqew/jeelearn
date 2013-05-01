package org.jeelearn.jpa;


import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class UserRepositoryTest {
	
	  
	   @Deployment
	   public static JavaArchive createDeployment() {
	      return ShrinkWrap.create(JavaArchive.class, "test.jar")
	         .addClasses(User.class, UserRepository.class, UserRepositoryBean.class)
	         .addAsManifestResource("META-INF/test-persistence.xml");
	   }
	 
	   private static final String FIRST_NAME = "John";
	   private static final String LAST_NAME = "Smith";
	 
	   @EJB
	   private UserRepository userRepository;
	 
	   @Test
	   public void testCanPersistUserObject() {
	      User u = new User(FIRST_NAME, LAST_NAME);
	      userRepository.storeAndFlush(u);
	 
	      List<User> users = userRepository.getByFirstName(LAST_NAME);
	 
	      Assert.assertNotNull(users);
	      Assert.assertTrue(users.size() == 1);
	 
	      Assert.assertEquals(users.get(0).getLastName(), LAST_NAME);
	      Assert.assertEquals(users.get(0).getFirstName(), FIRST_NAME);
	   }
}
