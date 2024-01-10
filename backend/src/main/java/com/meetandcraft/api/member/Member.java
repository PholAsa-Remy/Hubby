package com.meetandcraft.api.member;

import com.meetandcraft.api.user.UserEntity;
import com.meetandcraft.api.project.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "members")
/* Link a user to a specific project */
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public enum projectRole {
        REQUESTING, //Waiting to be approved by a member
        MEMBER,
        ADMIN,
        OWNER
    }

    private projectRole projectRole;

    @ManyToOne(fetch= FetchType.EAGER)
    private UserEntity user;

    @ManyToOne(fetch= FetchType.EAGER)
    private Project project;

}
