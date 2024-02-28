package org.bessam.mylocallibraryws.membershipsubdomain.dataacess;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByMemberIdentifier_MemberId(String memberId);
}
