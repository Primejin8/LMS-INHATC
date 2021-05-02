package kr.inhatc.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;

@Entity
@Data
public class User {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private int id;
   
   @Column(nullable = false)
   private String username;
   
   @Column(nullable = false)
   private String password;

   @Column(nullable = false)
   private String name;
   
   @Column(nullable = false)
   private String department;
   
   private String email;
   
   @Column(nullable = false)
   private String role;   //ROLE_STUDENT, ROLE_PROF
}