package com.whatap.SpringLambda.Repository;

import com.whatap.SpringLambda.Domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository{

    private EntityManager em;

    @Autowired
    public JpaMemberRepository(EntityManager em){
        this.em=em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findByEmail(String Email) {
        Member member = em.find(Member.class, Email);
        return Optional.ofNullable(member);
    }

    @Override
    public void updateCount(String Email, int Count) {
        Member member = em.find(Member.class, Email);
        member.setCount(Count+1);
        em.flush();
    }
}
