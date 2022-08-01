package com.whatap.SpringLambda.Service;

import com.whatap.SpringLambda.Domain.Member;
import com.whatap.SpringLambda.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    /**
     *회원가입
     */
    public void join(Member member){
        check(member);
        memberRepository.save(member);
    }

    /**
     * 중복검사
     */
    public void check(Member member){
        memberRepository.findByEmail(member.getEmail()).ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 이메일입니다");
        });
    }

    /**
     * 로그인
     */
    public boolean login(Member member){
        Optional<Member> optional = findOne(member.getEmail());
        if(optional.isPresent()){
            Member m=optional.get();
            if(m.getCount()>3){
                return false;
            }

            return true;
        }
        else{
            join(member);
            return true;
        }
    }

    /**
     * 회원 조회
     */
    public Optional<Member> findOne(String Email) {
        return memberRepository.findByEmail(Email);
    }

    /**
     * 카운트 증가
     */
    public void addCount(String Email){
        Optional<Member> optional = findOne(Email);
        if(optional.isPresent()){
            Member m=optional.get();
            memberRepository.updateCount(m.getEmail(), m.getCount());
        }
    }
}
