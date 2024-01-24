package com.meetandcraft.api.member;

import com.meetandcraft.api.project.Project;
import com.meetandcraft.api.project.ProjectDto;
import com.meetandcraft.api.project.ProjectMapper;
import com.meetandcraft.api.project.ProjectService;
import com.meetandcraft.api.user.UserEntity;
import com.meetandcraft.api.user.UserEntityDto;
import com.meetandcraft.api.user.UserEntityMapper;
import com.meetandcraft.api.user.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/")
public class MemberController {

    private MemberService memberService;
    private ProjectService projectService;
    private UserEntityService userEntityService;

    @Autowired
    public MemberController (MemberService memberService, ProjectService projectService, UserEntityService userEntityService){
        this.memberService = memberService;
        this.projectService = projectService;
        this.userEntityService = userEntityService;
    }

    @GetMapping("/project/{projectId}/member")
    public ResponseEntity<List<MemberDto>> getAllMembersFromProject( @PathVariable(value = "projectId") UUID projectId){
        ProjectDto projectDto = projectService.getProjectById(projectId);
        Project project = ProjectMapper.mapToEntity(projectDto);
        List<MemberDto> members = memberService.findAllMemberFromProject(projectDto);

        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @PostMapping("/member")
    @ResponseBody
    public ResponseEntity<List<MemberDto>> getAllMembersFromCurrentUser(
            Principal principal
    ){
        UserEntityDto userDto = userEntityService.getUserByUsername(principal.getName());
        List <MemberDto> members = memberService.findAllMemberFromUser(userDto);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @PostMapping("/{username}/member")
    @ResponseBody
    public ResponseEntity<List<MemberDto>> getAllMembersFromUser(
            @PathVariable(value = "username") String username
    ){
        UserEntityDto userDto = userEntityService.getUserByUsername(username);
        List <MemberDto> members = memberService.findAllMemberFromUser(userDto);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

}
