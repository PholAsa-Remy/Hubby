package com.meetandcraft.api.member;
public class MemberMapper {
    public static MemberDto mapToDto (Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setRole(member.getRole());
        memberDto.setUser(member.getUser());
        memberDto.setProject(member.getProject());
        return memberDto;
    }

    public static Member mapToEntity (MemberDto memberDto){
        Member member = new Member();
        member.setId(memberDto.getId());
        member.setRole(memberDto.getRole());
        member.setUser(memberDto.getUser());
        member.setProject(memberDto.getProject());
        return member;
    }
}
