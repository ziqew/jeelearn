package org.jeelearn.jpa;

import java.util.List;

/**
 * UserRepository
 *
 * @author <a href="mailto:aslak@conduct.no">Aslak Knutsen</a>
 * @version $Revision: $
 */
public interface UserRepository
{
   List<User> getByFirstName(String firstName);
   
   void storeAndFlush(User user);
   
}
