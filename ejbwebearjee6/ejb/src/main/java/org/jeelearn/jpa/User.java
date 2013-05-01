package org.jeelearn.jpa;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User
 *
 * @author <a href="mailto:aslak@conduct.no">Aslak Knutsen</a>
 * @version $Revision: $
 */
@Entity
@Table
public class User implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Id
   private String id;
   
   private String firstName; 
   
   private String lastName;
   
   protected User()
   {
   }
   
   public User(String firstName, String lastName) 
   {
      this.id = UUID.randomUUID().toString();
      this.firstName = firstName;
      this.lastName = lastName;
   }
   
   public String getId()
   {
      return id;
   }
   
   public String getFirstName()
   {
      return firstName;
   }
   
   public String getLastName()
   {
      return lastName;
   }
}