package kr.inhatc.spring.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.inhatc.spring.model.User;

//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다
//로그인을 진행 완료 되면 시큐리티 session을 만들어줌 (Security ContextHolder)
//오브젝트 => Authentication 타입의 객체여야됨
//Authentication 안에 User 정보가 있어야 됨.
//User 오브젝트 타입 => UserDetails 타입 객체여야 됨
//Security Session => AUtentication => UserDetails (PrincipalDetails)

public class PrincipalDetails implements UserDetails {
   
   private User user; //콤포지션
   
   public PrincipalDetails(User user) {
      this.user=user;
   }
   
   //해당 User의 권한을 리턴하는 곳!
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      Collection<GrantedAuthority> collect = new ArrayList<>();
      collect.add(new GrantedAuthority() {
         
         @Override
         public String getAuthority() {
            return user.getRole();
         }
      });
      return collect;
   }

   @Override
   public String getPassword() {
      return user.getPassword();
   }

   @Override
   public String getUsername() {
      return user.getUsername();
   }
  
   @Override
   public boolean isAccountNonExpired() { //계정 만료
      return true;
   }
   @Override
   public boolean isAccountNonLocked() { //계정 잠금
      return true;
   }
   @Override
   public boolean isCredentialsNonExpired() { //비번 오래사용
      return true;
   }
   @Override
   public boolean isEnabled() { //사이트에서 1년동안 로그인안하면 휴면계정 
      return true;
   }
}