package kr.inhatc.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.inhatc.spring.model.User;
import kr.inhatc.spring.repository.UserRepository;

@Controller	//view를 리턴
public class IndexController {
	
	private Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired //필요한곳에서 Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public IndexController() {
		logger.info("############### Create IndexController ###############");
	}
	
	//localhost:8001
	@GetMapping({"","/"})
	public String main() {
		//머스테치 기본폴더 src/main/resources/
		//뷰리절버 설정: templates (prefix).mustache (suffix) //application.yml에 생략가능
		return "mainpage";
	}
	
	@GetMapping("/index")
	public String index(HttpServletRequest request) {
		// 현재 인증 값 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails) authentication.getPrincipal();
		// userId 값 가져오기
		String username = user.getUsername();
		System.out.println("username :: " + username);
		// Session 생성
		HttpSession session = request.getSession();
		// 사용자 정보 가져오기
		User userEntity = userRepository.findByUsername(username);
	    logger.info("userEntity :: " + userEntity);
		session.setAttribute("id", userEntity.getId());
		session.setAttribute("username", userEntity.getUsername());
		session.setAttribute("name", userEntity.getName());
		session.setAttribute("department", userEntity.getDepartment());
		session.setAttribute("email", userEntity.getEmail());
		session.setAttribute("role", userEntity.getRole());
		return "index";
	}
	
	@GetMapping("/std")
	public @ResponseBody String std() {
		return "student";
	}
	@GetMapping("/prof")
	public @ResponseBody String prof() {
		return "professor";
	}
	//원래는 8001/login하면 시큐리티가 낚아챘는데 SecurityConfig 파일 생성후 작동안하고 login페이지로 감
	@GetMapping("/mainpage")
	public String mainpage(HttpServletRequest request) {
		// Session 생성
		HttpSession session = request.getSession();
		// 세션 무효화
		session.invalidate();
		return "mainpage";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	@PostMapping("/join")
	public String join(User user) {
		System.out.println(user);
		
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		userRepository.save(user); //회원가입은 잘되는데. 비밀번호 :1234-> 시큐리티 로그인 불가능(패스워드가 암호가 안돼서)
		return "redirect:/mainpage"; //redirect로 주면 /mainpage라는 함수로 호출
	}
	
//	//@secured("ROLE_ADMIN") 특정메소드에 간단히 걸고 싶을때 쓰면됨
//	@GetMapping("/info")
//	public @ResponseBody String info() {
//		return "개인정보";
//	}
//	//@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") 특정메소드에 간단히 걸고 싶을때 쓰면됨
//	@GetMapping("/data")
//	public @ResponseBody String data() {
//		return "데이터정보";
//	}
}
