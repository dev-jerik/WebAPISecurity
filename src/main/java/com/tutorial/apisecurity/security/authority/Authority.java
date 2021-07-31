package com.tutorial.apisecurity.security.authority;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.tutorial.apisecurity.security.user.User;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "authority")
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Authority implements GrantedAuthority {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @EqualsAndHashCode.Include
   private long id;

   @Column(name = "code")
   private String code;

   @Column(name = "description")
   private String description;

   /*
    * Without adding the joinColumns inserting of data from data.sql to the database will not work.
    */
   @ManyToMany
   @JoinTable(name = "user_authority_link", joinColumns = @JoinColumn(name = "authority_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
   @ToString.Exclude
   private Set<User> users;

   @Override
   public String getAuthority() {
      return code;
   }

}
