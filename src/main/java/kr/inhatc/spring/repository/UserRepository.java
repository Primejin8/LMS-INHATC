package kr.inhatc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.model.User;

//CRUD 함수를 JpaRepository가 들고 있음
//@Repository라는 어노테이션이 없어도 IoC가 된다. JpaRepository를 상속했기 때문에
public interface UserRepository extends JpaRepository<User, Integer>{
   //findBy = 규칙 -> Username = 문법
   //select * from user where username = ?
   public User findByUsername (String username); //JPA Query method 유저네임으로 유저찾기
}