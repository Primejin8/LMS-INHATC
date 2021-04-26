package kr.inhatc.spring.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class User {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private int id;
   private String username;
   private String password;
   private String name;
   private String department;
   private String email;
   private String role;   //ROLE_STUDENT, ROLE_PROF
   @CreationTimestamp
   private Timestamp createDate;
}