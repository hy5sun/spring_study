package hyo.hellospring;

import hyo.hellospring.repository.MemberRepository;
import hyo.hellospring.repository.MemoryMemberRepository;
import hyo.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//autowired로 연결짓지 않고, 직접 자바 코드로 스프링 빈에 등록하기
@Configuration
public class SpringConfig {

    @Bean //스프링 빈에 등록됨
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
