package com.meetandcraft.api.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface MemberRepository extends JpaRepository<Member, UUID> {
}

