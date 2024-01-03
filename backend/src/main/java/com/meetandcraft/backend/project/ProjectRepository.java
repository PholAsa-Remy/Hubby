package com.meetandcraft.backend.project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.meetandcraft.backend.project.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByPublished(boolean published);
    List<Project> findByTitleContaining(String title);
}