package hyo.hellospring.service;

import hyo.hellospring.domain.Member;
import hyo.hellospring.repository.MemberRepository;
import hyo.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복회원 검사
        memberRepository.save(member);
        return member.getId(); //id 반환
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { //null이 아닌 값이 있으면 throw
                    throw new IllegalStateException("이미 존재하는 이름의 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // id로 회원 한 명 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
