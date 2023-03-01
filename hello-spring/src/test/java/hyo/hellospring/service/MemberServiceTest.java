package hyo.hellospring.service;

import hyo.hellospring.domain.Member;
import hyo.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() { // 동작하기 전
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() { // 동작한 후 메모리 클리어
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given (상황)
        Member member = new Member();
        member.setName("hello");

        //when (이게 주어졌을 때)
        Long saveId = memberService.join(member);

        //then (결과가 이게 나와야 함)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원_제외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름의 회원입니다.");
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름의 회원입니다.");
//        }

        //then
        
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}