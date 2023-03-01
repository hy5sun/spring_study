package hyo.hellospring.repository;

import hyo.hellospring.domain.Member;
import org.apache.el.stream.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();



    @Test
    public void save() {
        Member member = new Member();
        member.setName("hyo");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //Assertions.assertEquals(result, member); // 같은지 확인하는 방법 1
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("hyo1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("hyo2");
        repository.save(member2);

        Member result = repository.findByName("hyo1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("hyo1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("hyo2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
