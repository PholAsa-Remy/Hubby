package com.meetandcraft.api.project;

import lombok.Data;

import java.util.UUID;

@Data
public class ProjectDto {
    private UUID id;
    private String title;
    private String description;
}
