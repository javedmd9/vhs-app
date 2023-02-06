package com.vivacom.vhs.repository;

import com.vivacom.vhs.model.Member;
import com.vivacom.vhs.model.MemberAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberAddressRepository extends JpaRepository<MemberAddress, Integer> {
    List<MemberAddress> findByMember(Member member);
}
