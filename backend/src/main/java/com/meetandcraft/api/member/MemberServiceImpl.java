package com.meetandcraft.api.member;

import com.meetandcraft.api.project.Project;
import com.meetandcraft.api.project.ProjectDto;
import com.meetandcraft.api.project.ProjectMapper;
import com.meetandcraft.api.user.UserEntity;
import com.meetandcraft.api.user.UserEntityDto;
import com.meetandcraft.api.user.UserEntityMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository;
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public MemberDto requestJoining(UserEntityDto userDto, ProjectDto projectDto) {
        UserEntity user = UserEntityMapper.mapToEntity(userDto);
        Project project = ProjectMapper.mapToEntity(projectDto);

        MemberKey memberKey = new MemberKey(user, project);
        Member member = new Member();
        member.setId(memberKey);
        member.setRole(Member.REQUESTING);

        Member savedMember = memberRepository.save(member);

        return MemberMapper.mapToDto(savedMember);
    }

    @Override
    public MemberDto acceptRequestingAsMember(UserEntityDto userDto, ProjectDto projectDto) {
        UserEntity user = UserEntityMapper.mapToEntity(userDto);
        Project project = ProjectMapper.mapToEntity(projectDto);

        MemberKey key = new MemberKey(user, project);
        Member member = memberRepository.findById(key)
                .orElseThrow(() -> new EntityNotFoundException("Member couldn't be found !"));
        member.setRole(Member.MEMBER);
        Member updatedMember = memberRepository.save(member);
        return MemberMapper.mapToDto(updatedMember);
    }

    @Override
    public void removeMember(UserEntityDto userDto, ProjectDto projectDto) {
        UserEntity user = UserEntityMapper.mapToEntity(userDto);
        Project project = ProjectMapper.mapToEntity(projectDto);

        MemberKey key = new MemberKey(user, project);
        memberRepository.deleteById(key);
    }

    @Override
    public List<MemberDto> findAllMemberFromUser(UserEntityDto userDto) {
        UserEntity user = UserEntityMapper.mapToEntity(userDto);
        List<Member> members = memberRepository.findAllById_User(user);

        return members.stream()
                .map(MemberMapper::mapToDto)
                .toList();
    }

    @Override
    public List<MemberDto> findAllMemberFromProject(ProjectDto projectDto) {
        Project project = ProjectMapper.mapToEntity(projectDto);
        List<Member> members = memberRepository.findAllById_Project(project);

        return members.stream()
                .map(MemberMapper::mapToDto)
                .toList();
    }

    @Override
    public MemberDto getMember(UserEntityDto userDto, ProjectDto projectDto) {
        UserEntity user = UserEntityMapper.mapToEntity(userDto);
        Project project = ProjectMapper.mapToEntity(projectDto);
        MemberKey key = new MemberKey(user, project);
        Member member = memberRepository.findById(key)
                .orElseThrow(() -> new EntityNotFoundException("Member couldn't be found !"));
        return MemberMapper.mapToDto(member);
    }
}
