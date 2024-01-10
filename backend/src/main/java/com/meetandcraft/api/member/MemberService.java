package com.meetandcraft.api.member;

import com.meetandcraft.api.project.Project;
import com.meetandcraft.api.user.UserEntity;

import java.util.UUID;

public interface MemberService {
    MemberDto requestJoining (UserEntity user, Project project);
    MemberDto acceptRequestingAsMember (UUID member, Project project);
    MemberDto removeMember (UUID removedMember, UUID initiator);

}
