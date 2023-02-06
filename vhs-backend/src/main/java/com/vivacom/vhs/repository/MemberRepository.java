package com.vivacom.vhs.repository;

import com.vivacom.vhs.model.Member;
import com.vivacom.vhs.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findByUser(Users existingUser);
}
