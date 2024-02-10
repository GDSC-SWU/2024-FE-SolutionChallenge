package com.app.fe.member.repository;

import com.app.fe.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndProvider(@Param("email") String email, @Param("provider") String provider);

    /**
     * 최초 생성된 사용자 인지
     */
    default boolean getSignedMember(String email, String provider) {
        return findByEmailAndProvider(email, provider).isPresent();
    }

    default Member getTblMember(Long memberId) {
        return findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 입니다."));
    }
}
