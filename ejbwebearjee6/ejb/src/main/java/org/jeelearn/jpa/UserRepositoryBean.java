package org.jeelearn.jpa;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * UserRepositoryBean
 *
 * @author <a href="mailto:aslak@conduct.no">Aslak Knutsen</a>
 * @version $Revision: $
 */
@Local(UserRepository.class)
@Stateless
public class UserRepositoryBean implements UserRepository
{

   @PersistenceContext(unitName = "greeterDatabase")
   private EntityManager entityManager;
   
   @SuppressWarnings("unchecked")
   public List<User> getByFirstName(String firstName)
   {
      return entityManager.createQuery("from User user where user.firstName = :firstName")
                           .setParameter("firstName", firstName)
                           .getResultList();
   }

   public void storeAndFlush(User user)
   {
      entityManager.persist(user);
      entityManager.flush();
   }

}