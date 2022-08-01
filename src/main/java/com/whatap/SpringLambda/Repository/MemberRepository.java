package com.whatap.SpringLambda.Repository;

import com.whatap.SpringLambda.Domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByEmail(String Email);

    void updateCount(String Email, int Count);
}
