package com.meetandcraft.api.member;

import com.meetandcraft.api.project.Project;
import com.meetandcraft.api.project.ProjectDto;
import com.meetandcraft.api.user.UserEntity;
import com.meetandcraft.api.user.UserEntityDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface MemberService {
    MemberDto requestJoining (UserEntityDto user, ProjectDto project);
    MemberDto acceptRequestingAsMember (UserEntityDto user, ProjectDto project);
    void removeMember (UserEntityDto user, ProjectDto project);
    List<MemberDto> findAllMemberFromUser (UserEntityDto user);
    List<MemberDto> findAllMemberFromProject(ProjectDto project);
    MemberDto getMember (UserEntityDto user, ProjectDto project);
}
