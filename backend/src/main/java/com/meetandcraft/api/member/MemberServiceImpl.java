package com.meetandcraft.api.member;

import com.meetandcraft.api.project.Project;
import com.meetandcraft.api.user.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository;

    public MemberDto requestJoining(UserEntity user, Project project) {
        MemberKey memberKey = new MemberKey(user, project);
        Member member = new Member();
        member.setId(memberKey);
        member.setRole(Member.REQUESTING);

        Member savedMember = memberRepository.save(member);

        return MemberMapper.mapToDto(savedMember);
    }

    @Override
    public MemberDto acceptRequestingAsMember(UserEntity user, Project project) {
        MemberKey key = new MemberKey(user, project);
        Member member = memberRepository.findById(key)
                .orElseThrow(() -> new EntityNotFoundException("Member couldn't be found !"));
        member.setRole(Member.MEMBER);
        Member updatedMember = memberRepository.save(member);
        return MemberMapper.mapToDto(updatedMember);
    }

    @Override
    public void removeMember(UserEntity user, Project project) {
        MemberKey key = new MemberKey(user, project);
        memberRepository.deleteById(key);
    }

    @Override
    public List<MemberDto> findAllMemberFromUser(UserEntity user) {
        List<Member> members = memberRepository.findAllById_User(user);

        return members.stream()
                .map(MemberMapper::mapToDto)
                .toList();
    }

    @Override
    public List<MemberDto> findAllMemberFromProject(Project project) {
        List<Member> members = memberRepository.findAllById_Project(project);

        return members.stream()
                .map(MemberMapper::mapToDto)
                .toList();
    }

    @Override
    public MemberDto getMember(UserEntity user, Project project) {
        MemberKey key = new MemberKey(user, project);
        Member member = memberRepository.findById(key)
                .orElseThrow(() -> new EntityNotFoundException("Member couldn't be found !"));
        return MemberMapper.mapToDto(member);
    }
}
