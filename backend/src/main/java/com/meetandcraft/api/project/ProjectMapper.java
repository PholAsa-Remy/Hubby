package com.meetandcraft.api.project;

class ProjectMapper {
    public static Project mapToEntity (ProjectDto projectDto) {
        Project project = new Project();
        project.setId(projectDto.getId());
        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        return project;
    }

    public static  ProjectDto mapToDto (Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setTitle(project.getTitle());
        projectDto.setDescription(project.getDescription());
        return projectDto;
    }
}
