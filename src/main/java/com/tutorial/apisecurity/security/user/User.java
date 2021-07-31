package com.tutorial.apisecurity.security.user;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.tutorial.apisecurity.security.authority.Authority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @EqualsAndHashCode.Include
   private long id;

   @Column(name = "username")
   private String username;

   @Column(name = "password")
   private String password;

   @Column(name = "enabled")
   private boolean enabled;

   /*
    * For simplicity of this application, I use the Eager fetch type to load the authorities immediately upon retrieving the user object. TODO: Change
    * the FetchType from EAGER to LAZY or just remove the authorities field.
    */
   @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
   @ToString.Exclude
   private Set<Authority> authorities;
}
