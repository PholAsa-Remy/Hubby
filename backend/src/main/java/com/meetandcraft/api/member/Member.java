package com.meetandcraft.api.member;

import com.meetandcraft.api.user.UserEntity;
import com.meetandcraft.api.project.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "members")
/* Link a user to a specific project */
public class Member {
    public static final String REQUESTING = "requesting";
    public static final String MEMBER = "member";
    public static final String ADMIN = "admin";
    public static final String OWNER = "owner";

    @EmbeddedId
    private MemberKey id;
    private String role;

    @ManyToOne
    @JoinColumn(name = "users_id", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "projects_id", insertable = false, updatable = false)
    private Project project;
}

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class MemberKey implements Serializable {
    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "projects_id")
    private Project project;
}