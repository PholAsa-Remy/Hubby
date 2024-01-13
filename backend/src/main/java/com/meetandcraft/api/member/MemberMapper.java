package com.meetandcraft.api.member;

import com.meetandcraft.api.project.Project;

public class MemberMapper {
    public static MemberDto mapToDto (Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setUserId(member.getId().getUser().getId());
        memberDto.setProjectId(member.getId().getProject().getId());
        memberDto.setRole(member.getRole());
        return memberDto;
    }
}
