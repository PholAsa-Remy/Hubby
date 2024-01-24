package com.meetandcraft.api.member;

import com.meetandcraft.api.project.Project;
import com.meetandcraft.api.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface MemberRepository extends JpaRepository<Member, MemberKey> {
    List<Member> findAllById_Project(Project project);
    List<Member> findAllById_User(UserEntity user);
}

