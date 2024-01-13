package com.meetandcraft.api.member;

import com.meetandcraft.api.project.Project;
import com.meetandcraft.api.user.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface MemberService {
    MemberDto requestJoining (UserEntity user, Project project);
    MemberDto acceptRequestingAsMember (UserEntity user, Project project);
    void removeMember (UserEntity user, Project project);
    List<MemberDto> findAllMemberFromUser (UserEntity user);
    List<MemberDto> findAllMemberFromProject(Project project);
    MemberDto getMember (UserEntity user, Project project);
}
